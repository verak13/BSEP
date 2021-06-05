package hospital.hospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import hospital.hospital.model.LogConfig;
import hospital.hospital.services.LogConfigService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/log-config")
public class LogConfigController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogConfigController.class);
	
	@Autowired
	private LogConfigService logConfigService;
	
	//@PreAuthorize("hasRole('HOSPITAL_ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveConfig(@RequestBody LogConfig logConfig) {
		try {
			this.logConfigService.saveConfig(logConfig);
			logger.trace("New simulator file added.");
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
