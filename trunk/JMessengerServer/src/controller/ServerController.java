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
	HashMap<String, HashMap> connectionMap; //username and objectoutputstream
	ConnectionPool connectionPool;
	
	public ServerController(int port, String uname, String pword){
		
		connectionMap = new HashMap<String, HashMap>();
		connectionPool = new ConnectionPool(uname, pword);
		
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

	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}

	public void setConnectionPool(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}
	
	
	
}
