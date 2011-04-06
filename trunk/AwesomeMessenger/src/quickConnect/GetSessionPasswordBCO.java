package quickConnect;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.quickconnect.ControlObject;

public class GetSessionPasswordBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		String password = JOptionPane.showInputDialog("Please Enter Session Password:");
		
		try {
			password = BCOHashPassword.SHA1(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return password;
	}

}
