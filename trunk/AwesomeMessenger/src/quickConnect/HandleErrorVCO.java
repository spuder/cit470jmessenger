package quickConnect;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.quickconnect.ControlObject;

import beans.MessageBean;

public class HandleErrorVCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		HashMap params = (HashMap) arg0.get(0);
		MessageBean msg = (MessageBean) params.get("message");
		
		JOptionPane.showMessageDialog(null, msg.getMessage());
		
		return null;
	}

}
