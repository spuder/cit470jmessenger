package qc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.quickconnect.ControlObject;

import beans.CommunicationBean;
import beans.SessionBean;

import ui.MainFrame;

public class PopulateChatSessionTabsVCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		System.out.println("Server: Adding Session Tabs");
		//get the communication bean sent back with the success message
		CommunicationBean commBean = (CommunicationBean) arg0.get(arg0.size()-1);
		HashMap params = commBean.getParameters();
		boolean success = (Boolean) params.get("success");
		if(success){
			SessionBean session = (SessionBean) params.get("session");
			Vector sessions = (Vector) params.get("list");
			//for (int i=)
			//MainFrame.mainFrame.getChatPanel().newSession(session);
		}
		return null;
	}

}
