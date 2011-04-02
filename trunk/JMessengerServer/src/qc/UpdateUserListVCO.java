package qc;

import java.util.ArrayList;
import java.util.Vector;

import org.quickconnect.ControlObject;

import ui.MainFrame;

public class UpdateUserListVCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		Vector users = (Vector) arg0.get(arg0.size()-1);
		MainFrame.mainFrame.getAdminPanel().setUsersList(users);
		return null;
	}

}
