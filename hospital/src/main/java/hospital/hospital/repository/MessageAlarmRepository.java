package hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.MessageAlarm;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageAlarmRepository extends JpaRepository<MessageAlarm, Long> {

}
