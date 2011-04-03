package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import org.quickconnect.QuickConnect;

import beans.SessionBean;

public class ChatSessionPanel extends JPanel {

	private static final int USERS_TABLE_MAX_HEIGHT = 300;
	private static final int USERS_TABLE_MAX_WIDTH = 200;
	private static final int CHAT_BOX_HEIGHT = 350;
	private static final int CHAT_BOX_WIDTH = 500;
	
	private static Vector<String> columnNames;
	
	JTable usersTable;
	JTextArea chatBox;
	JScrollPane usersTableScroll;
	JScrollPane chatScroll;
	JPanel leftPanel;
	JButton banButton;
	SessionBean session;
	
	static {
		columnNames = new Vector<String>();
		columnNames.add("User");
		columnNames.add("Role");
	}
	
	public ChatSessionPanel(){
		
		//instantiate UI elements
		usersTable = new JTable(new StandardTableModel(new Vector(),columnNames));
		populateUsersTable();
		usersTableScroll = new JScrollPane(usersTable);
		usersTableScroll.setPreferredSize(new Dimension(USERS_TABLE_MAX_WIDTH,USERS_TABLE_MAX_HEIGHT));
		chatScroll = new JScrollPane();
		chatBox = new JTextArea();
		chatScroll.setViewportView(chatBox);
		chatBox.setEditable(false);
		chatBox.setWrapStyleWord(true);
		chatBox.setLineWrap(true);
		chatBox.setBackground(Color.WHITE);
		chatBox.setBorder(BorderFactory.createLineBorder(Color.black));
		chatBox.setPreferredSize(new Dimension(CHAT_BOX_WIDTH,CHAT_BOX_HEIGHT));
		
		banButton = new JButton("Ban User");
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.add(usersTableScroll);
		leftPanel.add(banButton);
		
		//Organize elements
		this.add(leftPanel);
		this.add(chatBox);
		
	}

	public ChatSessionPanel(SessionBean newSession) {
		this();
		this.session = newSession;
	}

	private Vector<String[]> getSessionUsersList() {
		// TODO return list of users in the current session
		Vector vector = new Vector();
		
		return vector;
	}
	
	public SessionBean getSessionObject(){
		return session;
	}
	
	public void setSessionObject(SessionBean bean){
		this.session = bean;
	}

	public void setUsersList(Vector users) {
		((StandardTableModel)usersTable.getModel()).setItems(users);
	}
	
	public void populateUsersTable() {
		HashMap map = new HashMap();
		ArrayList al = new ArrayList();
		map.put("session", this.getSessionObject().getSessionId());
		al.add(MainFrame.mainFrame);
		al.add(map);
		QuickConnect.handleRequest("", al);
	}
	
	
	
}
