package admin.admin.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import admin.admin.model.Hospital;
import admin.admin.repository.HospitalRepository;


@Service
public class HospitalService {
	
	@Autowired
	HospitalRepository hospitalRepository;

	public Page<Hospital> findAll(Pageable pageable) {
		return hospitalRepository.findAll(pageable);
	}


}
