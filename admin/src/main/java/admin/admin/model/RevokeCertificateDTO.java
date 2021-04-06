package admin.admin.model;

public class RevokeCertificateDTO {

    private String subjectAlias;

    private String revocationReason;

    public RevokeCertificateDTO() {
    }

    public RevokeCertificateDTO(String subjectAlias, String revocationReason) {
        this.subjectAlias = subjectAlias;
        this.revocationReason = revocationReason;
    }

    public String getSubjectAlias() {
        return subjectAlias;
    }

    public void setSubjectAlias(String subjectAlias) {
        this.subjectAlias = subjectAlias;
    }

    public String getRevocationReason() {
        return revocationReason;
    }

    public void setRevocationReason(String revocationReason) {
        this.revocationReason = revocationReason;
    }
}