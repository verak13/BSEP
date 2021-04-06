package hospital.hospital.dto;

import hospital.hospital.model.CertificateRequest;

import java.io.Serializable;

public class CertificateRequestDTO implements Serializable  {

    public CertificateRequest csr;

    private byte[] encodedHash;

    public CertificateRequestDTO() {};

    public CertificateRequestDTO(CertificateRequest csr) {
        this.csr = csr;
    }

    public CertificateRequest getCsr() {
        return csr;
    }

    public void setCsr(CertificateRequest csr) {
        this.csr = csr;
    }

    public byte[] getEncodedHash() {
        return encodedHash;
    }

    public void setEncodedHash(byte[] hash) {
        this.encodedHash = hash;
    }
}
