package admin.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import admin.admin.model.Hospital;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>  {

	Hospital findById(long id);
}
