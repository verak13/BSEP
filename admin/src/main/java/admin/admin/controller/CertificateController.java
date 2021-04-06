package admin.admin.controller;

import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import admin.admin.model.CreateCertificateDTO;
import admin.admin.model.CertificateDTO;
import admin.admin.model.RevokeCertificateDTO;
import admin.admin.services.CertificateRequestService;
import admin.admin.services.CertificateService;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/certificate", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateController {
	
	@Autowired
    CertificateService certificateService;
	
	@Autowired
    CertificateRequestService certificateRequestService;
	
	@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCertificate(@Valid @RequestBody CreateCertificateDTO certificateCreationDTO) throws OperatorCreationException, CertificateException {
		
        try {
	        if (certificateService.createAdminCertificate(certificateCreationDTO, "superadmin@admin.com")) {
	        	certificateRequestService.delete(certificateCreationDTO.getRequestId());
		        return new ResponseEntity<>(HttpStatus.CREATED);
	        } else {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

	@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> revokeCertificate(@Valid @RequestBody RevokeCertificateDTO revokeCertificateDTO) {

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //User loggedIn = (User) authentication.getPrincipal();

        try {
            certificateService.revokeCertificate(revokeCertificateDTO, "superadmin@admin.com");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    
	@PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ArrayList<CertificateDTO>> readAllCertificates() {

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //User loggedIn = (User) authentication.getPrincipal();

    	ArrayList<CertificateDTO> certificates = new ArrayList<CertificateDTO>();
        try {
            certificates = certificateService.readAllCertificates();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ArrayList<CertificateDTO>>(certificates, HttpStatus.OK);

    }
	
	
	
	
	
	
	
	
	
	
	
	
	

}
