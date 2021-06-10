package hospital.hospital.services;

import hospital.hospital.dto.DeviceMessageDTO;
import hospital.hospital.keystore.KeyStoreReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.MGF1ParameterSpec;
import java.util.Arrays;

@Service
public class DevicesService {

    @Autowired
    KeyStoreReader keyStoreReaderService;


    public boolean receiveMessage(byte[] msg, String alias){
        System.out.println(msg);
        PrivateKey pk = keyStoreReaderService.readPrivateKey(alias);


        byte[] key = Arrays.copyOfRange(msg, 0, 256);
        byte[] cipher = Arrays.copyOfRange(msg, 256, msg.length);


        System.out.println("DUZINA KLJUCA JE " + key.length);

        if(pk == null){
            return false;
        }
        try {


            Cipher c = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            c.init(Cipher.DECRYPT_MODE, pk, new OAEPParameterSpec("SHA-256",
                    "MGF1", MGF1ParameterSpec.SHA256, new PSource.PSpecified("OAEP Encrypted".getBytes(StandardCharsets.UTF_8))));

            byte[] plainText = c.doFinal(key);

            System.out.println(plainText + " DESIFROVAO WOWOWOOWOWOWO");

            return plainText == plainText;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalStateException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }



        return true;
    }

}
