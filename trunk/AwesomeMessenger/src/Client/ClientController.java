package Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import beans.UserBean;

public class ClientController {
	
	String ipAddress;
	int port;
	Socket socket;
	ObjectOutputStream oStream;
	ObjectInputStream iStream;
	UserBean curUser;
	
	
	
	public UserBean getCurUser() {
		return curUser;
	}

	public void setCurUser(UserBean curUser) {
		this.curUser = curUser;
	}

	public ObjectOutputStream getoStream() {
		return oStream;
	}

	public void setoStream(ObjectOutputStream oStream) {
		this.oStream = oStream;
	}

	public ObjectInputStream getiStream() {
		return iStream;
	}

	public void setiStream(ObjectInputStream iStream) {
		this.iStream = iStream;
	}

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

	public void newThread(ObjectInputStream iStream, MainFrame mainFrame) {
		ClientListener clientListener = new ClientListener(iStream);
		Thread receivingThread = new Thread(clientListener);
		receivingThread.start();
	}
	
	
}
