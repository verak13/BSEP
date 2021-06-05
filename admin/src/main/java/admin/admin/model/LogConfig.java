package admin.admin.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LogConfig {
	
	@Pattern(regexp="[a-zA-Z]:)?(\\\\\\\\[a-zA-Z0-9._-]+)+\\\\\\\\?", message="Path must be valid.")
	@NotBlank(message="Field must not be empty")
	private String file;
	
	private long interval;
	
	@NotBlank(message="Field must not be empty")
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
