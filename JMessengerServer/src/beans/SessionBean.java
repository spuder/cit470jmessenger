package beans;

import java.io.Serializable;

public class SessionBean implements Serializable {

	static int session_number = 0;
	
	int sessionNumber;
	String sessionName;
	
	public SessionBean(){
		this.sessionNumber = SessionBean.session_number++;
		this.sessionName = "Session " + this.sessionNumber;
	}
	
	public SessionBean(String name){
		this();
		this.sessionName = name;
	}

	public int getSessionNumber() {
		return sessionNumber;
	}

	public void setSessionNumber(int sessionNumber) {
		this.sessionNumber = sessionNumber;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	
}
