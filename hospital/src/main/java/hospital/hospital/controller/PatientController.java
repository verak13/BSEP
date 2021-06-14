package hospital.hospital.controller;

import java.util.List;

import javax.validation.Valid;

import hospital.hospital.enums.BloodType;
import hospital.hospital.enums.Gender;
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

import hospital.hospital.model.CertificateRequest;
import hospital.hospital.model.Patient;
import hospital.hospital.services.CertificateRequestService;
import hospital.hospital.services.PatientService;

@RestController
@RequestMapping(value = "/patients", produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {
	
	private static final Logger logger = LoggerFactory.getLogger(LogConfigController.class);

	@Autowired
    PatientService patientService;

    @PreAuthorize("hasRole('DOCTOR') || hasRole(SUPER_ADMIN)")
    @RequestMapping(value= "/by-page",method = RequestMethod.GET)
    public ResponseEntity<?> getPatients(Pageable pageable)throws Exception {


    	Page<Patient> reqs = patientService.findAll(pageable);
    	logger.trace("Patients view requested.");
        return new ResponseEntity<>(reqs, HttpStatus.OK);
    }

}
