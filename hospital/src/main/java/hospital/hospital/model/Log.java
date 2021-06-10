package hospital.hospital.model;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import hospital.hospital.enums.LogSeverity;


@Document(collection = "logs")
public class Log {
	
	@Id
    private BigInteger id;
	
	@Indexed
	private Date timestamp;
	
	@Indexed
	private String source;
	
	@Indexed
	private String type;
	
	@Indexed
	private LogSeverity severity;
	
	@Indexed
	private String ip;
	
	@Indexed
	private String message;
	
	@Indexed
	private String username;
	
	
	public Log() {}

	public Log(BigInteger id, Date timestamp, String source, String type, LogSeverity severity, String ip, String message,
			String username) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.source = source;
		this.type = type;
		this.severity = severity;
		this.ip = ip;
		this.message = message;
		this.username = username;
	}

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LogSeverity getSeverity() {
		return severity;
	}

	public void setSeverity(LogSeverity severity) {
		this.severity = severity;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", timestamp=" + timestamp + ", source=" + source + ", type=" + type + ", severity="
				+ severity + ", ip=" + ip + ", message=" + message + ", username=" + username + "]";
	}
	
	
	

}
