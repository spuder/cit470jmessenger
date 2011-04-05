package qc;

import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import beans.CommunicationBean;
import beans.MessageBean;

public class SendErrorMessageECO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		HashMap map = (HashMap) arg0.get(1);
		String message = (String) map.get("error");
		CommunicationBean commBean = new CommunicationBean();
		MessageBean msg = new MessageBean("SERVER", message);
		commBean.setCommand("error");
		
		HashMap params = new HashMap();
		
		return null;
	}

}
