package admin.admin.controller;

import admin.admin.model.Hospital;
import admin.admin.model.LogConfig;
import admin.admin.model.User;
import org.bouncycastle.operator.OperatorCreationException;
import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import admin.admin.dto.CertificateDTO;
import admin.admin.dto.CreateCertificateDTO;
import admin.admin.dto.LogConfigDTO;
import admin.admin.dto.RevokeCertificateDTO;
import admin.admin.services.CertificateRequestService;
import admin.admin.services.CertificateService;
import admin.admin.services.HospitalService;
import admin.admin.services.LogConfigService;

import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/configuration", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogConfigController {
	
	@Autowired
	private LogConfigService configurationService;
	
	@Autowired
	private HospitalService hospitalService;
	
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createConfig(@Valid @RequestBody LogConfigDTO logConfigDTO) {
		try {
			configurationService.createConfig(logConfigDTO);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	@RequestMapping(value= "/hospitals/by-page",method = RequestMethod.GET)
	public ResponseEntity<?> getHospitals(Pageable pageable) {
		try {
			Page<Hospital> hospitals = hospitalService.findAll(pageable);
			return new ResponseEntity<>(hospitals, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
