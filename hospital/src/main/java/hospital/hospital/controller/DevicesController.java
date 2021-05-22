package hospital.hospital.controller;


import hospital.hospital.model.CertificateRequest;
import hospital.hospital.services.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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

    @Autowired
    DevicesService devicesService;

    @RequestMapping(value="/send", method= RequestMethod.POST)
    public ResponseEntity<?> sendMsg(@Valid @RequestBody String message) throws Exception {

        if (devicesService.receiveMessage(message)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
