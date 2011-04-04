package qc;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import beans.CommunicationBean;
import beans.MessageBean;

public class BroadcastMessageBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		MessageBean message = (MessageBean) arg0.get(arg0.size()-1);
		String sessionNumber = message.getSessionid();
		CommunicationBean commBean = new CommunicationBean();
		HashMap params = new HashMap();
		params.put("message", message);
		commBean.setParameters(params);
		commBean.setCommand("receiveMessage");
		
		HashMap<String, HashMap<String, ObjectOutputStream>> sessions = MainFrame.mainFrame.getController().getConnectionMap();
		
		HashMap<String, ObjectOutputStream> session = sessions.get(sessionNumber);
		
		ArrayList streams = new ArrayList(session.values());
		
		
		for(int i = 0; i < streams.size(); i++){
			ObjectOutputStream curStream = (ObjectOutputStream) streams.get(i);
			try {
				curStream.writeObject(commBean);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return message;
	}

}
