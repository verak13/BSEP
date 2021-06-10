package hospital.hospital.model.cep;


import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;
import java.time.LocalDate;

@Role(Role.Type.EVENT)
@Expires("60s")
public class UserLoginEvent implements Serializable {

    private String email;

    private LocalDate lastLogin;

    public UserLoginEvent(String email, LocalDate lastLogin) {
        this.email = email;
        this.lastLogin = lastLogin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }
}
