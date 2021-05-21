package admin.admin.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class RevokeCertificateDTO {

	@NotBlank
	@Email(message="Email must be valid")
    private String alias;

	@NotBlank
	@Pattern(regexp = "^UNSPECIFIED$|^KEY_COMPROMISE$|^CA_COMPROMISE$|^AFFILIATION_CHANGED$|^SUPERSEDED$|^CESSATION_OF_OPERATION$"
			+ "|^CERTIFICATE_HOLD$|^REMOVE_FROM_CRL$|^PRIVILEGE_WITHDRAWN$|^AA_COMPROMISE$", message = "input not allowed")
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