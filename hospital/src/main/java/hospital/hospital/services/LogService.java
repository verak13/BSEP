package hospital.hospital.services;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import hospital.hospital.model.Patient;
import hospital.hospital.model.cep.LogEvent;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import hospital.hospital.dto.SearchLogDTO;
import hospital.hospital.model.Log;
import hospital.hospital.model.LogConfig;
import hospital.hospital.model.LogConfigs;
import hospital.hospital.parser.ApplicationLogParser;
import hospital.hospital.parser.KeycloakLogParser;
import hospital.hospital.parser.SimulatorLogParser;
import hospital.hospital.repository.LogRepository;
import com.google.gson.Gson;

@Service
public class LogService {
	
	@Autowired
	LogRepository logRepository;

	@Autowired
	private KieSession kieSession;
	
	@Value("${pathkeycloak}")
	private String pathKeycloak;
	
	private static ApplicationLogParser applicationLogParser = new ApplicationLogParser();
	private static SimulatorLogParser simulatorLogParser = new SimulatorLogParser();
	private static KeycloakLogParser keycloakLogParser = new KeycloakLogParser();
	/*private Date applicationLogDate = null; 
	private Date simulatorLogDate = null; 
	private Date keycloakLogDate = null; */

	public Page<Log> findAll(Pageable pageable, SearchLogDTO search) {
		Page<Log> logPage = logRepository.searchLogs(search.getFrom(), search.getTo(), search.getIp(), search.getType(), search.getSeverity().toString(), search.getMessage(), search.getSource(), search.getUsername(), pageable);
		ArrayList<Log> forReturn = new ArrayList<Log>();
		for (Log l : logPage.getContent()) {
			forReturn.add(l);
		}
		return new PageImpl<Log>(forReturn, pageable, logPage.getTotalElements());
	}
	
	
	public void save(List<Log> logs) {
		for (Log log: logs) {
			kieSession.insert(new LogEvent(log));
		}
		kieSession.fireAllRules();
		this.logRepository.saveAll(logs);
	}
	
	@PostConstruct
	public void init() {
		try {
			Gson gson = new Gson();
			File file = ResourceUtils.getFile("classpath:configuration.json");
			LogConfigs configs = gson.fromJson(new FileReader(file), LogConfigs.class);
			for (LogConfig lc : configs.getLogConfigs()) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							readSimulatorLogs(lc.getFile(), lc.getInterval(), lc.getRegexp());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						readKeycloakLogs();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						readApplicationLogs();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addNewConfig(LogConfig lc) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					readSimulatorLogs(lc.getFile(), lc.getInterval(), lc.getRegexp());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	/*public List<Log> findAllTest(SearchLogDTO search) {
		List<Log> logPage = logRepository.searchLogsTest(search.getFrom(), search.getTo(), search.getIp(), search.getType(), search.getSeverity().toString(), search.getMessage(), search.getSource(), search.getUsername());
		return logPage;
	}*/
	
	protected void readApplicationLogs() throws IOException, InterruptedException {
		Date flagDate = new Date();
		while (true) {
			flagDate = this.readAppLogs(flagDate, "logs/application-logs.log");
			Thread.sleep(5000);
		}
	}
	
	private Date readAppLogs(Date flagDate, String path) throws IOException {
		ArrayList<Log> logs = new ArrayList<>();
		ReversedLinesFileReader reader = new ReversedLinesFileReader(new File(path));
		Date newFlagDate = new Date();
		try {
			String line = reader.readLine();
			boolean setNewFlagDate = false;
			while (line != null) {
				Log log = applicationLogParser.parse(line);
				if (log == null) {
					line = reader.readLine();
					continue;
				}
				if (!setNewFlagDate) {
					newFlagDate = log.getTimestamp();
					setNewFlagDate = true;
				}
				if (!log.getTimestamp().after(flagDate)) {
					break;
				}
				logs.add(log);
				line = reader.readLine();
			}
		} finally {
			reader.close();
		}
		Collections.reverse(logs);
		this.save(logs);
		return newFlagDate;
	}

	private void readSimulatorLogs(String path, long interval, String regexp) throws Exception {
		Date flagDate = new Date();
		while (true) {
			flagDate = this.readSimulatorLogs(flagDate, regexp, path);
			Thread.sleep(interval);
		}
	}

	private Date readSimulatorLogs(Date flagDate, String regexp, String path) throws IOException {
		ArrayList<Log> logs = new ArrayList<>();
		ReversedLinesFileReader reader = new ReversedLinesFileReader(new File(path));
		Date newFlagDate = new Date();
		try {
			String line = reader.readLine();
			System.out.println("SIMULATOR");
			System.out.println(line);
			boolean setNewFlagDate = false;
			while (line != null) {
				Log log = simulatorLogParser.parse(line);
				if (!setNewFlagDate) {
					newFlagDate = log.getTimestamp();
					setNewFlagDate = true;
				}
				if (!log.getTimestamp().after(flagDate)) {
					break;
				}
				if (log.getMessage().matches(regexp)) {
					logs.add(log);
				}

				line = reader.readLine();
			}
		} finally {
			reader.close();
		}
		Collections.reverse(logs);
		this.save(logs);
		return newFlagDate;
	}
	
	private void readKeycloakLogs() throws Exception {
		Date flagDate = new Date();
		while (true) {
			flagDate = this.readKeycloak(flagDate);
			Thread.sleep(10000);
		}
	}

	private Date readKeycloak(Date flagDate) throws IOException {
		ArrayList<Log> logs = new ArrayList<>();
		ReversedLinesFileReader reader = new ReversedLinesFileReader(new File(pathKeycloak));
		Date newFlagDate = new Date();
		try {
			String line = reader.readLine();	
			boolean setNewFlagDate = false;
			while (line != null) {
				Log log = keycloakLogParser.parse(line);
				System.out.println("KEYCLOAKLOG");
				System.out.println(log);
				if (log == null) {
					line = reader.readLine();
					continue;
				}
				if (!setNewFlagDate) {
					newFlagDate = log.getTimestamp();
					setNewFlagDate = true;
				}
				if (!log.getTimestamp().after(flagDate)) {
					break;
				}

				line = reader.readLine();
			}
		} finally {
			reader.close();
		}
		Collections.reverse(logs);
		this.save(logs);
		return newFlagDate;
	}

}
