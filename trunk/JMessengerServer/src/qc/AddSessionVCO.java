package qc;

import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import beans.CommunicationBean;
import beans.SessionBean;

public class AddSessionVCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		System.out.println("Server: Adding Session Tab");
		//get the communication bean sent back with the success message
		CommunicationBean commBean = (CommunicationBean) arg0.get(arg0.size()-1);
		HashMap params = commBean.getParameters();
		boolean success = (Boolean) params.get("success");
		if(success){
			SessionBean session = (SessionBean) params.get("session");
			MainFrame.mainFrame.getChatPanel().newSession(session);
		}
		return null;
		
	}

}
