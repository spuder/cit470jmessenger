package qc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.quickconnect.ControlObject;

import ui.ChatSessionPanel;
import ui.MainFrame;

public class UpdateSessionUsersVCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		//Vector users = (Vector) arg0.get(arg0.size()-1);
		ArrayList<ChatSessionPanel> sessions = MainFrame.mainFrame.getChatPanel().getChatSessions();
		HashMap map = (HashMap) arg0.get(1);
		Vector users = (Vector)map.get("list");
		String session = (String) map.get("sessionId");
		for (int i = 0; i < sessions.size(); i++) {
			if (sessions.get(i).getSessionObject().getSessionId().equals(session)) {
				sessions.get(i).setUsersList(users);
				return true;
			}
		}
		return true;
	}

}
