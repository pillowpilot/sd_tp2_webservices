package testA.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ResponseMessage {
	private String message;

	public ResponseMessage() {
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ResponseMessage [message=" + message + "]";
	}

}
