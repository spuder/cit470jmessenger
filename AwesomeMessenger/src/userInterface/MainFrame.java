package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import org.quickconnect.QuickConnect;

import beans.SessionBean;

import Client.ClientController;

import quickConnect.QCCommandMappings;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static final int MAIN_HEIGHT = 500;
	public static final int MAIN_WIDTH = 500;
	public static MainFrame mainFrame;
	
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu chat = new JMenu("Chat");
	private JMenu moderation = new JMenu("Moderation");

	// Menu items.
	private JMenuItem menuItemNew     = new JMenuItem("New Chat Session");
	private JMenuItem menuItemOpen    = new JMenuItem("Open");
	private JMenuItem menuItemExit   = new JMenuItem("Exit");
	private JMenuItem menuItemSave    = new JMenuItem("Save");
	private JMenuItem menuItemLogin  = new JMenuItem("Login");
	private JMenuItem menuItemServer    = new JMenuItem("Configure Server");
	private JMenuItem menuItemChatSession    = new JMenuItem("Join Chat");
	private JMenuItem menuItemLeaveSession = new JMenuItem("Leave Session");

	private JMenuItem menuItemRemove   = new JMenuItem("Remove File");
	private JMenuItem menuItemBan    = new JMenuItem("Ban User");
	private JMenuItem menuItemGrant  = new JMenuItem("Make Moderator");
	private JMenuItem menuItemClose    = new JMenuItem("Close Session");
	private JMenuItem menuItemAdd    = new JMenuItem("Add User");
	ClientController controller = new ClientController();

	JTabbedPane tabs = new JTabbedPane();
	ArrayList<ChatSessionPanel> chatSessions = new ArrayList<ChatSessionPanel>();
	
	@SuppressWarnings("unchecked")
	public MainFrame() {
		// initialize quick connect
		QCCommandMappings.mapCommands();
		MainFrame.mainFrame = this;

		//Configure Layout
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setSize(MAIN_WIDTH,MAIN_HEIGHT);
		this.setLocationRelativeTo(null);


		//Set Frame Settings
		this.setTitle("Palantir");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Organize Interface
		buildMenu();
		setAll(false);
		this.add(tabs);

		this.pack();
		this.setVisible(true);

		// ***********************************************************************************
		// ********************************** ACTION LISTENERS *******************************
		// ***********************************************************************************

		menuItemNew.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String name = JOptionPane.showInputDialog("Enter New Chat Session Name");
						if(name != null || !name.equals("")){
							ArrayList params = new ArrayList();
							params.add(name);
							QuickConnect.handleRequest("createSession", params);
						}
					} // End of actionPerformed method.
				}); // End of menuItemNew action listener.

		menuItemOpen.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// Open file.
						// openFile();
					} 
				}); 
		menuItemSave.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// Open file.
						// openFile();
					} 
				});
		menuItemExit.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(getController().getSocket() != null){
							try {
								getController().getSocket().close();
							} catch (IOException e1) {
							}
						}
						System.exit(EXIT_ON_CLOSE);
					} 
				});
		menuItemLogin.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String uName = JOptionPane.showInputDialog("Enter Username");
						String pWord = JOptionPane.showInputDialog("Enter Password");
						ArrayList params = new ArrayList();
						params.add(this);
						params.add(uName);
						params.add(pWord);
						QuickConnect.handleRequest("login", params);
					} 
				});
		menuItemServer.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						controller.setIpAddress(JOptionPane.showInputDialog("Enter Server IP:"));
						controller.setPort(Integer.parseInt(JOptionPane.showInputDialog("Enter Server Port:")));
						menuItemLogin.setEnabled(true);
					}
				});
		menuItemChatSession.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						ArrayList params = new ArrayList();
						QuickConnect.handleRequest("sessionList", params);
					}
				});
		// ***********************************************************************************
		// *************************** END OF ACTION LISTENERS *******************************
		// ***********************************************************************************
	}

	public void newSession(SessionBean newSession) {
		ChatSessionPanel newPanel = new ChatSessionPanel(newSession);
		tabs.addTab(newSession.getSessionName(), newPanel);
		chatSessions.add(newPanel);
		this.repaint();
		this.pack();
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MainFrame mf = new MainFrame();
	}
	private JMenuBar buildMenu()
	{
		// Add menus to the menu bar.
		menuBar.add(file);
		menuBar.add(chat);
		menuBar.add(moderation);

		// Set mnemonics for menu selections.
		file.setMnemonic('F');
		chat.setMnemonic('C');
		moderation.setMnemonic('M');

		// Menu items for file menu.
		file.add(menuItemNew);
		file.add(menuItemOpen);
		file.add(menuItemSave);
		file.add(menuItemExit);

		// Menu items for chat menu.
		chat.add(menuItemLogin);
		chat.add(menuItemServer);
		chat.addSeparator();
		chat.add(menuItemNew);
		chat.add(menuItemChatSession);
		chat.addSeparator();
		chat.add(menuItemLeaveSession);

		moderation.add(menuItemRemove);
		moderation.add(menuItemBan);
		moderation.add(menuItemGrant);
		moderation.add(menuItemClose);
		moderation.addSeparator();
		moderation.add(menuItemAdd);
		menuItemAdd.setEnabled(false);

		// Set mnemonics for menu item selections for file menu.
		menuItemNew.setMnemonic('N');
		menuItemOpen.setMnemonic('O');
		menuItemExit.setMnemonic('X');
		menuItemSave.setMnemonic('S');

		// Set mnemonics for menu item selections for chat menu.
		menuItemLogin.setMnemonic('L');
		menuItemServer.setMnemonic('S');
		menuItemChatSession.setMnemonic('C');
		// Set the menu bar in the frame and return menu bar.
		setJMenuBar(menuBar);

		// Return JMenuBar
		return menuBar;

	} // End of buildMenu() method.

	public ClientController getController() {
		return controller;
	}

	public void setController(ClientController controller) {
		this.controller = controller;
	}

	public JMenuItem getMenuItemAdd() {
		return menuItemAdd;
	}

	public void setAll(boolean setter) {
		menuItemNew.setEnabled(setter);
		menuItemOpen.setEnabled(setter);
		menuItemSave.setEnabled(setter);
		menuItemLogin.setEnabled(setter);
		menuItemChatSession.setEnabled(setter);
		menuItemRemove.setEnabled(setter);
		menuItemBan.setEnabled(setter);
		menuItemGrant.setEnabled(setter);
		menuItemClose.setEnabled(setter);
		menuItemLeaveSession.setEnabled(setter);
	}

	public ArrayList<ChatSessionPanel> getChatSessions() {
		return chatSessions;
	}


	
	
}







