package hospital.hospital.dto;

public class CustomMessageRuleDTODouble {
	public String description;
	public double minTemperature;
	public double maxTemperature;
	public double minPulse;
	public double maxPulse;
	public double minRespiration;
	public double maxRespiration;
	public double minDiastolic;
	public double maxDiastolic;
	public double minSystolic;
	public double maxSystolic;
	public CustomMessageRuleDTODouble(CustomMessageRuleDTO dto) {
		this.description = dto.getDescription();
		this.minTemperature = Double.parseDouble(dto.getMinTemperature());
		this.maxTemperature = Double.parseDouble(dto.getMaxTemperature());
		this.minPulse = Double.parseDouble(dto.getMinPulse());
		this.maxPulse = Double.parseDouble(dto.getMaxPulse());
		this.minRespiration = Double.parseDouble(dto.getMinRespiration());
		this.maxRespiration = Double.parseDouble(dto.getMaxRespiration());
		this.minDiastolic = Double.parseDouble(dto.getMinDiastolic());
		this.maxDiastolic = Double.parseDouble(dto.getMaxDiastolic());
		this.minSystolic = Double.parseDouble(dto.getMinSystolic());
		this.maxSystolic = Double.parseDouble(dto.getMaxSystolic());
	}

}
