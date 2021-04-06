package admin.admin.keystore;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Service;

@Service
public class KeyStoreWriter {

    private KeyStore keyStore;
    
    private String file = "src/main/resources/keystore.jks";
    
    private char[] password = "password".toCharArray();

    public KeyStoreWriter() {
        try {
            keyStore = KeyStore.getInstance("JKS", "SUN");
        } catch (KeyStoreException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }
    
    @PostConstruct
    private void init() {
        Security.addProvider(new BouncyCastleProvider());
        try {
            keyStore = KeyStore.getInstance("JKS", "SUN");
        } catch (KeyStoreException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }
    
    public void createKeyStore(String password) {
        try {
            keyStore.load(null, password.toCharArray());
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            e.printStackTrace();
        }
    }
    
    public boolean keyStoreExists() {
    	File f = new File(file);
    	if(f.exists()) {
    		return true;
    	}
    	return false;
    }

    public void saveKeyStore() {
        try {
            keyStore.store(new FileOutputStream(file), password);
        } catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String alias, PrivateKey privateKey, Certificate[] certificateChain) {
        try {
            keyStore.setKeyEntry(alias, privateKey, password, certificateChain);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }
    
    public void writeRootCA(String alias, PrivateKey privateKey, Certificate certificate) {
        try {
            keyStore.setKeyEntry(alias, privateKey, password, new Certificate[]{certificate});
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
    }


}