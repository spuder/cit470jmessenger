package quickConnect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;
import Client.ClientController;
import beans.CommunicationBean;

public class AddUserToServerBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

			ClientController controller = MainFrame.mainFrame.getController();
			HashMap params = (HashMap) arg0.get(1);
			
			CommunicationBean commBean = new CommunicationBean();
			commBean.setCommand("remoteAddUser");
			
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
