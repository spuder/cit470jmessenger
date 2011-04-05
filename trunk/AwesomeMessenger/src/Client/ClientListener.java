package Client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
			} catch (IOException e) { 
				System.out.println("Session Dropped");
				JOptionPane.showMessageDialog(mainFrame, "Connection to the server has been lost.");
				break; 
			} 
			catch (ClassNotFoundException e) { 
				System.out.println("Client: Not a CommBean!");
				e.printStackTrace(); 
			}
		}
		try {
			mainFrame.getController().getSocket().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
