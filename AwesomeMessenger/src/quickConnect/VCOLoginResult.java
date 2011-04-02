package quickConnect;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;

public class VCOLoginResult implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		Boolean result = (Boolean)(arg0.get(arg0.size() - 1));
		if(result){
			((MainFrame)arg0.get(0)).setTitle("Palantir - " + arg0.get(1));
			JOptionPane.showMessageDialog(null, "Login Successful");
			((MainFrame)arg0.get(0)).setAll(true);
			if(((MainFrame) arg0.get(0)).getController().getCurUser().getRole().equals("ADMIN")) {
				((MainFrame)arg0.get(0)).getMenuItemAdd().setEnabled(true);
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "You Shall Not Pass");
		}
		return null;
	}

}
