package hospital.hospital.model.cep.alarms;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Role(Role.Type.EVENT)
@Expires("5s")
@Entity
@Table(name="inactive_user_alarm")
public class InactiveUserAlarm  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "userEmail")
    private String userEmail;

    @Column(name = "daysInactive")
    private long daysInactive;

    @Column(name = "date")
    private Date date;

    @Transient
    private String adminEmail = "laketic.milena98@gmail.com";

    public String getAdminEmail() {
        return adminEmail;
    }

    public InactiveUserAlarm() {
    }

    public InactiveUserAlarm(String userEmail, long daysInactive) {
        this.userEmail = userEmail;
        this.daysInactive = daysInactive;
        this.date = new Date();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getDaysInactive() {
        return daysInactive;
    }

    public void setDaysInactive(long daysInactive) {
        this.daysInactive = daysInactive;
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

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}
