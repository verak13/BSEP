package admin.admin.services;
import org.apache.commons.codec.DecoderException;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;

import admin.admin.keystore.KeyStoreReader;
import admin.admin.model.CertificateRequest;
import admin.admin.repository.CertificateRequestRepository;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
    	CertificateRequest request = certificateRequestRepository.findByUserId(entity.getUserId());

    	if (request != null)
    		return certificateRequestRepository.save(entity);
    	else
    		return null;

    }

    public boolean delete(int id) {
    	CertificateRequest cerRequestInfo = findOne(id);
        if (cerRequestInfo != null) {
        	certificateRequestRepository.delete(cerRequestInfo);
            return true;
        }
        return false;

    }
    
    
    
    
    
    
    public boolean createCertificateRequest(byte[] encryptedCSR) throws IOException {

	    return true;
	}
	
	
}