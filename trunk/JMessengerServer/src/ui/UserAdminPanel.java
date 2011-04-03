package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.quickconnect.QuickConnect;

public class UserAdminPanel extends JPanel{

	private static final int UN_FIELD_LENGTH = 10;
	private static final int PW_FIELD_LENGTH = 10;
	
	private static Vector<String> columnNames;
	
	JPanel upperPanel;
	JPanel lowerPanel;
	JTable usersTable;
	JScrollPane usersTableScroll;
	JTextField newUsersNameField;
	JPasswordField newUsersPwField1;
	JPasswordField newUsersPwField2;
	JButton addUserButton;
	JButton deleteUserButton;
	
	static {
		columnNames = new Vector<String>();
		columnNames.add("User");
		columnNames.add("Role");
	}
	
	public UserAdminPanel(){
		
		//Create UI components
		upperPanel = new JPanel();
		lowerPanel = new JPanel();
		usersTable = new JTable(new StandardTableModel(new Vector(),columnNames));
		getUsersList();
		usersTableScroll = new JScrollPane(usersTable);
		newUsersNameField = new JTextField();
		newUsersNameField.setColumns(UN_FIELD_LENGTH);
		newUsersPwField1 = new JPasswordField();
		newUsersPwField1.setColumns(PW_FIELD_LENGTH);
		newUsersPwField2 = new JPasswordField();
		newUsersPwField2.setColumns(PW_FIELD_LENGTH);
		addUserButton = new JButton("Add User");
		deleteUserButton = new JButton("Delete User");
		
		//Add components
		upperPanel.setLayout(new GridLayout(0,2));
		upperPanel.add(new JLabel("Enter New Username:"));
		upperPanel.add(newUsersNameField);
		upperPanel.add(new JLabel("Enter Password:"));
		upperPanel.add(newUsersPwField1);
		upperPanel.add(new JLabel("Reenter Password:"));
		upperPanel.add(newUsersPwField2);
		upperPanel.add(addUserButton);
		
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
		lowerPanel.add(usersTableScroll);
		lowerPanel.add(deleteUserButton);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(upperPanel);
		this.add(lowerPanel);
		
		addUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addUser();
				
			}
			
		});
		
	}
	
	public void setUsersList(Vector users){
		((StandardTableModel)usersTable.getModel()).setItems(users);
	}

	private void getUsersList() {
		ArrayList al = new ArrayList();
		al.add(MainFrame.mainFrame);
		QuickConnect.handleRequest("getUsersList", al);
	}
	
	private void addUser() {
		//if (newUsersPwField1.getPassword() == newUsersPwField2.getPassword()) {
			ArrayList al = new ArrayList();
			al.add(MainFrame.mainFrame);
			HashMap map = new HashMap();
			MessageDigest md;
			String hashString = null;
			try {
				md = MessageDigest.getInstance("SHA1");
				md.update(new String(newUsersPwField1.getPassword()).getBytes());
				hashString = convToHex(md.digest());
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			map.put("username", newUsersNameField.getText());
			map.put("password", hashString);
			map.put("role", "USER");
			al.add(map);
			QuickConnect.handleRequest("addUser", al);
		//}
	}
	
	private static String convToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                        buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while(two_halfs++ < 1);
        }
        return buf.toString();
    }

	
}
