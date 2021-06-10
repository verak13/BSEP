package hospital.hospital.services;

import hospital.hospital.keystore.KeyStoreReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.security.*;
import java.security.spec.MGF1ParameterSpec;

@Service
public class DevicesService {

    @Autowired
    KeyStoreReader keyStoreReaderService;


    public boolean receiveMessage(byte[] msg, String alias){
        System.out.println(msg);
        PrivateKey pk = keyStoreReaderService.readPrivateKey(alias);

        if(pk == null){
            return false;
        }
        try {

            System.out.println(msg.length + "JE SIZE");

            Cipher c = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            c.init(Cipher.DECRYPT_MODE, pk, new OAEPParameterSpec("SHA-256",
                    "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));

            byte[] plainText = c.doFinal(msg);

            System.out.println(plainText + " DESIFROVAO WOWOWOOWOWOWO");

            return plainText == plainText;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalStateException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }



        return true;
    }

}
