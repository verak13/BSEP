package hospital.hospital.controller;


import hospital.hospital.dto.FilterMessagesDTO;
import hospital.hospital.dto.MessageResponseDTO;
import hospital.hospital.services.DevicesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/devices")
public class DevicesController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogConfigController.class);

    @Autowired
    DevicesService devicesService;

    @RequestMapping(value="/send", method= RequestMethod.POST)
    public ResponseEntity<?> sendMsg(@Valid @RequestBody String message) throws Exception {

        if (devicesService.receiveMessage(message)){
        	logger.trace("New message received.");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	logger.error("Message receiving failed.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping(value= "/by-page",method = RequestMethod.POST)
    public ResponseEntity<Page<MessageResponseDTO>> getAllCulturalOffersPaged(Pageable pageable, @RequestBody FilterMessagesDTO filter) {

    	logger.trace("View messages requested.");
        return new ResponseEntity<>(devicesService.findAll(pageable, filter), HttpStatus.OK);
    }

}
