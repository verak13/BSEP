package hospital.hospital.dto;

import javax.validation.constraints.Pattern;

public class RuleBloodPressureDTO {
	
//    @Pattern(regexp="^$|[0-9]+$", message="Must be integer.")
	private long patient;
	
//    @Pattern(regexp="^[0-9]+.[0-9]+$", message="Must be double.")
	private double systolic;
    
//    @Pattern(regexp="^[0-9]+.[0-9]+$", message="Must be double.")
	private double diastolic;
	
	public RuleBloodPressureDTO() {}

	public RuleBloodPressureDTO(int patient, double systolic, double diastolic) {
		this.patient = (long)patient;
		this.systolic = systolic;
		this.diastolic = diastolic;
	}

	public long getPatient() {
		return patient;
	}

	public void setPatient(long patient) {
		this.patient = patient;
	}

	public double getSystolic() {
		return systolic;
	}

	public void setSystolic(double systolic) {
		this.systolic = systolic;
	}

	public double getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(double diastolic) {
		this.diastolic = diastolic;
	}
}
