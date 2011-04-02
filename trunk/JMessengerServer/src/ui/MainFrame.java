package ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame{
	
	private static final int MAIN_FRAME_WIDTH = 800;
	private static final int MAIN_FRAME_HEIGHT = 500;

	JTabbedPane tabPane;
	ChatPanel chatPanel;
	UserAdminPanel adminPanel;

	public MainFrame(){
		
		//Set size
		Dimension dim = new Dimension(MAIN_FRAME_WIDTH,MAIN_FRAME_HEIGHT);
		this.setPreferredSize(dim);
		
		//Build Panes
		tabPane = new JTabbedPane();
		chatPanel = new ChatPanel();
		adminPanel = new UserAdminPanel();
		
		tabPane.addTab("User Admin", adminPanel);
		tabPane.addTab("Chat Sessions", chatPanel);
		
		this.add(tabPane);
		
		//Set Frame Settings
		this.setTitle("Palantir Server");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
		this.setVisible(true);
		
	}
	
	
	public static void main(String args[]){
		MainFrame mf = new MainFrame();
	}
}