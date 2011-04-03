package quickConnect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.quickconnect.ControlObject;

import beans.CommunicationBean;
import beans.UserBean;

import userInterface.MainFrame;

import Client.ClientController;

public class CreateSessionBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		ClientController controller = MainFrame.mainFrame.getController();
		String chatName = (String) arg0.get(arg0.size()-1);
		
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("createSession");
		
		HashMap params = new HashMap();
		params.put("sessionName", chatName);

		try {
			controller.getoStream().writeObject(commBean);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to contact server");
			e.printStackTrace();
		}
		
		return null;
	}
}