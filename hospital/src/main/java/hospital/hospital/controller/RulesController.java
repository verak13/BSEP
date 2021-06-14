package hospital.hospital.controller;

import javax.validation.Valid;

import hospital.hospital.dto.LogRuleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hospital.hospital.dto.CustomMessageRuleDTO;
import hospital.hospital.dto.RuleBloodPressureDTO;
import hospital.hospital.dto.RuleDTO;
import hospital.hospital.model.Patient;
import hospital.hospital.services.RulesService;

@RestController
@RequestMapping(value = "/rules", produces = MediaType.APPLICATION_JSON_VALUE)
public class RulesController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogConfigController.class);
	
	@Autowired
	RulesService rulesService;

    @PreAuthorize("hasRole('DOCTOR')")
    @RequestMapping(value="/high-temperature", method= RequestMethod.POST)
    public ResponseEntity<?> createHighTemperatureRule(@Valid @RequestBody RuleDTO dto) throws Exception {

    	if (rulesService.createHighTemperatureRule(dto)) {
        	logger.trace("Added new doctor rule.");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	logger.error("Adding rule failed.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PreAuthorize("hasRole('DOCTOR')")
    @RequestMapping(value = "/blood-pressure", method = RequestMethod.POST)
    public ResponseEntity<?> createBloodPressureRule(@Valid @RequestBody RuleBloodPressureDTO dto) throws Exception {

    	if (rulesService.createBloodPressureRule(dto)) {
        	logger.trace("Added new doctor rule.");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	logger.error("Adding rule failed.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PreAuthorize("hasRole('DOCTOR')")
    @RequestMapping(value = "/custom-message-rule", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomMessageRule(@Valid @RequestBody CustomMessageRuleDTO dto) throws Exception {

    	if (rulesService.createCustomMessageRule(dto)) {
        	logger.trace("Added new doctor rule.");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	logger.error("Adding rule failed.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('HOSPITAL_ADMIN')")
    @RequestMapping(value = "/log-rule", method = RequestMethod.POST)
    public ResponseEntity<?> createLogRume(@RequestBody LogRuleDTO dto) throws Exception {

        if (rulesService.createLogRule(dto)) {
            logger.trace("Added new log rule.");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.error("Adding log rule failed.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
