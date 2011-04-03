package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

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
				e.printStackTrace();
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
