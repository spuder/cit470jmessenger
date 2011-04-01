package userInterface;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	public static final int MESSENGER_PANEL_HEIGHT = 400;
	public static final int MESSENGER_PANEL_WIDTH = 300;
	public static final int FILE_PANEL_HEIGHT = 400;
	public static final int FILE_PANEL_WIDTH = 250;
	
	
	MessengerPanel aMessengerPanel;
	
	@SuppressWarnings("unchecked")
	public MainFrame() {

		Vector vector = new Vector();
		
		//TODO Test Data: Delete on production
		String[] set1 = {"main.java","tyler","10:22:33"};
		String[] set2 = {"messagePanel.java", "sam", "11:22:31"};
		String[] set3 = {"filePanel.java", "skyler", "12:22:22"};
		vector.add(set1);
		vector.add(set2);
		vector.add(set3);

		//Create panels
		aMessengerPanel = new MessengerPanel(MESSENGER_PANEL_WIDTH, MESSENGER_PANEL_HEIGHT);
		FilePanel aFilePanel = new FilePanel(FILE_PANEL_WIDTH, FILE_PANEL_HEIGHT, vector);
		aFilePanel.setPreferredSize(new Dimension(FILE_PANEL_WIDTH + 10, FILE_PANEL_HEIGHT));
		
		//Configure Layout
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setSize(500,500);
		this.setLocationRelativeTo(null);
		
		//Set Frame Settings
		this.setTitle("Palantir - ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Organize Interface
		this.add(aMessengerPanel);
		this.add(aFilePanel);
		this.pack();
		this.setVisible(true);
		
	}
	public static void main(String[] args) {
		MainFrame bob = new MainFrame();
	}

}
