package hospital.hospital.controller;
//import hospital.hospital.model.User;
//import org.keycloak.models.KeycloakSession;
//import org.keycloak.models.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Date;

@RestController
@RequestMapping(value = "/certificate-request", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateRequestController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogConfigController.class);

    @Autowired
    CertificateRequestService certificateRequestService;

    @PreAuthorize("hasRole('HOSPITAL_ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> sendCSR(@Valid @RequestBody CertificateRequest csr) throws Exception {

        if (certificateRequestService.createCSR(csr)) {
        	logger.trace("New CSR created.");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
        	logger.error("Creating CSR failed.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}