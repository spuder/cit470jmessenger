package userInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import org.quickconnect.QuickConnect;

import Client.ClientController;

import quickConnect.QCCommandMappings;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static final int MAIN_HEIGHT = 500;
	public static final int MAIN_WIDTH = 500;

	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu chat = new JMenu("Chat");
	private JMenu moderation = new JMenu("Moderation");

	// Menu items.
	private JMenuItem menuItemNew     = new JMenuItem("New");
	private JMenuItem menuItemOpen    = new JMenuItem("Open");
	private JMenuItem menuItemExit   = new JMenuItem("Exit");
	private JMenuItem menuItemSave    = new JMenuItem("Save");
	private JMenuItem menuItemLogin  = new JMenuItem("Login");
	private JMenuItem menuItemServer    = new JMenuItem("Server");
	private JMenuItem menuItemChatSession    = new JMenuItem("Chat Session");
	
	private JMenuItem menuItemRemove   = new JMenuItem("Close");
	private JMenuItem menuItemBan    = new JMenuItem("Save");
	private JMenuItem menuItemGrant  = new JMenuItem("Login");
	private JMenuItem menuItemClose    = new JMenuItem("Server");
	private JMenuItem menuItemAdd    = new JMenuItem("Add User");
	ClientController controller = new ClientController();

	JTabbedPane tabs = new JTabbedPane();

	@SuppressWarnings("unchecked")
	public MainFrame() {
		// initialize quick connect
		QCCommandMappings.mapCommands();

		//Configure Layout
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setSize(MAIN_WIDTH,MAIN_HEIGHT);
		this.setLocationRelativeTo(null);


		//Set Frame Settings
		this.setTitle("Palantir");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Organize Interface
		buildMenu();
		newSession();
		this.add(tabs);
		this.pack();
		this.setVisible(true);

		// ***********************************************************************************
		// ********************************** ACTION LISTENERS *******************************
		// ***********************************************************************************

		//		aMessengerPanel.sendMessageButton.addActionListener(new ActionListener(){
		//			public void actionPerformed(ActionEvent e){
		//				//aMessengerPanel.send();
		//			}
		//		});
		menuItemNew.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// New file.
						// newFile();

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
						// Open file.
						// openFile();
					} 
				});
		menuItemLogin.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String uName = JOptionPane.showInputDialog("enter username");
						String pWord = JOptionPane.showInputDialog("enter password");
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
					}
				});
		menuItemChatSession.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						newSession();
					}
				});
		// ***********************************************************************************
		// *************************** END OF ACTION LISTENERS *******************************
		// ***********************************************************************************
	}

	public void newSession() {
		ChatSessionPanel newPanel = new ChatSessionPanel();
		tabs.addTab("test", newPanel);

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
		file.addSeparator();
		file.add(menuItemOpen);
		file.add(menuItemSave);
		file.add(menuItemExit);

		// Menu items for chat menu.
		chat.add(menuItemLogin);
		chat.add(menuItemServer);
		chat.add(menuItemChatSession);

		moderation.add(menuItemRemove);
		moderation.add(menuItemBan);
		moderation.add(menuItemGrant);
		moderation.add(menuItemClose);
		file.addSeparator();
		moderation.add(menuItemAdd);
	
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

	public void setMenuItemAdd(JMenuItem menuItemAdd) {
		this.menuItemAdd = menuItemAdd;
	}

}
