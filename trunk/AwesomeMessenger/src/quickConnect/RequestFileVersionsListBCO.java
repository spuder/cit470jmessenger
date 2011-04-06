package quickConnect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;
import beans.CommunicationBean;

public class RequestFileVersionsListBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("getFileVersions");
		HashMap params = (HashMap) arg0.get(0);
		commBean.setParameters(params);
		
		try {
			MainFrame.mainFrame.getController().getoStream().writeObject(commBean);
		} catch (IOException e) {
			System.out.println("Failed to Contact Server");
			e.printStackTrace();
		}
				
		return null;
		
	}

}
