package hospital.hospital.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.BruteForceLoginAlarm;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface BruteForceLoginAlarmRepository extends JpaRepository<BruteForceLoginAlarm, Long> {
    public Page<BruteForceLoginAlarm> findAllByOrderByDateDesc(Pageable pageable);
}
