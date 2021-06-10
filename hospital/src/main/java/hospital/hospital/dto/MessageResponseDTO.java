package hospital.hospital.dto;

import java.util.Date;

import javax.persistence.Column;

public class MessageResponseDTO {
	
	private Long id;
	
	private Date timestamp;
	
	private Long patientId;
	
	private String patient;
	
	private double bodyTemperature;
	
	private double pulseRaye;
	
	private double respirationRate;
	
	private double bloodPressure;
	
	private double heartRate;
	
	public MessageResponseDTO() {}

	public MessageResponseDTO(Long id, Date timestamp, Long patientId, String patient, double bodyTemperature,
			double pulseRaye, double respirationRate, double bloodPressure, double heartRate) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.patientId = patientId;
		this.patient = patient;
		this.bodyTemperature = bodyTemperature;
		this.pulseRaye = pulseRaye;
		this.respirationRate = respirationRate;
		this.bloodPressure = bloodPressure;
		this.heartRate = heartRate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	public double getBodyTemperature() {
		return bodyTemperature;
	}

	public void setBodyTemperature(double bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	public double getPulseRaye() {
		return pulseRaye;
	}

	public void setPulseRaye(double pulseRaye) {
		this.pulseRaye = pulseRaye;
	}

	public double getRespirationRate() {
		return respirationRate;
	}

	public void setRespirationRate(double respirationRate) {
		this.respirationRate = respirationRate;
	}

	public double getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(double bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public double getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(double heartRate) {
		this.heartRate = heartRate;
	}
	
	

}
