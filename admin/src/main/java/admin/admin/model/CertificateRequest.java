package admin.admin.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "requests")
public class CertificateRequest implements Serializable  {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "commonName", unique = false, nullable = false)
    private String commonName;
    
    @Column(name = "countryName", unique = false, nullable = false)
    private String countryName;
    
    @Column(name = "organization", unique = false, nullable = false)
    private String organization;
    
    @Column(name = "organizationUnitName", unique = false, nullable = false)
    private String organizationUnitName;
    
    @Column(name = "stateName", unique = false, nullable = false)
    private String stateName;
    
    @Column(name = "localityName", unique = false, nullable = false)
    private String localityName;
    
    @Column(name = "email", unique = false, nullable = false)
    private String email;
    
    @Column(name = "userId", unique = false, nullable = false)
    private int userId;

    @Transient
    private String publicKeyEncoded;

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
