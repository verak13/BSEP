package hospital.hospital;

import hospital.hospital.model.BlackListedIP;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import hospital.hospital.model.User;
import hospital.hospital.model.cep.MessageEvent;
import hospital.hospital.services.LogService;
import hospital.hospital.services.MailService;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieRuntime;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hospital.hospital.enums.BloodType;
import hospital.hospital.enums.Gender;
import hospital.hospital.model.Log;
import hospital.hospital.model.LogConfig;
import hospital.hospital.model.LogConfigs;
import hospital.hospital.model.Message;
import hospital.hospital.model.Patient;

import hospital.hospital.repository.LogRepository;
import hospital.hospital.repository.PatientRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.util.ResourceUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HospitalApplication/* implements CommandLineRunner*/ {

	
	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private PatientRepository patientRepository;

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
		
		LogConfig newConfig = new LogConfig("C:\\\\bezb\\BSEP\\simulator\\simulator2.log", 10000, ".*INFOs.*", 1L);
		LogConfig updatedConfig = new LogConfig("C:\\\\bezb\\BSEP\\simulator\\simulator2.log", 5000, ".*INFO.*", 1L);
		
		Gson gson = new GsonBuilder().create();
		File file = ResourceUtils.getFile("classpath:configuration.json");
		
		LogConfigs configs = gson.fromJson(new FileReader(file), LogConfigs.class);
		
		boolean found = false;
		for (LogConfig conf : configs.getLogConfigs()) {
			System.out.println(conf.getFile());
			if (conf.getFile().equals(newConfig.getFile()) && conf.getHospitalId().equals(newConfig.getHospitalId())) {
				System.out.println("found" + conf.getFile());
				found = true;
				conf.setInterval(newConfig.getInterval());
				conf.setRegexp(newConfig.getRegexp());
			}
		}
		if (!found) {
			configs.getLogConfigs().add(newConfig);
	        //this.logService.addNewConfig(newConfig);
		}
		
		System.out.println("KONACNO");
		for (LogConfig conf : configs.getLogConfigs()) {
			System.out.println(conf.getFile() + conf.getInterval() + conf.getRegexp() + conf.getHospitalId());
		}
		
		Writer writer = new FileWriter(file.getAbsolutePath());
		gson.toJson(configs, writer);
        writer.close();
		
		Message message = new Message();
		message.setBodyTemperature(30.0);
		message.setPatientId(1L);
		message.setPulseRaye(40.0);
		message.setRespirationRate(60.0);
		message.setBloodPressureDiastolic(50.0);
		message.setBloodPressureSystolic(115.0);
		kieSession.insert(new MessageEvent(message));
        kieSession.getAgenda().getAgendaGroup("doctor-alarms").setFocus();
		kieSession.fireAllRules();
		
	}*/
	
}
