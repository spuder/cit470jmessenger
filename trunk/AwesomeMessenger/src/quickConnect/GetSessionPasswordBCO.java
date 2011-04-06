package quickConnect;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;

public class GetSessionPasswordBCO implements ControlObject {

	String password = "";
	
	@Override
	public Object handleIt(ArrayList<Object> arg0) {
				
		//= JOptionPane.showInputDialog("Please Enter Session Password:");
		getPassword();
		
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
	
	public void getPassword(){

			final JDialog dialog = new JDialog(MainFrame.mainFrame, "User Login");
			dialog.setLayout(new FlowLayout());
			final JLabel pWordLabel = new JLabel("Password:");
			final JPasswordField pWordInput = new JPasswordField(15);
			JButton ok = new JButton("Ok");

			JPanel panel1 = new JPanel(new GridLayout(2, 2, 10, 10));
			//		JPanel panel2 = new JPanel();
			//		JPanel panel3 = new JPanel();

			panel1.add(pWordLabel);
			panel1.add(pWordInput);
			panel1.add(Box.createGlue());
			panel1.add(ok);

			dialog.add(panel1);
			
			ActionListener go = new ActionListener()
			{
				@SuppressWarnings("unchecked")
				public void actionPerformed(ActionEvent e)
				{


					char[] pWord = pWordInput.getPassword();


					password = new String(pWord);
					dialog.setVisible(false);
					dialog.dispose();
				}
			};

			pWordInput.addActionListener(go);
			ok.addActionListener(go);
			dialog.setModal(true);
			dialog.setLocationRelativeTo(null);
			dialog.pack();
			dialog.setVisible(true);
	}

}
