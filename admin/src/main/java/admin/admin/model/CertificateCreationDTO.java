package admin.admin.model;

public class CertificateCreationDTO {
	
    private int requestId;

    private KeyUsageDTO keyUsageDTO;

	public int getRequestID() {
		return requestId;
	}

	public void setRequestID(int requestId) {
		this.requestId = requestId;
	}

	public KeyUsageDTO getKeyUsageDTO() {
		return keyUsageDTO;
	}

	public void setKeyUsageDTO(KeyUsageDTO keyUsageDTO) {
		this.keyUsageDTO = keyUsageDTO;
	}

	public CertificateCreationDTO(int requestId, KeyUsageDTO keyUsageDTO) {
		super();
		this.requestId = requestId;
		this.keyUsageDTO = keyUsageDTO;
	}
	
	public CertificateCreationDTO() {}

    
}
