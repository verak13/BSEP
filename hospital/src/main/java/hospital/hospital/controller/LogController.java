package hospital.hospital.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospital.hospital.dto.SearchLogDTO;
import hospital.hospital.model.Log;
import hospital.hospital.services.LogService;


@RestController
@RequestMapping(value = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogController {
	
	@Autowired
    LogService logService;
	
	private static final Logger logger = LoggerFactory.getLogger(LogController.class);

	@PreAuthorize("hasRole('HOSPITAL_ADMIN')")
	@PostMapping(value = "/by-page")
	public ResponseEntity<?> getLogs(Pageable pageable, @Valid @RequestBody SearchLogDTO searchLog) {
		try {
			logger.trace("View logs requested");
			return new ResponseEntity<Page<Log>>(logService.findAll(pageable, searchLog), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
