package hospital.hospital.model.cep;

import hospital.hospital.model.Log;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import java.io.Serializable;

@Role(Role.Type.EVENT)
@Expires("60s")
public class LogEvent implements Serializable {

    private Log log;

    public LogEvent() {}

    public LogEvent(Log log) {
        this.log = log;
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }
}
