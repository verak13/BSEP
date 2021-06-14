package hospital.hospital.model.cep.alarms;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Role(Role.Type.EVENT)
@Expires("60s")
@Entity
@Table(name="blacklisted_ip_alarm")
public class BlackListedIPAlarm  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column(name = "ip")
    private String ip;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "date")
    private Date date;

    @Transient
    private String adminEmail = "laketic.milena98@gmail.com";

    public String getAdminEmail() {
        return adminEmail;
    }

    public BlackListedIPAlarm() {};

    public BlackListedIPAlarm(String ip, String userEmail) {
        this.ip = ip;
        this.userEmail = userEmail;
        this.date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
