package quickConnect;

import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;

import beans.SessionBean;

public class UpdateSessionsVCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		HashMap params = (HashMap) arg0.get(0);
		SessionBean session = (SessionBean) params.get("session");
		MainFrame.mainFrame.newSession(session);
		
		return null;
	}

}
