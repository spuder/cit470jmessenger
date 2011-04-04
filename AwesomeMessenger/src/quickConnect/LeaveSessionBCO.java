package quickConnect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;
import beans.CommunicationBean;

public class LeaveSessionBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("leaveUser");
		HashMap params = new HashMap();
		params.put("sessionId", (String)arg0.get(2));
		
		commBean.setParameters(params);
		
		try {
			MainFrame.mainFrame.getController().getoStream().writeObject(commBean);
			return true;
		} catch (IOException e) {
			System.out.println("Failed to Contact Server");
			e.printStackTrace();
		}
		
		return false;
		
	}


}
