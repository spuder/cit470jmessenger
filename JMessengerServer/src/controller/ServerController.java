package controller;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class ServerController {

	ServerSocket serSock;
	Socket userConnection;
	HashMap<String, ObjectOutputStream> connectionMap; //username and objectoutputstream
	
	public ServerController(int port){
		
		connectionMap = new HashMap<String, ObjectOutputStream>();
		//TODO Ask for JDBC credentials and location
		
		try{
			serSock = new ServerSocket(port);
		} catch (Exception ec){
			//Error if port is taken, etc
			JOptionPane.showMessageDialog(null, "Could Not Bind Port");
			System.exit(0);
		}
		System.out.println("Server: Listener started");
	}
	
	public void startServer(){
		while(true){
			//listen for connections
			try{
				userConnection = this.serSock.accept();
				System.out.println("Server: Client connected");
				ServerConnectionHandler newConnection = new ServerConnectionHandler(userConnection);
				Thread newThread = new Thread(newConnection);
				newThread.start();
			}catch(Exception ce){
				new Exception("Server: Client could not connect");
			}
		}		
	}
	
	public void shutdownServer(){
		try {
			this.serSock.close();
			System.out.println("Server: Server Stopped");
		} catch (IOException e) {
			System.out.println("Server: Failed to stop server");
		}
	}
	
}
