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
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.MGF1ParameterSpec;
import java.util.Arrays;

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


	private boolean verifySignature(byte[] data, byte[] signature, PublicKey publicKey) {
		try {
			Signature sig = Signature.getInstance("SHA256withRSA");
			sig.initVerify(publicKey);
			sig.update(data);
			return sig.verify(signature);
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
			e.printStackTrace();
		}
		return false;
	}

    public boolean receiveMessage(byte[] msg, PublicKey pk){
        byte[] sig = Arrays.copyOfRange(msg, 0, 256);
        byte[] message = Arrays.copyOfRange(msg, 256, msg.length);

		if(!verifySignature(message, sig, pk)){
			return false;
		}

		String plainText = new String(message, StandardCharsets.UTF_8);
		parseMessage(plainText);

		// XSS filter...

		return true;

    }




	public Page<MessageResponseDTO> findAll(Pageable pageable, FilterMessagesDTO filter) {

		Page<Message> messages = filter.getPatientId() == 0L ? 
				messageRepository.findAll(pageable) : messageRepository.findAllByPatientId(filter.getPatientId(), pageable);

		ArrayList<MessageResponseDTO> forReturn = new ArrayList<MessageResponseDTO>();

//		Patient p0 = new Patient();
//		p0.setJmbg("1220202802020");
//		p0.setFirstName("Milena");
//		p0.setLastName("Laketic");
//
//		Patient p1 = new Patient();
//		p1.setJmbg("1220200202111");
//		p1.setFirstName("Markro");
//		p1.setLastName("Markovic");
//
//		Patient p2 = new Patient();
//		p2.setJmbg("12270200202330");
//		p2.setFirstName("Jovana");
//		p2.setLastName("Jovanovic");
//
//		Patient p3 = new Patient();
//		p3.setJmbg("12202001202330");
//		p3.setFirstName("Milena");
//		p3.setLastName("Laketic");
//
//		patientRepository.save(p1);
//		patientRepository.save(p2);
//		patientRepository.save(p3);
//		patientRepository.save(p0);



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
