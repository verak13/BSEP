package admin.admin.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import admin.admin.keystore.KeyStoreReader;
import admin.admin.keystore.KeyStoreWriter;
import admin.admin.model.CertificateCreationDTO;
import admin.admin.model.CertificateDTO;
import admin.admin.model.CertificateRequest;
import admin.admin.model.IssuerData;
import admin.admin.model.KeyUsageDTO;
import admin.admin.model.SubjectData;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.*;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.io.pem.PemObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.ArrayUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.*;
import org.bouncycastle.cert.jcajce.JcaX509CRLConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.*;
import java.security.cert.CRLReason;
import java.security.cert.Certificate;
import java.security.cert.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CertificateService {
	
	@Autowired
    KeyStoreReader keyStoreReader;

    @Autowired
    KeyStoreWriter keyStoreWriter;
    
    @Autowired
    CertificateRequestService certificateRequestService;
    
    @PostConstruct
    public void init() {
    	Security.addProvider(new BouncyCastleProvider());
        createRootCA();
    }

    public void createAdminCertificate(CertificateCreationDTO certificateCreationDTO, String issuerAlias) throws OperatorCreationException, CertificateException {

        Certificate[] issuerCertificateChain = keyStoreReader.readCertificateChain(issuerAlias);

        String alias = certificateRequestService.findOne(certificateCreationDTO.getRequestID()).getEmail();

        Certificate certInfo = keyStoreReader.readCertificate(alias);

        KeyPair keyPair = generateKeyPair();
        SubjectData subjectData = generateSubjectData(certificateCreationDTO.getRequestID());

        subjectData.setPublicKey(keyPair.getPublic());

        //email
        IssuerData issuer = keyStoreReader.readIssuerFromStore(issuerAlias);


        X500Name issuerName = issuer.getX500name();
        PrivateKey privateKey = issuer.getPrivateKey();

        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date startDate = calendar.getTime();

        calendar.add(Calendar.YEAR, 1);
        Date endDate = calendar.getTime();
        
        BigInteger serialNum = new BigInteger(Long.toString(new SecureRandom().nextLong()));
        
        ContentSigner certContentSigner = new JcaContentSignerBuilder("SHA256WithRSAEncryption").setProvider("BC").build(keyPair.getPrivate());
        X509v3CertificateBuilder certBuilder = new JcaX509v3CertificateBuilder(issuerName, serialNum, startDate, endDate, subjectData.getX500name(), keyPair.getPublic());


        KeyUsageDTO keyUsageDTO = certificateCreationDTO.getKeyUsageDTO();
        KeyUsage k = new KeyUsage(keyUsageDTO.getcRLSign() |
                keyUsageDTO.getDataEncipherment() |
                keyUsageDTO.getDecipherOnly() |
                keyUsageDTO.getDigitalSignature() |
                keyUsageDTO.getEncipherOnly() |
                keyUsageDTO.getKeyAgreement() |
                keyUsageDTO.getKeyCertSign() | 
                keyUsageDTO.getKeyEncipherment() |
                keyUsageDTO.getNonRepudiation());

        try {
        	certBuilder.addExtension(Extension.keyUsage, false, k);
            if (keyUsageDTO.iscRLSign())
            	certBuilder.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));
        } catch (CertIOException e) {
            e.printStackTrace();
        }

        X509CertificateHolder certHolder = certBuilder.build(certContentSigner);

        JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
        certConverter = certConverter.setProvider("BC");

        X509Certificate createdCertificate = certConverter.getCertificate(certHolder);


        Certificate[] newCertificateChain = ArrayUtils.insert(0, issuerCertificateChain, createdCertificate);
        keyStoreWriter.write(alias, keyPair.getPrivate(), newCertificateChain);
        keyStoreWriter.saveKeyStore();
    }
    
    public void createRootCA() {
        try {
            if (!keyStoreWriter.loadKeyStore())
            	createRootCertificate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void createRootCertificate() throws OperatorCreationException, CertificateException, CRLException, IOException {
        keyStoreWriter.createKeyStore("password");

        KeyPair keyPair = generateKeyPair();
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date startDate = calendar.getTime();

        calendar.add(Calendar.YEAR, 1);
        Date endDate = calendar.getTime();
        
        BigInteger rootSerialNum = new BigInteger(Long.toString(new SecureRandom().nextLong()));
        
        
        X500Name rootCertIssuer = new X500Name("CN=root-cert");
        X500Name rootCertSubject = rootCertIssuer;
        ContentSigner rootCertContentSigner = new JcaContentSignerBuilder("SHA256WithRSAEncryption").setProvider("BC").build(keyPair.getPrivate());
        X509v3CertificateBuilder rootCertBuilder = new JcaX509v3CertificateBuilder(rootCertIssuer, rootSerialNum, startDate, endDate, rootCertSubject, keyPair.getPublic());

        KeyUsageDTO keyUsageDTO = new KeyUsageDTO(true, true, true, true, true, true, true, true, true);
        KeyUsage k = new KeyUsage(keyUsageDTO.getcRLSign() |
                keyUsageDTO.getDataEncipherment() |
                keyUsageDTO.getDecipherOnly() |
                keyUsageDTO.getDigitalSignature() |
                keyUsageDTO.getEncipherOnly() |
                keyUsageDTO.getKeyAgreement() |
                keyUsageDTO.getKeyCertSign() |
                keyUsageDTO.getKeyEncipherment() |
                keyUsageDTO.getNonRepudiation());

        try {
        	rootCertBuilder.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));
        	rootCertBuilder.addExtension(Extension.keyUsage, false, k);
        } catch (CertIOException e) {
            e.printStackTrace();
        }
        X509CertificateHolder certHolder = rootCertBuilder.build(rootCertContentSigner);

        JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
        certConverter = certConverter.setProvider("BC");

        X509Certificate createdCertificate = certConverter.getCertificate(certHolder);

        keyStoreWriter.writeRootCA("super.admin@admin.com", keyPair.getPrivate(), createdCertificate);
        keyStoreWriter.saveKeyStore();
    }
    
    
    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);

            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    private SubjectData generateSubjectData(int subjectID) {
        CertificateRequest cerRequestInfo = certificateRequestService.findOne(subjectID);

        if (cerRequestInfo == null)
            return null;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDateTime startDate = LocalDateTime.now();
        startDate.format(dtf);

        LocalDateTime endDate = startDate.plusYears(1);
        endDate.format(dtf);

        Calendar curCal = new GregorianCalendar(TimeZone.getDefault());
        curCal.setTimeInMillis(System.currentTimeMillis());

        String serialNumber = curCal.getTimeInMillis() + "";

        // klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, cerRequestInfo.getCommonName()); 
        builder.addRDN(BCStyle.O, cerRequestInfo.getOrganization());
        builder.addRDN(BCStyle.OU, cerRequestInfo.getOrganizationUnitName());
        builder.addRDN(BCStyle.C, cerRequestInfo.getCountryName());
        builder.addRDN(BCStyle.E, cerRequestInfo.getEmail());
        builder.addRDN(BCStyle.ST, cerRequestInfo.getStateName());
        builder.addRDN(BCStyle.L, cerRequestInfo.getLocalityName());

        // UID (USER ID) je ID korisnika
        builder.addRDN(BCStyle.UID, String.valueOf(cerRequestInfo.getUserId()));

        return new SubjectData(builder.build(), serialNumber, startDate, endDate);

    }
    

    
    
    
    
    
    
    

}
