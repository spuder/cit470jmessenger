package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.quickconnect.QuickConnect;

import ui.MainFrame;

import beans.CommunicationBean;

public class ServerConnectionHandler implements Runnable {

	Socket connection;
	ObjectOutputStream outputStream;
	ObjectInputStream inputStream;
	
	ServerConnectionHandler(Socket sock){
		this.connection = sock;
	}
	
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
				params.add(MainFrame.mainFrame);
				params.add(commBean.getParameters());
				QuickConnect.handleRequest(commBean.getCommand(), params);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
}
