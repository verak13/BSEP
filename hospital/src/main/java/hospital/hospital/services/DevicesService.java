package hospital.hospital.services;

import org.springframework.stereotype.Service;

@Service
public class DevicesService {

    public boolean receiveMessage(String msg){
        System.out.println(msg);
        return true;
    }

}
