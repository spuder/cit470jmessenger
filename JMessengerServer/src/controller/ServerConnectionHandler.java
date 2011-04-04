package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.quickconnect.QuickConnect;

import ui.MainFrame;

import beans.CommunicationBean;
import beans.UserBean;

public class ServerConnectionHandler implements Runnable {

	Socket connection;
	UserBean user;
	ObjectOutputStream outputStream;
	ObjectInputStream inputStream;
	
	ServerConnectionHandler(Socket sock){
		this.connection = sock;
	}
	
	@Override
	public void run() {
		try {
			outputStream = new ObjectOutputStream(connection.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			inputStream = new ObjectInputStream(connection.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(connection.isConnected()){
			CommunicationBean commBean;
			try {
				commBean = (CommunicationBean)inputStream.readObject();
				ArrayList params = new ArrayList();
				params.add(this);
				params.add(commBean.getParameters());
				QuickConnect.handleRequest(commBean.getCommand(), params);
			} catch (IOException e) {
				try {
					String username = this.user.getUsername();
					HashMap<String, HashMap<String, ObjectOutputStream>> allSessions = MainFrame.mainFrame.getController().getConnectionMap();
					ArrayList<HashMap<String,ObjectOutputStream>> sessionsAsArray = new ArrayList(allSessions.values());
					ArrayList<String> keys = new ArrayList(allSessions.keySet());
					for (int i = 0; i< sessionsAsArray.size(); i++){
						HashMap curHashMap = sessionsAsArray.get(i);
						System.out.println("Deleting user: " + username + " from Session.");
						if(curHashMap.get(username).equals(username)) {
							ArrayList al = new ArrayList();
							HashMap map = new HashMap();
							curHashMap.remove(username);
							map.put("sessionId", keys.get(i));
							al.add(this);
							al.add(map);
							QuickConnect.handleRequest("leaveUser", al);

						}


					}
					
					connection.close();
	
				} catch (IOException e1) {
					e1.printStackTrace();
					break;
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public Socket getConnection() {
		return connection;
	}

	public void setConnection(Socket connection) {
		this.connection = connection;
	}

	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(ObjectOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public ObjectInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ObjectInputStream inputStream) {
		this.inputStream = inputStream;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}
	
	
	
}
