package hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.BlackListedIPAlarm;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListedIPAlarmRepository extends JpaRepository<BlackListedIPAlarm, Long> {

}
