package hospital.hospital.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.BlackListedIPAlarm;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface BlackListedIPAlarmRepository extends JpaRepository<BlackListedIPAlarm, Long> {
    public Page<BlackListedIPAlarm> findAllByOrderByDateDesc(Pageable pageable);
}
