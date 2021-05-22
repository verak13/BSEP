package admin.admin.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import admin.admin.keystore.KeyStoreReader;
import admin.admin.keystore.KeyStoreWriter;
import admin.admin.model.CertificateDTO;
import admin.admin.model.CertificateRequest;
import admin.admin.model.IssuerData;
import admin.admin.model.CreateCertificateDTO;
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
		try {
			createRootCertificate();
		} catch (OperatorCreationException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (CRLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createRootCertificate()
			throws OperatorCreationException, CertificateException, CRLException, IOException {
		
		try {
			if (!keyStoreWriter.keyStoreExists()) {
				keyStoreWriter.createKeyStore("password");

				KeyPair keyPair = generateKeyPair();

				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -1);
				Date startDate = calendar.getTime();

				calendar.add(Calendar.YEAR, 1);
				Date endDate = calendar.getTime();

				BigInteger rootSerialNum = new BigInteger(Long.toString(new SecureRandom().nextLong()));

				X500Name rootCertIssuer = new X500Name("CN=root-certificate, E=superadmin@admin.com");
				X500Name rootCertSubject = rootCertIssuer;
				ContentSigner rootCertContentSigner = new JcaContentSignerBuilder("SHA256WithRSAEncryption")
						.setProvider("BC").build(keyPair.getPrivate());
				X509v3CertificateBuilder rootCertBuilder = new JcaX509v3CertificateBuilder(rootCertIssuer,
						rootSerialNum, startDate, endDate, rootCertSubject, keyPair.getPublic());

				CreateCertificateDTO keyUsageDTO = new CreateCertificateDTO(true, true, true, true, true, true, true, true, true);
				KeyUsage k = new KeyUsage(keyUsageDTO.getcRLSign() | keyUsageDTO.getDataEncipherment()
						| keyUsageDTO.getDecipherOnly() | keyUsageDTO.getDigitalSignature()
						| keyUsageDTO.getEncipherOnly() | keyUsageDTO.getKeyAgreement() | keyUsageDTO.getKeyCertSign()
						| keyUsageDTO.getKeyEncipherment() | keyUsageDTO.getNonRepudiation());

				try {
					rootCertBuilder.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));
					rootCertBuilder.addExtension(Extension.keyUsage, false, k);
				} catch (CertIOException e) {
					e.printStackTrace();
				}

				X509CertificateHolder certificateHolder = rootCertBuilder.build(rootCertContentSigner);

				JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
				certConverter = certConverter.setProvider("BC");

				X509Certificate createdCertificate = certConverter.getCertificate(certificateHolder);

				keyStoreWriter.writeRootCA("superadmin@admin.com", keyPair.getPrivate(), createdCertificate);
				keyStoreWriter.saveKeyStore();

				createCRL(keyPair.getPrivate(), rootCertIssuer);

				System.out.println(isRevoked(createdCertificate));

				//revokeCertificate(new RevokeCertificateDTO("superadmin@admin.com", "UNSPECIFIED"),
				//		"superadmin@admin.com");

				System.out.println(isRevoked(createdCertificate));

				ArrayList<CertificateDTO> lista = readAllCertificates();
			} else {
				keyStoreWriter.loadKeyStore();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void createCRL(PrivateKey key, X500Name issuer)
			throws CRLException, IOException, OperatorCreationException {
		X509v2CRLBuilder crlBuilder = new X509v2CRLBuilder(issuer, new Date());
		JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSA");
		builder.setProvider("BC");
		X509CRLHolder holder = crlBuilder.build(builder.build(key));
		JcaX509CRLConverter converter = new JcaX509CRLConverter();
		converter.setProvider("BC");
		X509CRL crl = converter.getCRL(holder);
		byte[] encoded = crl.getEncoded();
		OutputStream os = new FileOutputStream("src/main/resources/revoked.crl");
		os.write(encoded);
		os.close();
	}

	public boolean createAdminCertificate(CreateCertificateDTO certificateCreationDTO, String issuerAlias)
			throws OperatorCreationException, CertificateException {

		Certificate[] issuerCertificateChain = keyStoreReader.readCertificateChain(issuerAlias);
		
		X509Certificate issuer = (X509Certificate) issuerCertificateChain[0];
		try {
			if (!isValid(issuerCertificateChain) || issuer.getBasicConstraints() == -1 || !issuer.getKeyUsage()[5]) {
				return false;
			}
		} catch (CertificateException e1) {
			e1.printStackTrace();
		} catch (CRLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		String alias = certificateRequestService.findOne(certificateCreationDTO.getRequestId()).getEmail();

		KeyPair keyPair = generateKeyPair();
		SubjectData subjectData = generateSubjectData(certificateCreationDTO.getRequestId());

		subjectData.setPublicKey(keyPair.getPublic());

		IssuerData issuerData = keyStoreReader.readIssuerFromStore(issuerAlias);

		X500Name issuerName = issuerData.getX500name();
		PrivateKey privateKey = issuerData.getPrivateKey();

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date startDate = calendar.getTime();

		calendar.add(Calendar.YEAR, 1);
		Date endDate = calendar.getTime();

		BigInteger serialNum = new BigInteger(Long.toString(new SecureRandom().nextLong()));

		ContentSigner contentSigner = new JcaContentSignerBuilder("SHA256WithRSAEncryption").setProvider("BC")
				.build(issuerData.getPrivateKey());
		X509v3CertificateBuilder builder = new JcaX509v3CertificateBuilder(issuerName, serialNum, startDate,
				endDate, subjectData.getX500name(), keyPair.getPublic());

		CreateCertificateDTO keyUsageDTO = certificateCreationDTO;
		KeyUsage k = new KeyUsage(keyUsageDTO.getcRLSign() | keyUsageDTO.getDataEncipherment()
				| keyUsageDTO.getDecipherOnly() | keyUsageDTO.getDigitalSignature() | keyUsageDTO.getEncipherOnly()
				| keyUsageDTO.getKeyAgreement() | keyUsageDTO.getKeyCertSign() | keyUsageDTO.getKeyEncipherment()
				| keyUsageDTO.getNonRepudiation());

		try {
			builder.addExtension(Extension.keyUsage, false, k);
			if (keyUsageDTO.isKeyCertSign())
				builder.addExtension(Extension.basicConstraints, true, new BasicConstraints(true));
		} catch (CertIOException e) {
			e.printStackTrace();
		}

		X509CertificateHolder holder = builder.build(contentSigner);

		JcaX509CertificateConverter converter = new JcaX509CertificateConverter();
		converter = converter.setProvider("BC");

		X509Certificate createdCertificate = converter.getCertificate(holder);

		Certificate[] newCertificateChain = ArrayUtils.insert(0, issuerCertificateChain, createdCertificate);
		keyStoreWriter.write(alias, keyPair.getPrivate(), newCertificateChain);
		keyStoreWriter.saveKeyStore();
		return true;
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

		
		X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
		builder.addRDN(BCStyle.CN, cerRequestInfo.getCommonName());
		builder.addRDN(BCStyle.O, cerRequestInfo.getOrganization());
		builder.addRDN(BCStyle.OU, cerRequestInfo.getOrganizationUnitName());
		builder.addRDN(BCStyle.C, cerRequestInfo.getCountryName());
		builder.addRDN(BCStyle.E, cerRequestInfo.getEmail());
		builder.addRDN(BCStyle.ST, cerRequestInfo.getStateName());
		builder.addRDN(BCStyle.L, cerRequestInfo.getLocalityName());

		builder.addRDN(BCStyle.UID, String.valueOf(cerRequestInfo.getUserId()));

		return new SubjectData(builder.build(), serialNumber, startDate, endDate);

	}

	public void revokeCertificate(RevokeCertificateDTO revokeCertificateDTO, String issuerAlias)
			throws IOException, CRLException, OperatorCreationException, CertificateEncodingException {

		File file = new File("src/main/resources/revoked.crl");
		byte[] bytes = Files.readAllBytes(file.toPath());
		X509CRLHolder holder = new X509CRLHolder(bytes);
		X509v2CRLBuilder crlBuilder = new X509v2CRLBuilder(holder);

		Certificate certificate = keyStoreReader.readCertificate(revokeCertificateDTO.getAlias());
		JcaX509CertificateHolder certHolder = new JcaX509CertificateHolder((X509Certificate) certificate);
		crlBuilder.addCRLEntry(certHolder.getSerialNumber(),
				new Date(),
				RevocationReason.valueOf(revokeCertificateDTO.getReason()).ordinal());
		JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSA");
		builder.setProvider("BC");

		IssuerData issuerData = keyStoreReader.readIssuerFromStore(issuerAlias);

		X509CRLHolder crlHolder = crlBuilder.build(builder.build(issuerData.getPrivateKey()));
		JcaX509CRLConverter converter = new JcaX509CRLConverter();
		converter.setProvider("BC");

		X509CRL crl = converter.getCRL(crlHolder);
		byte[] encoded = crl.getEncoded();
		OutputStream os = new FileOutputStream("src/main/resources/revoked.crl");
		os.write(encoded);
		os.close();
	}

	public boolean isRevoked(Certificate cer) throws IOException, CertificateException, CRLException {

		CertificateFactory factory = CertificateFactory.getInstance("X.509");
		File file = new File("src/main/resources/revoked.crl");
		byte[] bytes = Files.readAllBytes(file.toPath());
		X509CRL crl = (X509CRL) factory.generateCRL(new ByteArrayInputStream(bytes));
		return crl.isRevoked(cer);
	}

	public boolean isValid(Certificate[] chain) throws CertificateException, CRLException, IOException {

		Date today = new Date();
		X509Certificate certificate;
		for (int i = 0; i < chain.length; i++) {
			certificate = (X509Certificate) chain[i];
			if (isRevoked(certificate)) {
				return false;
			}
			if (today.after(certificate.getNotAfter()) || today.before(certificate.getNotBefore())) {
				return false;
			}
			if (i == chain.length - 1) {
				try {
					certificate.verify(certificate.getPublicKey());
					return true;
				} catch (InvalidKeyException e) {
					e.printStackTrace();
					return false;
				} catch (CertificateException e) {
					e.printStackTrace();
					return false;
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					return false;
				} catch (NoSuchProviderException e) {
					e.printStackTrace();
					return false;
				} catch (SignatureException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return false;
	}

	public ArrayList<CertificateDTO> readAllCertificates() throws CertificateException, CRLException, IOException {
		ArrayList<CertificateDTO> certificates = new ArrayList<CertificateDTO>();

		Enumeration<String> aliases = keyStoreReader.readAliases();

		while (aliases.hasMoreElements()) {
			Certificate c = keyStoreReader.readCertificate(aliases.nextElement());
			CertificateDTO certificate = new CertificateDTO();
			JcaX509CertificateHolder certHolder = new JcaX509CertificateHolder((X509Certificate) c);
			X500Name i = certHolder.getIssuer();
			X500Name subject = certHolder.getSubject();
			try {
				certificate.setEmail(IETFUtils.valueToString(subject.getRDNs(BCStyle.E)[0].getFirst().getValue()));
			}catch (Exception e){
				certificate.setEmail("none");
			}
			certificate.setCommonName(IETFUtils.valueToString(subject.getRDNs(BCStyle.CN)[0].getFirst().getValue()));
			System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
			System.out.println(((X509Certificate) c).getBasicConstraints());
			System.out.println(((X509Certificate) c).getKeyUsage()[5]);
			if (((X509Certificate) c).getBasicConstraints() != -1 || ((X509Certificate) c).getKeyUsage()[5])
				certificate.setCA(true);
			if (isRevoked(c)) {
				certificate.setRevoked(true);
			}

			certificates.add(certificate);
			System.out.println(certificate.getCommonName());
			System.out.println(certificate.getEmail());
			System.out.println(certificate.isCA());
			System.out.println(certificate.getRevoked());
		}
		return certificates;
	}

	public enum RevocationReason {

		UNSPECIFIED, KEY_COMPROMISE, CA_COMPROMISE, AFFILIATION_CHANGED, SUPERSEDED, CESSATION_OF_OPERATION,
		CERTIFICATE_HOLD, REMOVE_FROM_CRL, PRIVILEGE_WITHDRAWN, AA_COMPROMISE

	}

}
