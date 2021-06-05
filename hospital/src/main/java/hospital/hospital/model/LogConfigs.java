package hospital.hospital.model;

import java.util.List;

public class LogConfigs {
	
	private List<LogConfig> logConfigs;
	
	public LogConfigs() {}

	public LogConfigs(List<LogConfig> logConfigs) {
		super();
		this.logConfigs = logConfigs;
	}

	public List<LogConfig> getLogConfigs() {
		return logConfigs;
	}

	public void setLogConfigs(List<LogConfig> logConfigs) {
		this.logConfigs = logConfigs;
	}
	
	

}
