package ui;

import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

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
		usersTable = new JTable(new StandardTableModel(getUsersList(),columnNames));
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
		
	}

	private Vector<String[]> getUsersList() {
		// TODO Get list of users from DB
		Vector vector = new Vector();
		
		//Test Data
		String[] user1 = {"sonken","Admin"};
		String[] user2 = {"shood", "Admin"};
		vector.add(user1);
		vector.add(user2);
	
		return vector;
	}
	
}
