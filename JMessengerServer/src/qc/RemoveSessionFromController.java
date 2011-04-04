package qc;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

public class RemoveSessionFromController implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		HashMap params1 = (HashMap) arg0.get(0);
		String id = (String) params1.get("sessionId");
		HashMap<String, HashMap<String, ObjectOutputStream>> sessions = MainFrame.mainFrame.getController().getConnectionMap();
		
		sessions.remove(id);
		
		return true;
	}

}
