package quickConnect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.quickconnect.ControlObject;

import Client.ClientController;

import userInterface.MainFrame;

public class BCOConnectToServer implements ControlObject{
	ObjectOutputStream outputStream;
	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		// TODO Auto-generated method stub
		
		ClientController controller = MainFrame.mainFrame.getController();
		Socket sock;
		String ip = controller.getIpAddress();
		int port = controller.getPort();

		
		try {
			sock = new Socket(ip,port);
			controller.setSocket(sock);
			controller.setoStream(new ObjectOutputStream(sock.getOutputStream()));
			controller.setiStream(new ObjectInputStream(sock.getInputStream()));
			System.out.println("Client: Connected to Server");
			return controller;
		} catch (IOException e) { e.printStackTrace(); }
		
		return false;
	}

}
