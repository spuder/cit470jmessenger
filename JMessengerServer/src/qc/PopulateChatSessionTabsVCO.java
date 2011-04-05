package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.quickconnect.ControlObject;

import com.mysql.jdbc.Connection;

import beans.CommunicationBean;
import beans.SessionBean;

import ui.MainFrame;

public class PopulateChatSessionTabsVCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		CommunicationBean commBean = (CommunicationBean) arg0.get(arg0.size()-1);
		HashMap params = commBean.getParameters();
		
		Vector sessions = (Vector) params.get("list");
		ArrayList<String[]> sessionsNumberArray = new ArrayList<String[]>(sessions);
		
		for(int i = 0; i< sessionsNumberArray.size(); i++){
			SessionBean curSession = new SessionBean();
			curSession.setSessionName(sessionsNumberArray.get(i)[0]);
			curSession.setSessionId(sessionsNumberArray.get(i)[2]);
			MainFrame.mainFrame.getChatPanel().newSession(curSession);
		}
		MainFrame.mainFrame.getChatPanel().repaint();
		MainFrame.mainFrame.pack();
		return null;
	}

}
