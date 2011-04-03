package quickConnect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;

import beans.CommunicationBean;

public class RequestSessionListBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("sessionList");
		HashMap params = new HashMap();
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
