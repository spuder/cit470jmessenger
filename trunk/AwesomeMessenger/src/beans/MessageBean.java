package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class MessageBean implements Serializable {

	private static final long serialVersionUID = 7338746756665040103L;

	String sender;
	String message;
	Timestamp timestamp; // SQL Friendly object
	
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
}
