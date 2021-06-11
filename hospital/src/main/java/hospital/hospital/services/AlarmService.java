package hospital.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hospital.hospital.model.cep.alarms.BlackListedIPAlarm;
import hospital.hospital.model.cep.alarms.BruteForceLoginAlarm;
import hospital.hospital.model.cep.alarms.ErrorLogAlarm;
import hospital.hospital.model.cep.alarms.InactiveUserAlarm;
import hospital.hospital.repository.BlackListedIPAlarmRepository;
import hospital.hospital.repository.BruteForceLoginAlarmRepository;
import hospital.hospital.repository.ErrorLogAlarmRepository;
import hospital.hospital.repository.InactiveUserAlarmRepository;

@Service
public class AlarmService {
	
	@Autowired
	BlackListedIPAlarmRepository blackListedIPAlarmRepository;
	
	@Autowired
	BruteForceLoginAlarmRepository bruteForceLoginAlarmRepository;
	
	@Autowired
	ErrorLogAlarmRepository errorLogAlarmRepository;
	
	@Autowired
	InactiveUserAlarmRepository inactiveUserAlarmRepository;
	
	public Page<BlackListedIPAlarm> findAllBlackListedIPAlarms(Pageable pageable) {
		return blackListedIPAlarmRepository.findAll(pageable);
	}
	
	public Page<BruteForceLoginAlarm> findAllBruteForceLoginAlarms(Pageable pageable) {
		return bruteForceLoginAlarmRepository.findAll(pageable);
	}
	
	public Page<ErrorLogAlarm> findAllErrorLogAlarms(Pageable pageable) {
		return errorLogAlarmRepository.findAll(pageable);
	}
	
	public Page<InactiveUserAlarm> findAllInactiveUserAlarms(Pageable pageable) {
		return inactiveUserAlarmRepository.findAll(pageable);
	}
	

}