package hospital.hospital.dto;


public class LogRuleDTO {

    private String name;

    private TypeList type;

    private TypeList severity;

    private int times;

    private int seconds;

    private TypeList precTypes;

    private int precSec;

    private TypeList succTypes;

    private int succSec;

    private String msg;

    public LogRuleDTO(String name, TypeList type, TypeList severity, int times, int seconds, TypeList precTypes, int precSec, TypeList succTypes, int succSec, String msg) {
        this.name = name;
        this.type = type;
        this.severity = severity;
        this.times = times;
        this.seconds = seconds;
        this.precTypes = precTypes;
        this.precSec = precSec;
        this.succTypes = succTypes;
        this.succSec = succSec;
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeList getType() {
        return type;
    }

    public void setType(TypeList type) {
        this.type = type;
    }

    public TypeList getSeverity() {
        return severity;
    }

    public void setSeverity(TypeList severity) {
        this.severity = severity;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TypeList getPrecTypes() {
        return precTypes;
    }

    public void setPrecTypes(TypeList precTypes) {
        this.precTypes = precTypes;
    }

    public int getPrecSec() {
        return precSec;
    }

    public void setPrecSec(int precSec) {
        this.precSec = precSec;
    }

    public TypeList getSuccTypes() {
        return succTypes;
    }

    public void setSuccTypes(TypeList succTypes) {
        this.succTypes = succTypes;
    }

    public int getSuccSec() {
        return succSec;
    }

    public void setSuccSec(int succSec) {
        this.succSec = succSec;
    }
}
