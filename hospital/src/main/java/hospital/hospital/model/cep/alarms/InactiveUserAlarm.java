package hospital.hospital.model.cep.alarms;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import javax.persistence.*;
import java.io.Serializable;

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

    @Transient
    private String adminEmail = "laketic.milena98@gmail.com";

    public String getAdminEmail() {
        return adminEmail;
    }

    public InactiveUserAlarm(String userEmail, long daysInactive) {
        this.userEmail = userEmail;
        this.daysInactive = daysInactive;
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
}
