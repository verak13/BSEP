package hospital.hospital.model.cep;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import hospital.hospital.model.Message;

@Role(Role.Type.EVENT)
@Expires("1s")
public class MessageEvent implements Serializable {
	
	private Message message;
	
	public MessageEvent() {}

	public MessageEvent(Message message) {
		super();
		this.message = message;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
