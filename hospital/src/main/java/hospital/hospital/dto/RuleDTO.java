package hospital.hospital.dto;

import javax.validation.constraints.Pattern;

public class RuleDTO {
	
    @Pattern(regexp="^$|[0-9]+$", message="Must be integer.")
	private String patient;
    
    @Pattern(regexp="^[0-9]+.[0-9]+$", message="Must be double.")
	private String value;
	
	public RuleDTO() {}
	
	public RuleDTO(String patient, String value) {
		super();
		this.patient = patient;
		this.value = value;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
