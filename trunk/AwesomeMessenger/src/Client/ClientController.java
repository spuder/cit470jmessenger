package Client;

import java.net.Socket;

public class ClientController {
	
	String ipAddress;
	int port;
	Socket socket;
	
	public ClientController() {
		
	}
	
	public void connectToServer() {
		
	}
	
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	
}
