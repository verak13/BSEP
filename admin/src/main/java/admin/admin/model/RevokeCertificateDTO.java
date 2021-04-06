package admin.admin.model;

public class RevokeCertificateDTO {

    private String alias;

    private String reason;

    public RevokeCertificateDTO() {
    }

    public RevokeCertificateDTO(String alias, String reason) {
        this.alias = alias;
        this.reason = reason;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}