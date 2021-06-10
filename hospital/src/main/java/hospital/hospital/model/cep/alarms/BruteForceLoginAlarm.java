package hospital.hospital.model.cep.alarms;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import javax.persistence.*;
import java.io.Serializable;
@Role(Role.Type.EVENT)
@Expires("5s")
@Entity
@Table(name="brute_force_alarm")
public class BruteForceLoginAlarm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Transient
    private String adminEmail = "laketic.milena98@gmail.com";

    public String getAdminEmail() {
        return adminEmail;
    }
    @Column(name = "username")
    private String username;

    @Column(name = "attempts")
    private long attempts;

    public BruteForceLoginAlarm(String username, long attempts) {
        this.username = username;
        this.attempts = attempts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getAttempts() {
        return attempts;
    }

    public void setAttempts(long attempts) {
        this.attempts = attempts;
    }
}
