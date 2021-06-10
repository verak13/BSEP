package hospital.hospital.model;

public class LogConfig {
	
	private String file;
	private long interval;
	private String regexp;
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
	public LogConfig(String file, long interval, String regexp, Long hospitalId) {
		super();
		this.file = file;
		this.interval = interval;
		this.regexp = regexp;
		this.hospitalId = hospitalId;
	}

	public LogConfig() {}
}
