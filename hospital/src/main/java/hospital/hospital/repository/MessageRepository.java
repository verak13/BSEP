package hospital.hospital.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hospital.hospital.model.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	@Query("SELECT m FROM Message m WHERE m.patientId = ?1")
	Page<Message> findAllByPatientId(long patientId, Pageable pageable);

}
