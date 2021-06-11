package hospital.hospital.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "errorLogCount")
    private Long errorLogCount;

    @Column(name = "loginLogCount")
    private Long loginLogCount;

    @Column(name = "loginErrorLogCount")
    private Long loginErrorLogCount;

    @Column(name = "applicationLogCount")
    private Long applicationLogCount;

    @Column(name = "totalLogCount")
    private Long totalLogCount;

    @Column(name = "alarmsTriggered")
    private Long alarmsTriggered;

    @Column(name = "bruteForceAlarmsTriggered")
    private Long bruteForceAlarmsTriggered;

    @Column(name = "ipAlarmsTriggered")
    private Long ipAlarmsTriggered;

    @Column(name = "inactiveUserAlarmsTriggered")
    private Long inactiveUserAlarmsTriggered;

    @Column(name = "errorLogAlarmsTriggered")
    private Long errorLogAlarmsTriggered;

    public Report() {};

    public Report(Long id, Long errorLogCount, Long loginLogCount, Long loginErrorLogCount,
                  Long applicationLogCount, Long totalLogCount, Long alarmsTriggered, Long bruteForceAlarmsTriggered, Long ipAlarmsTriggered,
                  Long inactiveUserAlarmsTriggered, Long errorLogAlarmsTriggered, Date date) {
        this.id = id;
        this.errorLogCount = errorLogCount;
        this.loginLogCount = loginLogCount;
        this.loginErrorLogCount = loginErrorLogCount;
        this.applicationLogCount = applicationLogCount;
        this.totalLogCount = totalLogCount;
        this.alarmsTriggered = alarmsTriggered;
        this.bruteForceAlarmsTriggered = bruteForceAlarmsTriggered;
        this.ipAlarmsTriggered = ipAlarmsTriggered;
        this.inactiveUserAlarmsTriggered = inactiveUserAlarmsTriggered;
        this.errorLogAlarmsTriggered = errorLogAlarmsTriggered;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getErrorLogCount() {
        return errorLogCount;
    }

    public void setErrorLogCount(Long errorLogCount) {
        this.errorLogCount = errorLogCount;
    }

    public Long getLoginLogCount() {
        return loginLogCount;
    }

    public void setLoginLogCount(Long loginLogCount) {
        this.loginLogCount = loginLogCount;
    }

    public Long getLoginErrorLogCount() {
        return loginErrorLogCount;
    }

    public void setLoginErrorLogCount(Long loginErrorLogCount) {
        this.loginErrorLogCount = loginErrorLogCount;
    }

    public Long getApplicationLogCount() {
        return applicationLogCount;
    }

    public void setApplicationLogCount(Long applicationLogCount) {
        this.applicationLogCount = applicationLogCount;
    }

    public Long getTotalLogCount() {
        return totalLogCount;
    }

    public void setTotalLogCount(Long totalLogCount) {
        this.totalLogCount = totalLogCount;
    }

    public Long getAlarmsTriggered() {
        return alarmsTriggered;
    }

    public void setAlarmsTriggered(Long alarmsTriggered) {
        this.alarmsTriggered = alarmsTriggered;
    }

    public Long getBruteForceAlarmsTriggered() {
        return bruteForceAlarmsTriggered;
    }

    public void setBruteForceAlarmsTriggered(Long bruteForceAlarmsTriggered) {
        this.bruteForceAlarmsTriggered = bruteForceAlarmsTriggered;
    }

    public Long getIpAlarmsTriggered() {
        return ipAlarmsTriggered;
    }

    public void setIpAlarmsTriggered(Long ipAlarmsTriggered) {
        this.ipAlarmsTriggered = ipAlarmsTriggered;
    }

    public Long getInactiveUserAlarmsTriggered() {
        return inactiveUserAlarmsTriggered;
    }

    public void setInactiveUserAlarmsTriggered(Long inactiveUserAlarmsTriggered) {
        this.inactiveUserAlarmsTriggered = inactiveUserAlarmsTriggered;
    }

    public Long getErrorLogAlarmsTriggered() {
        return errorLogAlarmsTriggered;
    }

    public void setErrorLogAlarmsTriggered(Long errorLogAlarmsTriggered) {
        this.errorLogAlarmsTriggered = errorLogAlarmsTriggered;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
