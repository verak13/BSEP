package hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.InactiveUserAlarm;

public interface InactiveUserAlarmRepository extends JpaRepository<InactiveUserAlarm, Long> {

}
