package hospital.hospital.model;
import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CertificateRequest implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	//@NotBlank(message="Field must not be empty")
    //@Size(min=2, max=20)
    //@Pattern(regexp="^$|[a-zA-Z ]+$", message="Field must not include special characters.")
	private String publicKeyEncoded;

	@NotBlank(message="Field must not be empty")
    @Size(min=2, max=20)
    @Pattern(regexp="^$|[a-zA-Z ]+$", message="Field must not include special characters.")
    private String commonName;
    
	@NotBlank(message="Field must not be empty")
    @Size(min=2, max=20)
    @Pattern(regexp="^$|[a-zA-Z ]+$", message="Field must not include special characters.")
    private String countryName;
    
	@NotBlank(message="Field must not be empty")
    @Size(min=2, max=20)
    @Pattern(regexp="^$|[a-zA-Z ]+$", message="Field must not include special characters.")
    private String organization;
    
	@NotBlank(message="Field must not be empty")
    @Size(min=2, max=20)
    @Pattern(regexp="^$|[a-zA-Z ]+$", message="Field must not include special characters.")
    private String organizationUnitName;
    
	@NotBlank(message="Field must not be empty")
    @Size(min=2, max=20)
    @Pattern(regexp="^$|[a-zA-Z ]+$", message="Field must not include special characters.")
    private String stateName;
    
	@NotBlank(message="Field must not be empty")
    @Size(min=2, max=20)
    @Pattern(regexp="^$|[a-zA-Z ]+$", message="Field must not include special characters.")
    private String localityName;
    
	@NotBlank(message="Field must not be empty")
    @Size(min=2, max=20)
    @Email(message="Email must be valid.")
    private String email;
    
    //private Integer userId;

	/*public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}*/

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
			String organizationUnitName, String stateName, String localityName, String email/*, int userId*/) {
		super();
		this.commonName = commonName;
		this.countryName = countryName;
		this.organization = organization;
		this.organizationUnitName = organizationUnitName;
		this.stateName = stateName;
		this.localityName = localityName;
		this.email = email;
		//this.userId = userId;
	}
	
	public CertificateRequest() {}

	public String serializeObject() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.id);
		sb.append(this.getEmail());
		//sb.append(this.getUserId());
		sb.append(this.getCommonName());
		sb.append(this.getCountryName());
		sb.append(this.getLocalityName());
		sb.append(this.getOrganization());
		sb.append(this.getOrganizationUnitName());
		sb.append(this.getStateName());

		return sb.toString();
	}

}
