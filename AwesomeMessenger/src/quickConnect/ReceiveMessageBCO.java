package quickConnect;

import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import userInterface.ChatSessionPanel;
import userInterface.MainFrame;

import beans.MessageBean;
import beans.SessionBean;

public class ReceiveMessageBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		HashMap params = (HashMap)arg0.get(0);
		MessageBean msg = (MessageBean) params.get("message");
		
		ArrayList<ChatSessionPanel> sessions = MainFrame.mainFrame.getChatSessions();
		
		for(int i = 0; i < sessions.size(); i++){
			SessionBean curSession = sessions.get(i).getSession();
			if(curSession.getSessionId().equals(msg.getSessionid())){
				String timestamp = msg.getTimestamp().getHours() + ":" + msg.getTimestamp().getMinutes();
				sessions.get(i).getaMessengerPanel().getReceiveArea().append("[" + timestamp + "] " + msg.getSender()+ " > " +msg.getMessage() + "\n");
				break;
			}
		}
		
		return null;
	}

}
