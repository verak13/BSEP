package admin.admin.helper;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;

import org.bouncycastle.jce.provider.BouncyCastleProvider;


// RSA algoritam moze da kriptuje podatke maksimalne velicine do velicine kljuca (u bajtovima)
// Stoga se obicno koristi samo za kriptovanje tajnih kljuceva koji kriptuju podatke
public class RSA {
    public RSA() {
        // Bouncy Castle (https://www.bouncycastle.org) je provajder kriptografskih funkcionalnosti
        // Iako JDK dolazi sa odredjenim skupom kriptografskih primitiva, treba skrenuti paznju da postoje druga resenja za ovaj problem
        // Razliciti provajderi nude implementacije razlicitih skupova kriptografskih funkcija
        // U zavisnosti od potrebe, treba izabrati provajdera koji vazi za najpouzdanijeg za dat programski jezik
        // Za RSA u Javi, mi koristimo Bouncy Castle API
        Security.addProvider(new BouncyCastleProvider());
    }

    public KeyPair generateKeys() {
        try {
            // Generator para kljuceva
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");

            // Za kreiranje kljuceva neophodno je definisati generator pseudoslucajnih brojeva
            // Ovaj generator mora biti bezbedan (nije jednostavno predvideti koje brojeve ce RNG generisati)
            // U ovom primeru se koristi generator zasnovan na SHA1 algoritmu, gde je SUN provajder
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");

            // inicijalizacija generatora, 2048 bitni kljuc
            keyGen.initialize(2048, random);

            // generise par kljuceva koji se sastoji od javnog i privatnog kljuca
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] encrypt(byte[] plainText, PrivateKey key) {
        try {
            Cipher rsaCipherEnc = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");

            // inicijalizacija za sifrovanje
            rsaCipherEnc.init(Cipher.ENCRYPT_MODE, key);

            // sifrovanje
            byte[] cipherText = rsaCipherEnc.doFinal(plainText);
            return cipherText;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException
                | IllegalStateException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] decrypt(byte[] cipherText, PublicKey key) {
        try {
            Cipher rsaCipherDec = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");

            // inicijalizacija za desifrovanje
            rsaCipherDec.init(Cipher.DECRYPT_MODE, key);

            // desifrovanje
            byte[] plainText = rsaCipherDec.doFinal(cipherText);
            return plainText;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException
                | IllegalStateException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String encodePublicKey(PublicKey key) {
        return DatatypeConverter
                .printBase64Binary(key.getEncoded());
    }
}
