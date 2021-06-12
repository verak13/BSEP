package hospital.hospital.dto;

import javax.validation.constraints.Pattern;

public class RuleDTO {
	
//    @Pattern(regexp="^$|[0-9]+$", message="Must be integer.")
	private Long patient;
    
//    @Pattern(regexp="^[0-9]+.[0-9]+$", message="Must be double.")
	private Double value;
	
	public RuleDTO() {}
	
	public RuleDTO(Long patient, Double value) {
		super();
		this.patient = patient;
		this.value = value;
	}

	public Long getPatient() {
		return patient;
	}

	public void setPatient(Long patient) {
		this.patient = patient;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	

}
