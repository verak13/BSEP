package hospital.hospital.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hospital.hospital.model.cep.alarms.BlackListedIPAlarm;
import hospital.hospital.model.cep.alarms.BruteForceLoginAlarm;
import hospital.hospital.model.cep.alarms.ErrorLogAlarm;
import hospital.hospital.model.cep.alarms.InactiveUserAlarm;
import hospital.hospital.model.cep.alarms.MessageAlarm;
import hospital.hospital.services.AlarmService;


@RestController
@RequestMapping(value = "/alarms", produces = MediaType.APPLICATION_JSON_VALUE)
public class AlarmController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogConfigController.class);

	@Autowired
	AlarmService alarmService;
	
	@PreAuthorize("hasRole('HOSPITAL_ADMIN')")
	@RequestMapping(value= "/blacklisted-ip/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<BlackListedIPAlarm>> findAllBlackListedIPAlarms(Pageable pageable) {

    	logger.trace("View black listed ip alarms requested.");
        return new ResponseEntity<>(alarmService.findAllBlackListedIPAlarms(pageable), HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('HOSPITAL_ADMIN')")
	@RequestMapping(value= "/bruteforce-login/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<BruteForceLoginAlarm>> findAllBruteForceLoginAlarms(Pageable pageable) {

    	logger.trace("View black brute force login alarms requested.");
        return new ResponseEntity<>(alarmService.findAllBruteForceLoginAlarms(pageable), HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('HOSPITAL_ADMIN')")
	@RequestMapping(value= "/error-log/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<ErrorLogAlarm>> findAllErrorLogAlarms(Pageable pageable) {

    	logger.trace("View black error log alarms requested.");
        return new ResponseEntity<>(alarmService.findAllErrorLogAlarms(pageable), HttpStatus.OK);
    }
	
	@PreAuthorize("hasRole('HOSPITAL_ADMIN')")
	@RequestMapping(value= "/inactive-user/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<InactiveUserAlarm>> findAllInactiveUserAlarms(Pageable pageable) {

    	logger.trace("View black inactive user alarms requested.");
        return new ResponseEntity<>(alarmService.findAllInactiveUserAlarms(pageable), HttpStatus.OK);
    }
}
