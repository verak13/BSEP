package hospital.hospital.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import hospital.hospital.model.CertificateRequest;
import hospital.hospital.services.CertificateRequestService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/certificate-request", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateRequestController {

    @Autowired
    CertificateRequestService certificateRequestService;

    @PreAuthorize("hasRole('HOSPITAL_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> sendCSR(@Valid @RequestBody CertificateRequest csr) throws Exception {

        System.out.println("POGODJEN CTRL");
        
        if (certificateRequestService.createCSR(csr)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}