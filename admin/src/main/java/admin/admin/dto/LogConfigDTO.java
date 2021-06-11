package admin.admin.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LogConfigDTO {
	
	@NotBlank
	@Pattern(regexp = "^SIMULATOR1$|^SIMULATOR2$|^SIMULATOR3$", message = "input not allowed")
	private String file;
	
	@NotBlank
	@Min(1)
	private long interval;
	
	@NotBlank
	private String regexp;
	
	@NotBlank
	@Min(1)
	private Long hospitalId;
	
	
	public Long getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Long hospitalId) {
		this.hospitalId = hospitalId;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public long getInterval() {
		return interval;
	}
	public void setInterval(long interval) {
		this.interval = interval;
	}
	public String getRegexp() {
		return regexp;
	}
	public void setRegexp(String regexp) {
		this.regexp = regexp;
	}
	public LogConfigDTO(String file, long interval, String regexp, Long hospitalId) {
		super();
		this.file = file;
		this.interval = interval;
		this.regexp = regexp;
		this.hospitalId = hospitalId;
	}

	public LogConfigDTO() {}
}
