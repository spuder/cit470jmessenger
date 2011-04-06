package quickConnect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;
import Client.ClientController;
import beans.CommunicationBean;

public class JoinPasswordProtectedSessionBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		String password = (String) arg0.get(arg0.size()-1);
		ClientController controller = MainFrame.mainFrame.getController();
		
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("joinSession");
		
		HashMap params = (HashMap) arg0.get(0);
		params.put("password", password);
		commBean.setParameters(params);
		try {
			controller.getoStream().writeObject(commBean);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to contact server");
			e.printStackTrace();
		}
		
		return null;
	}

}
