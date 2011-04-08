package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ConnectionBean implements Serializable{
	
	private static final long serialVersionUID = -8955662893007433764L;
	String ip;
	String port;
	
	public ConnectionBean() {
		
		try {
			FileInputStream input = new FileInputStream("serverconfig.dat");
			ObjectInputStream objInput = new ObjectInputStream(input);
			Object obj = objInput.readObject();
			if (obj instanceof ConnectionBean) {
				ConnectionBean imported = (ConnectionBean) obj;
				this.ip = imported.getIp();
				this.port = imported.getPort();
			}
		} catch (Exception e) {
			this.ip = "";
			this.port = "";
		}
		
		
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	public void saveBean() {
		
		try {
			FileOutputStream output = new FileOutputStream("serverconfig.dat");
			ObjectOutputStream objOutput = new ObjectOutputStream(output);
			objOutput.writeObject(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
