package hospital.hospital.dto;

import javax.validation.constraints.Pattern;

public class CustomMessageRuleDTO {
	
	@Pattern(regexp="^[a-zA-Z0-9 ]+$", message="Must be text.")
	private String description;
	
	@Pattern(regexp="^$|^[0-9]+.[0-9]+$", message="Must be double.")
	private String minTemperature;
	
	@Pattern(regexp="^$|^[0-9]+.[0-9]+$", message="Must be double.")
	private String maxTemperature;
	
	@Pattern(regexp="^$|^[0-9]+.[0-9]+$", message="Must be double.")
	private String minPulse;
	
	@Pattern(regexp="^$|^[0-9]+.[0-9]+$", message="Must be double.")
	private String maxPulse;
	
	@Pattern(regexp="^$|^[0-9]+.[0-9]+$", message="Must be double.")
	private String minRespiration;
	
	@Pattern(regexp="^$|^[0-9]+.[0-9]+$", message="Must be double.")
	private String maxRespiration;
	
	@Pattern(regexp="^$|^[0-9]+.[0-9]+$", message="Must be double.")
	private String minDiastolic;
	
	@Pattern(regexp="^$|^[0-9]+.[0-9]+$", message="Must be double.")
	private String maxDiastolic;
	
	@Pattern(regexp="^$|^[0-9]+.[0-9]+$", message="Must be double.")
	private String minSystolic;
	
	@Pattern(regexp="^$|^[0-9]+.[0-9]+$", message="Must be double.")
	private String maxSystolic;
	
	public CustomMessageRuleDTO() {}

	public CustomMessageRuleDTO(String description,
			String minTemperature,
			String maxTemperature,
			String minPulse,
			String maxPulse,
			String minRespiration,
			String maxRespiration,
			String minDiastolic,
			String maxDiastolic,
			String minSystolic,
			String maxSystolic) {
		super();
		this.description = description;
		this.minTemperature = minTemperature;
		this.maxTemperature = maxTemperature;
		this.minPulse = minPulse;
		this.maxPulse = maxPulse;
		this.minRespiration = minRespiration;
		this.maxRespiration = maxRespiration;
		this.minDiastolic = minDiastolic;
		this.maxDiastolic = maxDiastolic;
		this.minSystolic = minSystolic;
		this.maxSystolic = maxSystolic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMinTemperature() {
		return minTemperature;
	}

	public void setMinTemperature(String minTemperature) {
		this.minTemperature = minTemperature;
	}

	public String getMaxTemperature() {
		return maxTemperature;
	}

	public void setMaxTemperature(String maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	public String getMinPulse() {
		return minPulse;
	}

	public void setMinPulse(String minPulse) {
		this.minPulse = minPulse;
	}

	public String getMaxPulse() {
		return maxPulse;
	}

	public void setMaxPulse(String maxPulse) {
		this.maxPulse = maxPulse;
	}

	public String getMinRespiration() {
		return minRespiration;
	}

	public void setMinRespiration(String minRespiration) {
		this.minRespiration = minRespiration;
	}

	public String getMaxRespiration() {
		return maxRespiration;
	}

	public void setMaxRespiration(String maxRespiration) {
		this.maxRespiration = maxRespiration;
	}

	public String getMinDiastolic() {
		return minDiastolic;
	}

	public void setMinDiastolic(String minDiastolic) {
		this.minDiastolic = minDiastolic;
	}

	public String getMaxDiastolic() {
		return maxDiastolic;
	}

	public void setMaxDiastolic(String maxDiastolic) {
		this.maxDiastolic = maxDiastolic;
	}

	public String getMinSystolic() {
		return minSystolic;
	}

	public void setMinSystolic(String minSystolic) {
		this.minSystolic = minSystolic;
	}

	public String getMaxSystolic() {
		return maxSystolic;
	}

	public void setMaxSystolic(String maxSystolic) {
		this.maxSystolic = maxSystolic;
	}
	
	

}
