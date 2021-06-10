package hospital.hospital.dto;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
//@Table(name = "messages")
public class DeviceMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Date timestamp;

    @Column
    private Long patientId;

    @Column
    private double bodyTemperature;

    @Column
    private double pulseRaye;

    @Column
    private double respirationRate;

    @Column
    private double bloodPressure;

    @Column
    private double heartRate;

    public DeviceMessage() {}

    public DeviceMessage(Long id, Date timestamp, Long patientId, double bodyTemperature, double pulseRaye,
                   double respirationRate, double bloodPressure, double heartRate) {
        super();
        this.id = id;
        this.timestamp = timestamp;
        this.patientId = patientId;
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