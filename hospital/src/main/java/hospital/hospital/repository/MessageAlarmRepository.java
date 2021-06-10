package hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.MessageAlarm;

public interface MessageAlarmRepository extends JpaRepository<MessageAlarm, Long> {

}
