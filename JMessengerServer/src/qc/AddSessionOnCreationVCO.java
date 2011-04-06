package qc;

import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;
import beans.CommunicationBean;
import beans.SessionBean;

public class AddSessionOnCreationVCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		System.out.println("Server: Adding Session Tab");
		//get the communication bean sent back with the success message
		CommunicationBean comBean = (CommunicationBean) arg0.get(2);
		HashMap params = comBean.getParameters();
		SessionBean session = (SessionBean) params.get("session");
		boolean success = (Boolean) params.get("success");
		if(success){
			MainFrame.mainFrame.getChatPanel().newSession(session);
		}
		return null;
	}

}
