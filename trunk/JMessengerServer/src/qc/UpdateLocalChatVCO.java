package qc;

import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.ChatSessionPanel;
import ui.MainFrame;

import beans.MessageBean;

public class UpdateLocalChatVCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		HashMap params = (HashMap) arg0.get(1);
		MessageBean msg = (MessageBean) params.get("message");
		
		ArrayList sessions = MainFrame.mainFrame.getChatPanel().getChatSessions();
		String session = msg.getSession();
		for (int i=0; i < sessions.size(); i++) {
			ChatSessionPanel current = (ChatSessionPanel) sessions.get(i);
			if (session.equals(current.getSessionObject().getSessionId())) {
				current.getChatBox().append("[" + msg.getTimestamp().toString() + "] " + msg.getSender()+ "> " +msg.getMessage() + "\n");
			}
		}
		return null;
	}

}
