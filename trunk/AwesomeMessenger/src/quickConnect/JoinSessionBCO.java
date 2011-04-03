package quickConnect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.quickconnect.ControlObject;

import beans.CommunicationBean;

import userInterface.MainFrame;
import Client.ClientController;

public class JoinSessionBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		
		if(!(arg0.get(arg0.size()-1).getClass() == Boolean.class)){
			String idToJoin = (String) arg0.get(arg0.size()-1);
			ClientController controller = MainFrame.mainFrame.getController();
			
			CommunicationBean commBean = new CommunicationBean();
			commBean.setCommand("joinSession");
			
			HashMap params = new HashMap();
			params.put("sessionId", idToJoin);
			commBean.setParameters(params);
			try {
				controller.getoStream().writeObject(commBean);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Failed to contact server");
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
