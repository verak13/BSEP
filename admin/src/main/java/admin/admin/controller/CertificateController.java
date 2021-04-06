package admin.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import admin.admin.model.CertificateCreationDTO;
import admin.admin.model.RevokeCertificateDTO;
import admin.admin.services.CertificateRequestService;
import admin.admin.services.CertificateService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/certificate", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {
	
	@Autowired
    CertificateService certificateService;
	
	@Autowired
    CertificateRequestService certificateRequestService;
	
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCertificate(@Valid @RequestBody CertificateCreationDTO certificateCreationDTO) {

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Admin loggedIn = (Admin) authentication.getPrincipal();

        try {
            certificateService.createAdminCertificate(certificateCreationDTO, "superadmin@admin.com");
            certificateRequestService.delete(certificateCreationDTO.getRequestID());
            return new ResponseEntity<>(HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> revokeCertificate(@Valid @RequestBody RevokeCertificateDTO revokeCertificateDTO) {

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //Admin loggedIn = (Admin) authentication.getPrincipal();

        try {
            certificateService.revokeCertificate(revokeCertificateDTO, "superadmin@admin.com");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
	
	
	
	
	
	
	
	
	
	
	
	

}
