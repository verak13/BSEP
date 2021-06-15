package hospital.hospital.controller;

import hospital.hospital.model.Report;
import hospital.hospital.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/report", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PreAuthorize("hasRole('HOSPITAL_ADMIN') || hasRole('SUPER_ADMIN')")
    @RequestMapping(value="/generate", method = RequestMethod.GET)
    public ResponseEntity<?> generateReport()throws Exception {

        Report report = reportService.generate();

        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('HOSPITAL_ADMIN') || hasRole('SUPER_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll()throws Exception {

        return new ResponseEntity<>(reportService.findAll(), HttpStatus.OK);
    }
}
