package qc;

import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import controller.ServerConnectionHandler;

import ui.ChatSessionPanel;
import ui.MainFrame;
import beans.FileBean;
import beans.MessageBean;

public class UpdateLocalFileVCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		ServerConnectionHandler connection = (ServerConnectionHandler) arg0.get(0);
		HashMap params = (HashMap) arg0.get(1);
		FileBean file = (FileBean) params.get("file");
		ArrayList sessions = MainFrame.mainFrame.getChatPanel().getChatSessions();
		String session = (String) params.get("sessionId");
		for (int i=0; i < sessions.size(); i++) {
			ChatSessionPanel current = (ChatSessionPanel) sessions.get(i);
			if (session.equals(current.getSessionObject().getSessionId())) {
				current.getChatBox().append("[" + file.getTimestamp().toString() + "] " + connection.getUser().getUsername()+ "> " +file.getFileName() + "\n");
			}
		}
		return null;
	}

}
