package hospital.hospital.model;
import java.io.Serializable;

public class CertificateRequest implements Serializable {
	
    private int id;

	private String publicKeyEncoded;

    private String commonName;
    
    private String countryName;
    
    private String organization;
    
    private String organizationUnitName;
    
    private String stateName;
    
    private String localityName;
    
    private String email;
    
    private int userId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrganizationUnitName() {
		return organizationUnitName;
	}

	public void setOrganizationUnitName(String organizationUnitName) {
		this.organizationUnitName = organizationUnitName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getLocalityName() {
		return localityName;
	}

	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPublicKey() {
		return publicKeyEncoded;
	}

	public void setPublicKey(String publicKey) {
		this.publicKeyEncoded = publicKey;
	}

	public CertificateRequest(int id, String commonName, String countryName, String organization,
							  String organizationUnitName, String stateName, String localityName, String email, String publicKey) {
		super();
		this.id = id;
		this.commonName = commonName;
		this.countryName = countryName;
		this.organization = organization;
		this.organizationUnitName = organizationUnitName;
		this.stateName = stateName;
		this.localityName = localityName;
		this.email = email;
		this.publicKeyEncoded = publicKey;
	}
	
	public CertificateRequest(String commonName, String countryName, String organization,
			String organizationUnitName, String stateName, String localityName, String email, int userId) {
		super();
		this.commonName = commonName;
		this.countryName = countryName;
		this.organization = organization;
		this.organizationUnitName = organizationUnitName;
		this.stateName = stateName;
		this.localityName = localityName;
		this.email = email;
		this.userId = userId;
	}
	
	public CertificateRequest() {}

	public String serializeObject() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id);
		sb.append(this.getEmail());
		sb.append(this.getUserId());
		sb.append(this.getCommonName());
		sb.append(this.getCountryName());
		sb.append(this.getLocalityName());
		sb.append(this.getOrganization());
		sb.append(this.getOrganizationUnitName());
		sb.append(this.getStateName());

		return sb.toString();
	}

}
