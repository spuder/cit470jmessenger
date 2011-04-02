package beans;

import java.io.Serializable;

public class UserBean implements Serializable {
	
	String username;
	String role;
	
	public UserBean(){
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	
}
