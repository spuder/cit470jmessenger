package qc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.quickconnect.ControlObject;

import beans.CommunicationBean;

public class SendUserListBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		CommunicationBean bean = new CommunicationBean();
		bean.setCommand("userListResponse");
		HashMap parameters = new HashMap();
		Vector vector = (Vector) arg0.get(arg0.size()-1);
		parameters.put("list", vector);
		bean.setParameters(parameters);
		return bean;
	}

}
