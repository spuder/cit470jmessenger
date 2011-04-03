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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

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
	JButton changeUserRoleButton;
	JPanel buttonPanel;
	
	static {
		columnNames = new Vector<String>();
		columnNames.add("User");
		columnNames.add("Role");
	}
	
	public UserAdminPanel(){
		
		//Create UI components
		upperPanel = new JPanel();
		lowerPanel = new JPanel();
		buttonPanel = new JPanel();
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
		changeUserRoleButton = new JButton("Change Role");
		
		//Add components
		upperPanel.setLayout(new GridLayout(0,2));
		upperPanel.add(new JLabel("Enter New Username:"));
		upperPanel.add(newUsersNameField);
		upperPanel.add(new JLabel("Enter Password:"));
		upperPanel.add(newUsersPwField1);
		upperPanel.add(new JLabel("Reenter Password:"));
		upperPanel.add(newUsersPwField2);
		upperPanel.add(addUserButton);
		
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(deleteUserButton);
		buttonPanel.add(changeUserRoleButton);
		
		lowerPanel.setLayout(new BoxLayout(lowerPanel, BoxLayout.Y_AXIS));
		lowerPanel.add(usersTableScroll);
		lowerPanel.add(buttonPanel);
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(upperPanel);
		this.add(lowerPanel);
		
		addUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addUser();
				
			}
			
		});
		
		deleteUserButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteUser();
				
			}
			
		});
		
		changeUserRoleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				updateUser();
				
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
	
	
	
	public JTextField getNewUsersNameField() {
		return newUsersNameField;
	}

	public void setNewUsersNameField(JTextField newUsersNameField) {
		this.newUsersNameField = newUsersNameField;
	}

	public JPasswordField getNewUsersPwField1() {
		return newUsersPwField1;
	}

	public void setNewUsersPwField1(JPasswordField newUsersPwField1) {
		this.newUsersPwField1 = newUsersPwField1;
	}

	public JPasswordField getNewUsersPwField2() {
		return newUsersPwField2;
	}

	public void setNewUsersPwField2(JPasswordField newUsersPwField2) {
		this.newUsersPwField2 = newUsersPwField2;
	}

	private void addUser() {
		
		String pass1 = new String(newUsersPwField1.getPassword()).trim();
		String pass2 = new String(newUsersPwField2.getPassword()).trim();
		if (!newUsersNameField.getText().trim().equals("") && !pass1.equals("") && !pass2.equals("")) {
			if (pass1.equals(pass2)) {
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
			}
		}
	}
	
	private void deleteUser() {
		
		if(usersTable.getSelectedRow() != -1){
			HashMap map = new HashMap();
			ArrayList al = new ArrayList();
			String user = (String) usersTable.getValueAt(usersTable.getSelectedRow(), 0);
			map.put("username", user);
			al.add(MainFrame.mainFrame);
			al.add(map);
			QuickConnect.handleRequest("deleteUser", al);
		}
	}
	
	private void updateUser() {
		
		if(usersTable.getSelectedRow() != -1){
			HashMap map = new HashMap();
			ArrayList al = new ArrayList();
			String role = "";
			role = (String) JOptionPane.showInputDialog(MainFrame.mainFrame, "Please choose a Role", "Change User Role",
			JOptionPane.QUESTION_MESSAGE, null, new Object[] { "ADMIN", "USER" }, "USER");
			if (role.equals("ADMIN") || role.equals("USER")) {
				String user = (String) usersTable.getValueAt(usersTable.getSelectedRow(), 0);
				map.put("username", user);
				map.put("role", role);
				al.add(MainFrame.mainFrame);
				al.add(map);
				QuickConnect.handleRequest("updateRole", al);
			}
		}
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
