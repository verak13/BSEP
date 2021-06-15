package hospital.hospital.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import hospital.hospital.model.cep.alarms.MessageAlarm;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface MessageAlarmRepository extends JpaRepository<MessageAlarm, Long> {
    public Page<MessageAlarm> findAllByOrderByDateDesc(Pageable pageable);
}
