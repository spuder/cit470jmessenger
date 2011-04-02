package controller;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ServerController {

	ServerSocket serSock;
	Socket userConnection;
	
	public ServerController(int port){
		try{
			serSock = new ServerSocket(port);
		} catch (Exception ec){
			//Error if port is taken, etc
			JOptionPane.showMessageDialog(null, "Could Not Bind Port");
			System.exit(0);
		}
		System.out.println("Server: Listener started");
		startServer();
	}
	
	private void startServer(){
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
	
}
