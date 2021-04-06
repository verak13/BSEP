package admin.admin.controller;
import admin.admin.dto.CertificateRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import admin.admin.model.CertificateRequest;
import admin.admin.services.CertificateRequestService;

import java.util.List;

@RestController
@RequestMapping(value = "/certificate-request", produces = MediaType.APPLICATION_JSON_VALUE)
public class CertificateRequestController {

    @Autowired
    CertificateRequestService certificateRequestService;

    @RequestMapping(value = "/send-certificate-request", method = RequestMethod.POST)
    public ResponseEntity<?> sendCertificateRequest(@RequestBody CertificateRequestDTO encryptedCSR) {

        try {
            boolean success = certificateRequestService.createCertificateRequest(encryptedCSR);
            if (success) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificateRequest>> getCertificateRequests() {

        List<CertificateRequest> reqs = certificateRequestService.findAll();
        return new ResponseEntity<>(reqs, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> removeCertificateRequest(@PathVariable Integer id) {

        if (certificateRequestService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    
}