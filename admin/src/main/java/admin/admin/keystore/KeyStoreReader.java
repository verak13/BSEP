package admin.admin.keystore;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.annotation.PostConstruct;

import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

import admin.admin.model.IssuerData;

@Service
public class KeyStoreReader {

    private KeyStore keyStore;
    
    private String file = "src/main/resources/keystore.jks";
    
    private char[] password = "password".toCharArray();

    @PostConstruct
    private void init() {
        Security.addProvider(new BouncyCastleProvider());
        try {
            keyStore = KeyStore.getInstance("JKS", "SUN");
        } catch (KeyStoreException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    public IssuerData readIssuerFromStore(String alias) {
        try {
            // Datoteka se ucitava
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            keyStore.load(in, password);

            // Iscitava se sertifikat koji ima dati alias
            Certificate cert = null;
            if (keyStore.isKeyEntry(alias)) {
                cert = keyStore.getCertificate(alias);
            } else {
           
            }

            // Iscitava se privatni kljuc vezan za javni kljuc koji se nalazi na sertifikatu sa datim aliasom
            PrivateKey privKey = (PrivateKey) keyStore.getKey(alias, password);

            X500Name issuerName = new JcaX509CertificateHolder((X509Certificate) cert).getSubject();
            return new IssuerData(privKey, issuerName);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException
                | UnrecoverableKeyException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Certificate readCertificate(String alias) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            keyStore.load(in, password);

            if (keyStore.isKeyEntry(alias)) {
                Certificate cert = keyStore.getCertificate(alias);
                return cert;
            }
        } catch (KeyStoreException | NoSuchAlgorithmException
                | CertificateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public PrivateKey readPrivateKey(String alias) {
        try {
            // kreiramo instancu KeyStore
            KeyStore ks = KeyStore.getInstance("JKS", "SUN");
            // ucitavamo podatke
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            ks.load(in, password);

            if (ks.isKeyEntry(alias)) {
                PrivateKey pk = (PrivateKey) ks.getKey(alias, password);
                return pk;
            }
        } catch (KeyStoreException | NoSuchAlgorithmException | NoSuchProviderException | CertificateException
                | IOException | UnrecoverableKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public Certificate[] readCertificateChain(String alias) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
            keyStore.load(in, password);

            if (keyStore.isKeyEntry(alias)) {
                return keyStore.getCertificateChain(alias);
            }
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Enumeration<String> readAliases() {
    	 try {
             BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
             keyStore.load(in, password);
             return keyStore.aliases();
           
         } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
             e.printStackTrace();
         }
         return null;
    }

}
