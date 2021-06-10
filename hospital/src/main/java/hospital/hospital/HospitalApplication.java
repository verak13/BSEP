package hospital.hospital;

import hospital.hospital.model.BlackListedIP;
import hospital.hospital.services.MailService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hospital.hospital.repository.LogRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HospitalApplication/* implements CommandLineRunner*/ {
	
	@Autowired
	private LogRepository logRepository;

	@Autowired
	private MailService mailService;

	@Autowired
	private KieSession kieSession;

	private static final Logger logger = LoggerFactory.getLogger(HospitalApplication.class);

	@Bean
	public KieSession kieSession() {
		KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
		KieSession kieSession = kc.newKieSession("rulesSession");
		KieRuntime kieRuntime = (KieRuntime) kieSession;
		kieRuntime.setGlobal("mailService", mailService);

		return kieSession;
	}

	public static void main(String[] args) {
	      
		SpringApplication.run(HospitalApplication.class, args);
	}


	@EventListener(ApplicationReadyEvent.class)
	public void loadBlackListedIPs() {
		List<String> ips = new ArrayList<>();
		try {
			File file = ResourceUtils.getFile("classpath:blacklisted-ip.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while (null != line) {
				line = line.replace("\n", "");
				ips.add(line);
				line = reader.readLine();
			}
			BlackListedIP blackListedIP = new BlackListedIP(ips);

			kieSession.insert(blackListedIP);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	/*@Override
	public void run(String... args) throws Exception {
		
		
		logger.info("this is a info message");
	    logger.warn("this is a warn message");
	    logger.error("this is a error message");
	    
	    		//List<Log> logPage = logRepository.searchLogsTest(search.getFrom(), search.getTo(), search.getIp(), search.getType(), search.getSeverity().toString(), search.getMessage(), search.getSource(), search.getUsername());
		List<Log> logPage = logRepository.searchLogsTest(null, new Date(), "", "", "INFO", "", "", "");
	    
	    System.out.println("SEARCH============================================================");
	    for (Log l : logPage) {
	    	System.out.println(l);
	    }
	    System.out.println("SEARCHEND============================================================");
		
	}
	*/
}
