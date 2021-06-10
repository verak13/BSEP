package admin.admin.services;

import admin.admin.dto.CertificateRequestDTO;
import admin.admin.helper.RSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import admin.admin.keystore.KeyStoreReader;
import admin.admin.model.CertificateRequest;
import admin.admin.repository.CertificateRequestRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.DatatypeConverter;
import java.security.*;

@Service
public class CertificateRequestService {

	@Autowired
	CertificateRequestRepository certificateRequestRepository;

	@Autowired
	KeyStoreReader keyStoreReader;

	public List<CertificateRequest> findAll() {
		return certificateRequestRepository.findAll();
	}

	public CertificateRequest findOne(int id) {
		return certificateRequestRepository.findById(id).orElse(null);
	}

	public CertificateRequest saveOne(CertificateRequest entity) {
		CertificateRequest request = certificateRequestRepository.findByEmail(entity.getEmail());

		if (request == null) {
			return certificateRequestRepository.save(entity);
		}
		else
			return null;
		

	}

	public boolean delete(int id) {
		CertificateRequest request = findOne(id);
		if (request != null) {
			certificateRequestRepository.delete(request);
			return true;
		}
		return false;

	}

	public boolean createCertificateRequest(CertificateRequestDTO encryptedCSR) throws IOException {
		RSA rsa = new RSA();
		PublicKey publicKey = decodePublicKey(encryptedCSR.getCsr().getPublicKey());
		byte[] decryptedHash = rsa.decrypt(encryptedCSR.getEncodedHash(), publicKey);
		byte[] hashed = hash((encryptedCSR.getCsr().serializeObject()).getBytes(StandardCharsets.UTF_8));
		if(!checkHash(hashed, decryptedHash)) {
			return false;
		}
		try {
			saveOne(encryptedCSR.getCsr());
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private PublicKey decodePublicKey(String encodedKey) {

		KeyFactory keyFactory = null;
		try {
			keyFactory = KeyFactory.getInstance("RSA");
			byte[] decoded = DatatypeConverter.parseBase64Binary(encodedKey);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decoded);

			return (RSAPublicKey) keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public byte[] hash(byte[] data) {
		// Kao hes funkcija koristi SHA-256
		try {
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			return sha256.digest(data);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean checkHash(byte[] receivedHash, byte[] hashed) {
		return Arrays.equals(receivedHash, hashed);
	}

}