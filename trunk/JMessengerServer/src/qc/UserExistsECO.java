package qc;

import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import beans.CommunicationBean;
import beans.MessageBean;

public class UserExistsECO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		CommunicationBean commBean = new CommunicationBean();
		MessageBean msg = new MessageBean("SERVER","User Already Exists!");
		commBean.setCommand("error");
		
		HashMap params = new HashMap();
		
		return null;
	}

}
