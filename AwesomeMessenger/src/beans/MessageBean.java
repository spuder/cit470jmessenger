package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageBean implements Serializable {

	private static final long serialVersionUID = 7338746756665040103L;

	String sender;
	String message;
	Timestamp timestamp; // SQL Friendly object
	String session;
	String sessionid;
	
	public MessageBean(String sender, String message, Timestamp timestamp){
		this.sender = sender;
		this.message = message;
		this.timestamp = timestamp;
	}
	
	public MessageBean(String sender, String message){
		this.sender = sender;
		this.message = message;
		timestamp = new Timestamp(System.currentTimeMillis());
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	
	
}
