package admin.admin.model;

import java.util.ArrayList;
import java.util.List;

public class CertificateDTO {
	
    private String commonName;

    private String email;

    private boolean isCA;
    
	private boolean revoked;

    
    public CertificateDTO(String commonName, String email, boolean isCA, Boolean revoked, String revocationReason) {
		super();
		this.commonName = commonName;
		this.email = email;
		this.isCA = isCA;
		this.revoked = revoked;
	}

	public Boolean getRevoked() {
		return revoked;
	}

	public void setRevoked(Boolean revoked) {
		this.revoked = revoked;
	}

	/*public String getRevocationReason() {
		return revocationReason;
	}

	public void setRevocationReason(String revocationReason) {
		this.revocationReason = revocationReason;
	}*/


	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isCA() {
		return isCA;
	}

	public void setCA(boolean isCA) {
		this.isCA = isCA;
	}

	public CertificateDTO(String commonName, String email, boolean isCA) {
		super();
		this.commonName = commonName;
		this.email = email;
		this.isCA = isCA;
	}
	
	public CertificateDTO() {
		 this.revoked = false;
	}
    
    

}
