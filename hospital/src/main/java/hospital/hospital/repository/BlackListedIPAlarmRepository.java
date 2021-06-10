package hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.BlackListedIPAlarm;

public interface BlackListedIPAlarmRepository extends JpaRepository<BlackListedIPAlarm, Long> {

}
