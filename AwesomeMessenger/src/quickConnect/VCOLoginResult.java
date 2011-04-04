package quickConnect;

import java.io.ObjectInputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.quickconnect.ControlObject;

import Client.ClientController;

import userInterface.MainFrame;

public class VCOLoginResult implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		Boolean result = (Boolean)(arg0.get(arg0.size() - 1));
		if(result){
			MainFrame.mainFrame.setTitle("Palantir [" + arg0.get(1) + "]");
			JOptionPane.showMessageDialog(null, "Login Successful");
			ClientController controller = MainFrame.mainFrame.getController();
			
			//Spawn separate thread for inputstream
			controller.newThread((ObjectInputStream)controller.getiStream(), MainFrame.mainFrame);
			MainFrame.mainFrame.setAll(true);
			if(MainFrame.mainFrame.getController().getCurUser().getRole().equals("ADMIN")) {
				MainFrame.mainFrame.getMenuItemAdd().setEnabled(true);
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "You Shall Not Pass");
		}
		return null;
	}

}
