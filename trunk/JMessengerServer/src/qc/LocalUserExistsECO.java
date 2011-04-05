package qc;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.quickconnect.ControlObject;

public class LocalUserExistsECO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		JOptionPane.showMessageDialog(null, "User Already Exists");
		
		return null;
	}

}
