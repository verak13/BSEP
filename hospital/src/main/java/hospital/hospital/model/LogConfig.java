package hospital.hospital.model;

public class LogConfig {
	
	private String file;
	private long interval;
	private String regexp;
	
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
	public LogConfig(String file, long interval, String regexp) {
		super();
		this.file = file;
		this.interval = interval;
		this.regexp = regexp;
	}

	public LogConfig() {}
}
