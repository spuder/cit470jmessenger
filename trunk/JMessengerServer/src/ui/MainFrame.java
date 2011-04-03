package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import org.quickconnect.QuickConnect;

import controller.ServerController;

import qc.QCCommandMappings;

public class MainFrame extends JFrame{
	
	private static final int MAIN_FRAME_WIDTH = 800;
	private static final int MAIN_FRAME_HEIGHT = 500;
	public static MainFrame mainFrame;

	JTabbedPane tabPane;
	ChatPanel chatPanel;
	UserAdminPanel adminPanel;
	
	//Menu
	JMenuBar menuBar;
	JMenu fileMenu, sessionsMenu;
	JMenuItem exitItem, newSessionItem, closeSessionItem;
	
	ServerController controller;

	public MainFrame(){
		
		mainFrame = this;
		
		//Setup QC
		QCCommandMappings.mapCommands();
		
		//Setup Controller
		int port = Integer.parseInt(JOptionPane.showInputDialog("Enter Port:"));
		String un = JOptionPane.showInputDialog("Enter MySQL Username:");
		String pw = JOptionPane.showInputDialog("Enter MySQL Password:");
		controller = new ServerController(port,un,pw);
		
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
		
		controller.startServer();
		
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
				String name = JOptionPane.showInputDialog("New Session Name:");
				HashMap facadeMap = new HashMap();
				facadeMap.put("sessionName", name);
				ArrayList params = new ArrayList();
				params.add(this);
				params.add(facadeMap);
				QuickConnect.handleRequest("localCreateSession", params);
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
				controller.shutdownServer();
				System.exit(EXIT_ON_CLOSE);
			}
		});
	}

	public ChatPanel getChatPanel() {
		return chatPanel;
	}

	public void setChatPanel(ChatPanel chatPanel) {
		this.chatPanel = chatPanel;
	}

	public UserAdminPanel getAdminPanel() {
		return adminPanel;
	}

	public void setAdminPanel(UserAdminPanel adminPanel) {
		this.adminPanel = adminPanel;
	}

	public ServerController getController() {
		return controller;
	}

	public void setController(ServerController controller) {
		this.controller = controller;
	}

	public static void main(String args[]){		
		MainFrame mf = new MainFrame();
	}
}