package hospital.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.BruteForceLoginAlarm;
import org.springframework.stereotype.Repository;

@Repository
public interface BruteForceLoginAlarmRepository extends JpaRepository<BruteForceLoginAlarm, Long> {

}
