package hospital.hospital.services;

import hospital.hospital.model.Log;
import hospital.hospital.model.Report;
import hospital.hospital.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private LogService logService;

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public Report generate() {
        HashMap<String, Long> map = alarmService.countAlarms();

        Report r = new Report();
        r.setAlarmsTriggered(map.get(AlarmService.TOTAL));
        r.setBruteForceAlarmsTriggered(map.get(AlarmService.BRUTE_FORCE_ALARM));
        r.setInactiveUserAlarmsTriggered(map.get(AlarmService.INACTIVE_USER_ALARM));
        r.setErrorLogAlarmsTriggered(map.get(AlarmService.ERROR_LOG_ALARM));
        r.setIpAlarmsTriggered(map.get(AlarmService.BLACKLISTED_IP_ALARM));

        r.setDate(new Date());

        /*map = logService.countLogs();

        r.setTotalLogCount(map.get(LogService.TOTAL));
        r.setApplicationLogCount(map.get(LogService.APPLICATION_LOG));
        r.setErrorLogCount(map.get(LogService.ERROR_LOG));
        r.setLoginLogCount(map.get(LogService.LOGIN_LOG));
        r.setLoginErrorLogCount(map.get(LogService.LOGIN_ERROR_LOG));*/

        return reportRepository.save(r);
    }
}
