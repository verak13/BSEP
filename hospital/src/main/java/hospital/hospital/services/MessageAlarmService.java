package hospital.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import hospital.hospital.model.cep.alarms.MessageAlarm;
import hospital.hospital.repository.MessageAlarmRepository;

@Service
public class MessageAlarmService {

	@Autowired
	MessageAlarmRepository messageAlarmRepository;
	
	public Page<MessageAlarm> findAll(Pageable pageable) {
		System.out.println("dadddadaaad");
		return messageAlarmRepository.findAll(pageable);
	}

	public MessageAlarm findOne(long id) {
		return messageAlarmRepository.findById(id).orElse(null);
	}
	
	public MessageAlarm saveMessageAlarm(MessageAlarm ma) {
		return messageAlarmRepository.save(ma);
	}
}
