package hospital.hospital.services;

import java.util.Date;
import java.util.List;

import hospital.hospital.enums.BloodType;
import hospital.hospital.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hospital.hospital.model.Patient;
import hospital.hospital.repository.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	public Page<Patient> findAll(Pageable pageable) {
		//patientRepository.save(new Patient(2L, "1231221", "Gojko", "Maras", new Date(), 180.0, 75.0, Gender.MALE, BloodType.A));

		return patientRepository.findAll(pageable);
	}

	public Patient findOne(long id) {
		return patientRepository.findById(id).orElse(null);  }
	}

