package hospital.hospital.services;

import hospital.hospital.dto.CertificateRequestDTO;
import hospital.hospital.model.CertificateRequest;
import hospital.hospital.model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.StandardCharsets;
import java.security.*;

import hospital.hospital.helper.RSA;

@Service
public class CertificateRequestService {


    public boolean createCSR(CertificateRequest csr) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!csr.getEmail().equals(user.getEmail())) {
            return false;
        }
        RSA rsa = new RSA();
        KeyPair keyPair = rsa.generateKeys();

        String publicKeyEncoded = rsa.encodePublicKey(keyPair.getPublic());
        csr.setPublicKey(publicKeyEncoded);

        byte[] hashedCSR = hash(csr.serializeObject().getBytes(StandardCharsets.UTF_8));
        byte[] cipher = signCSR(hashedCSR, rsa, keyPair.getPrivate());

        CertificateRequestDTO request = new CertificateRequestDTO(csr);
        request.setEncodedHash(cipher);

        return sendRequest(request);
    }

    public boolean sendRequest(CertificateRequestDTO csr) {
        String urlCA = "http://localhost:8080//certificate-request/send-certificate-request";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<CertificateRequestDTO> request = new HttpEntity<>(csr);
        ResponseEntity<?> response = restTemplate.exchange(urlCA,
                HttpMethod.POST, request, CertificateRequestDTO.class);

        return response.getStatusCode() == HttpStatus.OK;
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

    public byte[] signCSR(byte[] hash, RSA rsa, PrivateKey privateKey) {
        return rsa.encrypt(hash, privateKey);
    }
}
