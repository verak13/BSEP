package hospital.hospital.model.cep.alarms;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

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
    private LocalDate date;

    @Transient
    private String adminEmail = "laketic.milena98@gmail.com";

    public String getAdminEmail() {
        return adminEmail;
    }

    public BlackListedIPAlarm(String ip, String userEmail, LocalDate date) {
        this.ip = ip;
        this.userEmail = userEmail;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
