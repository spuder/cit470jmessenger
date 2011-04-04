package quickConnect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.quickconnect.ControlObject;

import userInterface.ChatSessionPanel;
import userInterface.MainFrame;
import beans.SessionBean;

public class DisplayFileListVCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		HashMap params = (HashMap) arg0.get(0);
		Vector list = (Vector) params.get("list");
		String id = (String) params.get("sessionId");
		
		ArrayList<ChatSessionPanel> sessions = MainFrame.mainFrame.getChatSessions();
		
		for(int i = 0; i < sessions.size(); i++){
			SessionBean curSession = sessions.get(i).getSession();
			if(curSession.getSessionId().equals(id)){
				sessions.get(i).getaFilePanel().setFileList(list);
				break;
			}
		}
		
		return null;
	}

}
