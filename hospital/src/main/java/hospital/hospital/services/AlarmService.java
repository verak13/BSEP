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

import java.util.HashMap;

@Service
public class AlarmService {

	public static final String BRUTE_FORCE_ALARM = "BRUTE_FORCE_ALARM";
	public static final String INACTIVE_USER_ALARM = "INACTIVE_USER_ALARM";
	public static final String BLACKLISTED_IP_ALARM = "BLACKLISTED_IP_ALARM";
	public static final String ERROR_LOG_ALARM = "ERROR_LOG_ALARM";
	public static final String TOTAL = "TOTAL";
	
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

	public HashMap<String, Long> countAlarms() {

		HashMap<String, Long> map = new HashMap<>();
		map.put(ERROR_LOG_ALARM, errorLogAlarmRepository.count());
		map.put(BRUTE_FORCE_ALARM, bruteForceLoginAlarmRepository.count());
		map.put(INACTIVE_USER_ALARM, inactiveUserAlarmRepository.count());
		map.put(BLACKLISTED_IP_ALARM, blackListedIPAlarmRepository.count());
		map.put(TOTAL, map.get(ERROR_LOG_ALARM) + map.get(BRUTE_FORCE_ALARM) + map.get(INACTIVE_USER_ALARM) + map.get(BLACKLISTED_IP_ALARM));

		return map;
	}

}
