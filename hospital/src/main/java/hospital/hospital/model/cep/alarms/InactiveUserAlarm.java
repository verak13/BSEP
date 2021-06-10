package hospital.hospital.model.cep.alarms;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
@Expires("60s")
public class InactiveUserAlarm extends Alarm implements Serializable {

    private String userEmail;

    private long daysInactive;

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
