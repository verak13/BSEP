package hospital.hospital.services;

import hospital.hospital.model.CertificateRequest;
import hospital.hospital.model.SubjectData;
import hospital.hospital.model.User;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.security.*;
import java.text.ParseException;

@Service
public class CertificateRequestService {


    public void sendCSR(CertificateRequest csr) throws Exception {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (csr.getEmail() != user.getEmail()) {
            throw new Exception();
        }
        KeyPair keyPairSubject = generateKeyPair();

        //generisi subjectdata
        SubjectData subjectData = generateSubjectData(csr, user, keyPairSubject.getPublic());

        //hesiraj dokument ovo nekako treba u bytearr da ide
//        byte[] dataHash = hash(subjectData);

        //kodira se sve sa private keyem
        //digitalno potpisivanje

        //posalji ga preko rest templejta -lako

        //proveri odg -tjt
    } 


    private SubjectData generateSubjectData(CertificateRequest csr, User user, PublicKey key) {


        // klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, csr.getCommonName());
        builder.addRDN(BCStyle.SURNAME, user.getLastName());
        builder.addRDN(BCStyle.GIVENNAME, user.getFirstName());
        builder.addRDN(BCStyle.O, csr.getOrganization());
        builder.addRDN(BCStyle.OU, csr.getOrganizationUnitName());
        builder.addRDN(BCStyle.C, csr.getCountryName());
        builder.addRDN(BCStyle.E, csr.getEmail());

        // UID (USER ID) je ID korisnika
        builder.addRDN(BCStyle.UID, user.getId().toString());

        return new SubjectData(key, builder.build());
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


    public byte[] hash(String data) {
        // Kao hes funkcija koristi SHA-256
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            return sha256.digest(data.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
