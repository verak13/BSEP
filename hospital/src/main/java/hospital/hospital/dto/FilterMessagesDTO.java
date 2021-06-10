package hospital.hospital.dto;

public class FilterMessagesDTO {
	
	private long patientId;
	
	public FilterMessagesDTO() {}

	public FilterMessagesDTO(long patientId) {
		super();
		this.patientId = patientId;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	
	

}
