package hospital.hospital;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import hospital.hospital.model.User;
import hospital.hospital.services.SecurityService;
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
import hospital.hospital.model.Patient;
import hospital.hospital.repository.LogRepository;
import hospital.hospital.repository.PatientRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class HospitalApplication implements CommandLineRunner {
	
	@Autowired
	private LogRepository logRepository;
	
	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private SecurityService securityService;
	
	private static final Logger logger = LoggerFactory.getLogger(HospitalApplication.class);

	@Bean
	public KieSession kieSession() {
		KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
		KieSession kieSession = kc.newKieSession("rulesSession");
		KieRuntime kieRuntime = (KieRuntime) kieSession;
		kieRuntime.setGlobal("securityService", securityService);

		return kieSession;
	}

	public static void main(String[] args) {
	      
		SpringApplication.run(HospitalApplication.class, args);
	}


	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {

	}


	@Override
	public void run(String... args) throws Exception {
		
		
		/*logger.info("this is a info message");
	    logger.warn("this is a warn message");
	    logger.error("this is a error message");
	    
	    		//List<Log> logPage = logRepository.searchLogsTest(search.getFrom(), search.getTo(), search.getIp(), search.getType(), search.getSeverity().toString(), search.getMessage(), search.getSource(), search.getUsername());
		List<Log> logPage = logRepository.searchLogsTest(null, new Date(), "", "", "INFO", "", "", "");
	    
	    System.out.println("SEARCH============================================================");
	    for (Log l : logPage) {
	    	System.out.println(l);
	    }
	    System.out.println("SEARCHEND============================================================");*/
		
		//INSERT INTO patients (jmbg, first_name, last_name, birth_date, height, weight, gender, blood_type) VALUES ('1234567890', 'John', 'Smith', '2021-06-01 00:00:00', 185.0, 90.0, 'MALE', 'AB');

		
		patientRepository.save(new Patient(1L, "12345678", "John", "Smith", new Date(), 180.0, 90.0, Gender.MALE, BloodType.AB));
		List<Patient> patients = patientRepository.findAll();
		for (Patient p : patients) {
			System.out.println("*********************************************");
			System.out.println(p.getFirstName() + p.getLastName() + p.getJmbg());
		}
	}
	
}
