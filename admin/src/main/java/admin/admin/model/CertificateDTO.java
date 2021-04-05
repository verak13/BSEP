package admin.admin.model;

import java.util.ArrayList;
import java.util.List;

public class CertificateDTO {
	
    private String commonName;

    private String email;

    private boolean isCA;

    private List<CertificateDTO> certificates;

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

	public List<CertificateDTO> getCertificates() {
		return certificates;
	}

	public void setCertificates(List<CertificateDTO> certificates) {
		this.certificates = certificates;
	}

	public CertificateDTO(String commonName, String email, boolean isCA) {
		super();
		this.commonName = commonName;
		this.email = email;
		this.isCA = isCA;
		this.certificates = new ArrayList<>();
	}
	
	public CertificateDTO() {
		 this.certificates = new ArrayList<>();
	}
    
    

}
