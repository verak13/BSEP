package hospital.hospital.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hospital.hospital.dto.FilterMessagesDTO;
import hospital.hospital.dto.MessageResponseDTO;
import hospital.hospital.model.Message;
import hospital.hospital.model.Patient;
import hospital.hospital.repository.MessageRepository;
import hospital.hospital.repository.PatientRepository;

@Service
public class DevicesService {
	
	private static final Logger logger = LoggerFactory.getLogger(DevicesService.class);

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	PatientRepository patientRepository;

    //yyyy-MM-dd HH:mm|patientId|bodyTemperature|pulseRate|respirationRate|bloodPressure|heartRate
    public boolean receiveMessage(String msg){
        System.out.println(msg);
        Message message = new Message();
		String[] array = msg.split("|");
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
		message.setBloodPressure(Double.parseDouble(array[5]));
		message.setHeartRate(Double.parseDouble(array[6]));
		messageRepository.save(message);
        return true;
    }


	public Page<MessageResponseDTO> findAll(Pageable pageable, FilterMessagesDTO filter) {
		Page<Message> messages = filter.getPatientId() == 0L ? 
				messageRepository.findAll(pageable) : messageRepository.findAllByPatientId(filter.getPatientId(), pageable);

		ArrayList<MessageResponseDTO> forReturn = new ArrayList<MessageResponseDTO>();
    	
		for (Message m : messages.toList()) {
			MessageResponseDTO messageDTO = new MessageResponseDTO();
			messageDTO.setId(m.getId());
			messageDTO.setBloodPressure(m.getBloodPressure());
			messageDTO.setBodyTemperature(m.getBodyTemperature());
			messageDTO.setHeartRate(m.getHeartRate());
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
