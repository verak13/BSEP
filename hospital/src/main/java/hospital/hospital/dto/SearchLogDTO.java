package hospital.hospital.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import hospital.hospital.enums.LogSeverity;

public class SearchLogDTO {
	
//	@Past
	private Date from;
	
//	@Past
	private Date to;
	
//    @Pattern(regexp="^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$", message="Field must be ip address.")
	private String ip;
    
	private String source;
	
//    @Pattern(regexp="^$|[A-Z]+$", message="Field must include only uppercase characters.")
	private String type;
    
//	@Pattern(regexp = "^TRACE$|^DEBUG$|^INFO$|^WARN$|^ERROR$|^FATAL$", message = "input not allowed")
	private String severity;
	
//	@Email(message="Email must be valid.")
	private String username;
	
	private String message;
	
	public SearchLogDTO() {}

	public SearchLogDTO(Date from, Date to, String ip, String source, String type, String severity,
			String username, String message) {
		super();
		this.from = from;
		this.to = to;
		this.ip = ip;
		this.source = source;
		this.type = type;
		this.severity = severity;
		this.username = username;
		this.message = message;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
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

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	

}