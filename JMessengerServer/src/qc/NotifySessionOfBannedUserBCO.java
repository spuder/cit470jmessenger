package qc;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;
import beans.CommunicationBean;
import beans.MessageBean;
import beans.UserBean;
import controller.ServerConnectionHandler;

public class NotifySessionOfBannedUserBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		HashMap params1 = (HashMap) arg0.get(1);
		String id = (String) params1.get("sessionId");
		ServerConnectionHandler handler = (ServerConnectionHandler) arg0.get(0);
		UserBean curUser = handler.getUser();
		
		MessageBean message = new MessageBean("SERVER", curUser.getUsername() +" has been banned");
		message.setSessionid(id);
		
		CommunicationBean commBean = new CommunicationBean();
		HashMap params = new HashMap();
		params.put("message", message);
		commBean.setParameters(params);
		commBean.setCommand("receiveSystemMessage");
		
		HashMap<String, HashMap<String, ObjectOutputStream>> sessions = MainFrame.mainFrame.getController().getConnectionMap();
		
		HashMap<String, ObjectOutputStream> session = sessions.get(id);
		
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
