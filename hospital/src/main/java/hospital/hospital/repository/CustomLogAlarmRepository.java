package hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.CustomLogAlarm;

public interface CustomLogAlarmRepository extends JpaRepository<CustomLogAlarm, Long> {

}
