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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hospital.hospital.dto.FilterMessagesDTO;
import hospital.hospital.dto.MessageResponseDTO;
import hospital.hospital.model.cep.alarms.MessageAlarm;
import hospital.hospital.services.MessageAlarmService;

@RestController
@RequestMapping(value = "/message-alarms", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageAlarmController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogConfigController.class);

	@Autowired
	MessageAlarmService messageAlarmService;
	
	@PreAuthorize("hasRole('DOCTOR')")
    @RequestMapping(value= "/by-page",method = RequestMethod.GET)
    public ResponseEntity<Page<MessageAlarm>> getAllCulturalOffersPaged(Pageable pageable) {

    	logger.trace("View message alarms requested.");
        return new ResponseEntity<>(messageAlarmService.findAll(pageable), HttpStatus.OK);
    }


}
