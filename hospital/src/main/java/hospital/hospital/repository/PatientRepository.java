package hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>  {

}
