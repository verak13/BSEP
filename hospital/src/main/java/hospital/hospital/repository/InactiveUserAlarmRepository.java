package hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.InactiveUserAlarm;
import org.springframework.stereotype.Repository;

@Repository
public interface InactiveUserAlarmRepository extends JpaRepository<InactiveUserAlarm, Long> {

}
