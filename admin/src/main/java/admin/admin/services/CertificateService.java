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
import admin.admin.model.RevokeCertificateDTO;
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
    
    private void createCRL(PrivateKey pk, X500Name issuerName) throws CRLException, IOException, OperatorCreationException {
        X509v2CRLBuilder crlBuilder = new X509v2CRLBuilder(issuerName, new Date());
        //crlBuilder.setNextUpdate(new Date(System.currentTimeMillis() + 86400 * 1000));

        JcaContentSignerBuilder contentSignerBuilder = new JcaContentSignerBuilder("SHA256WithRSA");
        contentSignerBuilder.setProvider("BC");

        X509CRLHolder crlHolder = crlBuilder.build(contentSignerBuilder.build(pk));
        JcaX509CRLConverter converter = new JcaX509CRLConverter();
        converter.setProvider("BC");

        X509CRL crl = converter.getCRL(crlHolder);

        byte[] bytes = crl.getEncoded();


        OutputStream os = new FileOutputStream("src/main/resources/adminCRLs.crl");
        os.write(bytes);
        os.close();
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

        keyStoreWriter.writeRootCA("superadmin@admin.com", keyPair.getPrivate(), createdCertificate);
        keyStoreWriter.saveKeyStore();
        
        createCRL(keyPair.getPrivate(), rootCertIssuer);
        
        System.out.println(isRevoked(createdCertificate));
        
        revokeCertificate(new RevokeCertificateDTO("superadmin@admin.com", "UNSPECIFIED"), "superadmin@admin.com");
        
        System.out.println(isRevoked(createdCertificate));
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
    
    
    
    
    
    
    
    
    
    
    
    
    public void revokeCertificate(RevokeCertificateDTO revokeCertificateDTO, String issuerAlias) throws IOException, CRLException, OperatorCreationException, CertificateEncodingException {

        File file = new File("src/main/resources/adminCRLs.crl");

        byte[] bytes = Files.readAllBytes(file.toPath());


        X509CRLHolder holder = new X509CRLHolder(bytes);
        X509v2CRLBuilder crlBuilder = new X509v2CRLBuilder(holder);

        Certificate cer = keyStoreReader.readCertificate(revokeCertificateDTO.getSubjectAlias());
        JcaX509CertificateHolder certHolder = new JcaX509CertificateHolder((X509Certificate) cer);

        crlBuilder.addCRLEntry(certHolder.getSerialNumber()/*The serial number of the revoked certificate*/, new Date() /*Revocation time*/, RevocationReason.valueOf(revokeCertificateDTO.getRevocationReason()).ordinal() /*Reason for cancellation*/);


        JcaContentSignerBuilder contentSignerBuilder = new JcaContentSignerBuilder("SHA256WithRSA");
        contentSignerBuilder.setProvider("BC");


        IssuerData issuer = keyStoreReader.readIssuerFromStore(issuerAlias);

        X509CRLHolder crlHolder = crlBuilder.build(contentSignerBuilder.build(issuer.getPrivateKey()));
        JcaX509CRLConverter converter = new JcaX509CRLConverter();
        converter.setProvider("BC");

        X509CRL crl = converter.getCRL(crlHolder);

        bytes = crl.getEncoded();


        OutputStream os = new FileOutputStream("src/main/resources/adminCRLs.crl");
        os.write(bytes);
        os.close();
    }
    
    
    public boolean isRevoked(Certificate cer) throws IOException, CertificateException, CRLException {

        CertificateFactory factory = CertificateFactory.getInstance("X.509");

        File file = new File("src/main/resources/adminCRLs.crl");

        byte[] bytes = Files.readAllBytes(file.toPath());
        X509CRL crl = (X509CRL) factory.generateCRL(new ByteArrayInputStream(bytes));

        return crl.isRevoked(cer);
    }
    
    
    public boolean isCertificateValid(Certificate[] chain) throws CertificateException, CRLException, IOException {
    	
        X509Certificate cert;
        for (int i = 0; i < chain.length; i++) {
            cert = (X509Certificate) chain[i];

            if (isRevoked(cert)) {
                return false;
            }

            Date now = new Date();

            if (now.after(cert.getNotAfter()) || now.before(cert.getNotBefore())) {
                return false;
            }

            try {
                if (i == chain.length - 1) {
                	try {
                        cert.verify(cert.getPublicKey());
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
                X509Certificate issuer = (X509Certificate) chain[i + 1];
                cert.verify(issuer.getPublicKey());
            } catch (SignatureException | InvalidKeyException e) {
                return false;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    

    
    
    
    
    
    
    public enum RevocationReason {

        UNSPECIFIED,
        KEY_COMPROMISE,
        CA_COMPROMISE,
        AFFILIATION_CHANGED,
        SUPERSEDED,
        CESSATION_OF_OPERATION,
        CERTIFICATE_HOLD,
        REMOVE_FROM_CRL,
        PRIVILEGE_WITHDRAWN,
        AA_COMPROMISE

    }

}
