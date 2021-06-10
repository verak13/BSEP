package hospital.hospital.dto;

import javax.validation.constraints.Pattern;

public class RuleBloodPressureDTO {
	
    @Pattern(regexp="^$|[0-9]+$", message="Must be integer.")
	private String patient;
	
    @Pattern(regexp="^[0-9]+.[0-9]+$", message="Must be double.")
	private String systolic;
    
    @Pattern(regexp="^[0-9]+.[0-9]+$", message="Must be double.")
	private String diastolic;
	
	public RuleBloodPressureDTO() {}

	public RuleBloodPressureDTO(String patient, String systolic, String diastolic) {
		super();
		this.patient = patient;
		this.systolic = systolic;
		this.diastolic = diastolic;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public String getSystolic() {
		return systolic;
	}

	public void setSystolic(String systolic) {
		this.systolic = systolic;
	}

	public String getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(String diastolic) {
		this.diastolic = diastolic;
	}
	
	

}
