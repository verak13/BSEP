package hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.ErrorLogAlarm;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorLogAlarmRepository extends JpaRepository<ErrorLogAlarm, Long> {

}
