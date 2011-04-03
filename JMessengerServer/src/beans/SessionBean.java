package beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class SessionBean implements Serializable {

	private static final long serialVersionUID = 973620567644323290L;

	static int session_number = 0;
	
	String sessionId;
	String sessionName;
	Timestamp creationTime;
	
	public SessionBean(){
		creationTime = new Timestamp(System.currentTimeMillis());
		this.sessionId = "" + SessionBean.session_number++ + "-" + creationTime.toString();
		this.sessionName = "Session " + this.sessionId;
	}
	
	public SessionBean(String name){
		this();
		this.sessionName = name;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	
}
