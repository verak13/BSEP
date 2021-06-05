package hospital.hospital;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hospital.hospital.model.Log;
import hospital.hospital.repository.LogRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class HospitalApplication/* implements CommandLineRunner*/ {
	
	@Autowired
	private LogRepository logRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(HospitalApplication.class);

	public static void main(String[] args) {
	      
		SpringApplication.run(HospitalApplication.class, args);
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
