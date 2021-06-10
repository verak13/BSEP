package hospital.hospital.controller;


import hospital.hospital.dto.DeviceMessageDTO;
import hospital.hospital.keystore.KeyStoreReader;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

@RestController
@RequestMapping(value = "/devices")
public class DevicesController {

    @Autowired
    DevicesService devicesService;

    @RequestMapping(value="/send", method= RequestMethod.POST)
    public ResponseEntity<?> sendMsg(@Valid @RequestBody byte[] message, HttpServletRequest servlet) throws Exception {
        X509Certificate[] certs = (X509Certificate[]) servlet.getAttribute("javax.servlet.request.X509Certificate");
        String cerAlias = certs[0].getSubjectDN().getName().substring(3);


        if (devicesService.receiveMessage(message, cerAlias)){
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
