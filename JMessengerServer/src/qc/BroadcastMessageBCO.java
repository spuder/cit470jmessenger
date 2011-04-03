package qc;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import beans.MessageBean;

public class BroadcastMessageBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		MessageBean message = (MessageBean) arg0.get(arg0.size()-1);
		String sessionNumber = message.getSessionid();
		
		HashMap<String, HashMap<String, ObjectOutputStream>> sessions = MainFrame.mainFrame.getController().getConnectionMap();
		
		HashMap<String, ObjectOutputStream> session = sessions.get(sessionNumber);
		
		ArrayList streams = new ArrayList(session.values());
		
		
		for(int i = 0; i < streams.size(); i++){
			ObjectOutputStream curStream = (ObjectOutputStream) streams.get(i);
			try {
				curStream.writeObject(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return message;
	}

}
