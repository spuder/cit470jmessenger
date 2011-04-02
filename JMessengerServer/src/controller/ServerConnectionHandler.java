package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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
				
		}
	}
	
}
