package ui;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class ChatPanel extends JPanel{

	ArrayList<ChatSessionPanel> chatSessions;
	JTabbedPane tabs;
	
	public ChatPanel(){
		
		tabs = new JTabbedPane();
		tabs.addTab("Session 1", new ChatSessionPanel());
		tabs.setSize(this.getSize());
		
		//Organize UI
		this.add(tabs);
	}
	
}
