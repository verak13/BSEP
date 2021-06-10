package hospital.hospital.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hospital.hospital.dto.FilterMessagesDTO;
import hospital.hospital.dto.MessageResponseDTO;
import hospital.hospital.model.Log;
import hospital.hospital.model.Message;
import hospital.hospital.model.Patient;
import hospital.hospital.model.cep.LogEvent;
import hospital.hospital.model.cep.MessageEvent;
import hospital.hospital.repository.MessageRepository;
import hospital.hospital.repository.PatientRepository;
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
	
	private static final Logger logger = LoggerFactory.getLogger(DevicesService.class);

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private KieSession kieSession;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	private RulesService rulesService;
	
	@Autowired
    KeyStoreReader keyStoreReaderService;

    //yyyy-MM-dd HH:mm|patientId|bodyTemperature|pulseRate|respirationRate|bloodPressureDiastolic|bloodPressureSystolic
    public boolean parseMessage(String msg){
        System.out.println(msg);
        Message message = new Message();
		String[] array = msg.split("\\|");
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(array[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		message.setTimestamp(date);
		message.setPatientId(Long.parseLong(array[1]));
		message.setBodyTemperature(Double.parseDouble(array[2]));
		message.setPulseRaye(Double.parseDouble(array[3]));
		message.setRespirationRate(Double.parseDouble(array[4]));
		message.setBloodPressureDiastolic(Double.parseDouble(array[5]));
		message.setBloodPressureSystolic(Double.parseDouble(array[6]));
				
		messageRepository.save(message);
		kieSession.insert(new MessageEvent(message));
        kieSession.getAgenda().getAgendaGroup("doctor-alarms").setFocus();
		kieSession.fireAllRules();
		return true;
    }
    
    


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
            //tu negdje kad se dobije poruka kao string pozvati parseMessage

            return plainText == plainText;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalStateException | IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }



        return true;
    }



	public Page<MessageResponseDTO> findAll(Pageable pageable, FilterMessagesDTO filter) {
		Page<Message> messages = filter.getPatientId() == 0L ? 
				messageRepository.findAll(pageable) : messageRepository.findAllByPatientId(filter.getPatientId(), pageable);

		ArrayList<MessageResponseDTO> forReturn = new ArrayList<MessageResponseDTO>();
    	
		for (Message m : messages.toList()) {
			MessageResponseDTO messageDTO = new MessageResponseDTO();
			messageDTO.setId(m.getId());
			messageDTO.setBloodPressure(m.getBloodPressureDiastolic());
			messageDTO.setBodyTemperature(m.getBodyTemperature());
			messageDTO.setHeartRate(m.getBloodPressureSystolic());
			messageDTO.setPatientId(m.getPatientId());
			messageDTO.setPulseRaye(m.getPulseRaye());
			messageDTO.setRespirationRate(m.getRespirationRate());
			messageDTO.setTimestamp(m.getTimestamp());
			Optional<Patient> p = patientRepository.findById(m.getPatientId());
			if (p.isPresent()) {
				messageDTO.setPatient(p.get().getFirstName() + ' ' + p.get().getLastName());
			}
			forReturn.add(messageDTO);
		}
		logger.info("Reading messages from database");
		return new PageImpl<MessageResponseDTO>(forReturn, pageable, messages.getTotalElements());
	}
	
}
