package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame{
	
	private static final int MAIN_FRAME_WIDTH = 800;
	private static final int MAIN_FRAME_HEIGHT = 500;

	JTabbedPane tabPane;
	ChatPanel chatPanel;
	UserAdminPanel adminPanel;
	
	//Menu
	JMenuBar menuBar;
	JMenu fileMenu, sessionsMenu;
	JMenuItem exitItem, newSessionItem, closeSessionItem;

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
		
		buildMenu();
		setJMenuBar(menuBar);
		
		//Set Frame Settings
		this.setTitle("Palantir Server");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
		this.setVisible(true);
		
	}
	
	
	private void buildMenu() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		sessionsMenu = new JMenu("Sessions");
		exitItem = new JMenuItem("Exit");
		newSessionItem = new JMenuItem("New Session");
		closeSessionItem = new JMenuItem("Close Session");
		
		menuBar.add(fileMenu);
		menuBar.add(sessionsMenu);
		
		fileMenu.add(exitItem);
		sessionsMenu.add(newSessionItem);
		sessionsMenu.add(closeSessionItem);
		
		newSessionItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				chatPanel.newSession();
			}
		});
		
		closeSessionItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				chatPanel.closeSession(chatPanel.getTabbedPane().getSelectedIndex());
			}			
		});
		
		exitItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
	}


	public static void main(String args[]){
		MainFrame mf = new MainFrame();
	}
}