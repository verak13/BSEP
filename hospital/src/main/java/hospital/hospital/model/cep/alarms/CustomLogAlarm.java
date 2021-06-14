package hospital.hospital.model.cep.alarms;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Role(Role.Type.EVENT)
@Expires("60s")
@Entity
@Table(name="custom_log_alarm")
public class CustomLogAlarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "errorMsg")
    private String errorMsg;

    @Column(name = "ruleName")
    private String ruleName;

    @Transient
    private boolean sentEmail;

    @Transient
    private String adminEmail = "laketic.milena98@gmail.com";

    public CustomLogAlarm() {
    }

    public CustomLogAlarm(Date date, String errorMsg, String ruleName) {
        this.date = date;
        this.errorMsg = errorMsg;
        this.ruleName = ruleName;
        this.sentEmail = false;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public boolean isSentEmail() {
        return sentEmail;
    }

    public void setSentEmail(boolean sentEmail) {
        this.sentEmail = sentEmail;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}
