package ui;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import beans.SessionBean;

public class ChatPanel extends JPanel{

	ArrayList<ChatSessionPanel> chatSessions;
	JTabbedPane tabs;
	
	public ChatPanel(){
		
		chatSessions = new ArrayList<ChatSessionPanel>();
		tabs = new JTabbedPane();	
		//Organize UI
		tabs.setSize(this.getSize());
		this.add(tabs);
	}

	public void newSession(SessionBean newSession) {
		ChatSessionPanel newPanel = new ChatSessionPanel(newSession);
		tabs.addTab(newSession.getSessionName(), newPanel);
		this.chatSessions.add(newPanel);
		this.repaint();
	}
	
	public JTabbedPane getTabbedPane(){
		return tabs;
	}

	public void closeSession(int selectedIndex) {
		tabs.removeTabAt(selectedIndex);
		//TODO Do other stuff here to stop and close down the session
		chatSessions.remove(selectedIndex);
	}
	
}
