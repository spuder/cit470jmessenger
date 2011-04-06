package qc;

import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import beans.CommunicationBean;
import beans.MessageBean;

public class SendSessionLoginPromptECO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		HashMap map = (HashMap) arg0.get(1);
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("getSessionPassword");		
		commBean.setParameters(map);
		
		return commBean;
	}

}
