package userInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JPanel;

import org.quickconnect.QuickConnect;

public class ChatSessionPanel extends JPanel {
	public static final int MESSENGER_PANEL_HEIGHT = 400;
	public static final int MESSENGER_PANEL_WIDTH = 300;
	public static final int FILE_PANEL_HEIGHT = 400;
	public static final int FILE_PANEL_WIDTH = 250;
	
	MessengerPanel aMessengerPanel;
	FilePanel aFilePanel;

	public ChatSessionPanel()  {
		Vector vector = new Vector();
		String[] set1 = {"main.java","tyler","10:22:33"};
		String[] set2 = {"messagePanel.java", "sam", "11:22:31"};
		String[] set3 = {"filePanel.java", "skyler", "12:22:22"};
		vector.add(set1);
		vector.add(set2);
		vector.add(set3);

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
	
	

}
