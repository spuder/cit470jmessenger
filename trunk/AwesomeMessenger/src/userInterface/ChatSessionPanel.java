package userInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JPanel;

import org.quickconnect.QuickConnect;

import beans.SessionBean;

public class ChatSessionPanel extends JPanel {
	public static final int MESSENGER_PANEL_HEIGHT = 400;
	public static final int MESSENGER_PANEL_WIDTH = 300;
	public static final int FILE_PANEL_HEIGHT = 400;
	public static final int FILE_PANEL_WIDTH = 250;
	SessionBean session;
	MessengerPanel aMessengerPanel;
	FilePanel aFilePanel;

	public ChatSessionPanel()  {
		Vector vector = new Vector();

		//Create panels
		aMessengerPanel = new MessengerPanel(MESSENGER_PANEL_WIDTH, MESSENGER_PANEL_HEIGHT);
		aFilePanel = new FilePanel(FILE_PANEL_WIDTH, FILE_PANEL_HEIGHT, vector);
		aFilePanel.setPreferredSize(new Dimension(FILE_PANEL_WIDTH + 10, FILE_PANEL_HEIGHT));
		this.add(aMessengerPanel);
		this.add(aFilePanel);
		
		
		aMessengerPanel.sendMessageButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
	}
	
	public ChatSessionPanel(SessionBean newSession) {
		this();
		this.session = newSession;
	}

}
