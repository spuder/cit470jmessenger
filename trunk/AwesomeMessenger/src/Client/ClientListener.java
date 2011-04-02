package Client;

import java.io.IOException;
import java.io.ObjectInputStream;

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
		while(true) {
			try {
				CommunicationBean commBean = (CommunicationBean) iStream.readObject();
				String id = (String) ((MessageBean)commBean.getParameters().get("message")).getSessionid(); // fix this
				String messageText = (String) ((MessageBean)commBean.getParameters().get("message")).getMessage();
				mainFrame.setText(id, messageText);
			} catch (IOException e) { e.printStackTrace(); } catch (ClassNotFoundException e) { e.printStackTrace(); }


		}

	}

}
