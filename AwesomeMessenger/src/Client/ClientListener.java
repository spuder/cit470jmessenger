package Client;

import java.io.IOException;
import java.io.ObjectInputStream;

import userInterface.MainFrame;

import beans.CommunicationBean;

public class ClientListener implements Runnable {
	private ObjectInputStream iStream;
	public ClientListener(ObjectInputStream iStream) {
		this.iStream = iStream;
	}
	@Override
	public void run() {
		while(true) {
			try {
				CommunicationBean commBean = (CommunicationBean) iStream.readObject();
				String messageText = commBean.getParameters().get(0); // 
				
			} catch (IOException e) { e.printStackTrace(); } catch (ClassNotFoundException e) { e.printStackTrace(); }
			

		}

	}

}
