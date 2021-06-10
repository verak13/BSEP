package hospital.hospital.dto;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



public class DeviceMessageDTO {

    private byte[] message;
    private byte[] key;

    public DeviceMessageDTO() {}

    public DeviceMessageDTO(byte[] message, byte[] key) {
        this.message = message;
        this.key = key;
    }

    public byte[] getMessage() {
        return message;
    }

    public void setMessage(byte[] message) {
        this.message = message;
    }

    public byte[] getKey() {
        return key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }



}