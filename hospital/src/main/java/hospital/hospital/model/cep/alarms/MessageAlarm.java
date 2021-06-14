package hospital.hospital.model.cep.alarms;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Entity
@Table(name = "message_alarms")
@Role(Role.Type.EVENT)
@Expires("60s")
public class MessageAlarm implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private Long patientId;
	
	@Column
	private String variable;
	
	@Column
	private Double value;
	
	@Column
	private Double otherValue;
	
	@Column
	private Date date;
	
	public MessageAlarm() {}
	
	

	public MessageAlarm(Long id, Long patientId, String variable, Double value, Double otherValue, Date date) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.variable = variable;
		this.value = value;
		this.otherValue = otherValue;
		this.date = date;
	}
	
	public MessageAlarm(Long patientId, String variable) {
		super();
		this.patientId = patientId;
		this.variable = variable;
	}

	public MessageAlarm(int patientId, String variable) {
		super();
		this.patientId = (long) patientId;
		this.variable = variable;
	}

	public MessageAlarm(int patientId, String variable, Double value) {
		super();
		this.patientId = (long) patientId ;
		this.variable = variable;
		this.value = value;
	}
	public MessageAlarm(Long patientId, String variable, Double value) {
		super();
		this.patientId =  patientId ;
		this.variable = variable;
		this.value = value;
	}
	public MessageAlarm(int patientId, String variable, Double value, Double otherValue) {
		super();
		this.patientId =  (long)patientId;
		this.variable = variable;
		this.value = value;
		this.otherValue = otherValue;
	}
	public MessageAlarm(Long patientId, String variable, Double value, Double otherValue) {
		super();
		this.patientId =  patientId;
		this.variable = variable;
		this.value = value;
		this.otherValue = otherValue;
	}


	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getVariable() {
		return variable;
	}

	public void setVariable(String variable) {
		this.variable = variable;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getOtherValue() {
		return otherValue;
	}

	public void setOtherValue(Double otherValue) {
		this.otherValue = otherValue;
	}

	@Override
	public String toString() {
		return "MessageAlarm [patientId=" + patientId + ", variable=" + variable + ", value=" + value + ", otherValue="
				+ otherValue + ", date=" + date + "]";
	}


	
	
}
