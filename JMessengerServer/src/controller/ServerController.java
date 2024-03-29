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
	HashMap<String, HashMap<String, ObjectOutputStream>> connectionMap; //K= Session Number   Value=HashMap   <--- That map has Username - OutputStream pair
	ConnectionPool connectionPool;
	
	public ServerController(int port, String uname, String pword, int dbport){
		
		connectionMap = new HashMap<String, HashMap<String, ObjectOutputStream>>();
		connectionPool = new ConnectionPool(uname, pword, dbport);
		
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

	public HashMap<String, HashMap<String, ObjectOutputStream>> getConnectionMap() {
		return connectionMap;
	}

	public void setConnectionMap(HashMap<String, HashMap<String, ObjectOutputStream>> connectionMap) {
		this.connectionMap = connectionMap;
	}

	public ServerSocket getSerSock() {
		return serSock;
	}

	public void setSerSock(ServerSocket serSock) {
		this.serSock = serSock;
	}
	
	
}
