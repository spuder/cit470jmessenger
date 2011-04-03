package quickConnect;

import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import beans.SessionBean;

public class ParseSessionIdBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		HashMap params = (HashMap) arg0.get(0);
		boolean success = (Boolean) params.get("success");
		if(success){
			SessionBean session = (SessionBean) params.get("session");
			return session.getSessionId();
		} else {
			return false;
		}
		
	}

}
