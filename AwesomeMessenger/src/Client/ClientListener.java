package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import org.quickconnect.QuickConnect;

import userInterface.MainFrame;

import beans.CommunicationBean;
import beans.MessageBean;

public class ClientListener implements Runnable {
	private ObjectInputStream iStream;
	private MainFrame mainFrame;
	public ClientListener(ObjectInputStream iStream, MainFrame mainFrame) {
		this.iStream = iStream;
		this.mainFrame = mainFrame;
	}
	@Override
	public void run() {
		while(mainFrame.getController().getSocket().isConnected()) {
			try {
				CommunicationBean commBean = (CommunicationBean) iStream.readObject();
				ArrayList params = new ArrayList();
				params.add(commBean.getParameters());
				QuickConnect.handleRequest(commBean.getCommand(), params);
				//mainFrame.setText(id, messageText);
			} catch (IOException e) { e.printStackTrace(); } 
			catch (ClassNotFoundException e) { 
				System.out.println("Client: Not a CommBean!");
				e.printStackTrace(); 
			}
		}

	}

}
