package userInterface;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static final int MESSENGER_PANEL_HEIGHT = 400;
	public static final int MESSENGER_PANEL_WIDTH = 300;
	public static final int FILE_PANEL_HEIGHT = 400;
	public static final int FILE_PANEL_WIDTH = 250;
	public static final int MAIN_HEIGHT = 500;
	public static final int MAIN_WIDTH = 500;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu chat = new JMenu("Chat");
	

	// Menu items.
	private JMenuItem menuItemNew     = new JMenuItem("New");
	private JMenuItem menuItemOpen    = new JMenuItem("Open");
	private JMenuItem menuItemClose   = new JMenuItem("Close");
	private JMenuItem menuItemSave    = new JMenuItem("Save");
	private JMenuItem menuItemLogin  = new JMenuItem("Login");
	private JMenuItem menuItemServer    = new JMenuItem("Server");
	
	MessengerPanel aMessengerPanel;
	FilePanel aFilePanel;

	String username;

	@SuppressWarnings("unchecked")
	public MainFrame() {
		// initialize quickconnect
		QCCommandMappings.mapCommands();
		@SuppressWarnings("rawtypes")
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
		aFilePanel = new FilePanel(FILE_PANEL_WIDTH, FILE_PANEL_HEIGHT, vector);
		aFilePanel.setPreferredSize(new Dimension(FILE_PANEL_WIDTH + 10, FILE_PANEL_HEIGHT));

		//Configure Layout
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setSize(MAIN_WIDTH,MAIN_HEIGHT);
		this.setLocationRelativeTo(null);

		//Set Frame Settings
		this.setTitle("Palantir");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Organize Interface
		buildMenu();
		this.add(aMessengerPanel);
		this.add(aFilePanel);
		this.pack();
		this.setVisible(true);
		aMessengerPanel.sendMessageButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//aMessengerPanel.send();
			}
		});
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
		menuItemClose.addActionListener(
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
						// Open file.
						// openFile();

					} 
				});
		menuItemServer.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						// Open file.
						// openFile();

					}
				});
	}
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		MainFrame bob = new MainFrame();
	}
	private JMenuBar buildMenu()
	{
		// Add menus to the menu bar.
		menuBar.add(file);
		menuBar.add(chat);


		// Set mnemonics for menu selections.
		file.setMnemonic('F');
		chat.setMnemonic('C');

		// Menu items for file menu.
		file.add(menuItemNew);
		file.addSeparator();
		file.add(menuItemOpen);
		file.add(menuItemSave);
		file.add(menuItemClose);
		
		// Menu items for comm menu.
		chat.add(menuItemLogin);
		chat.add(menuItemServer);

		// Set mnemonics for menu item selections for file menu.
		menuItemNew.setMnemonic('N');
		menuItemOpen.setMnemonic('O');
		menuItemClose.setMnemonic('C');
		menuItemSave.setMnemonic('S');

		// Set mnemonics for menu item selections for chat menu.
		menuItemLogin.setMnemonic('L');
		menuItemServer.setMnemonic('S');

		// Set the menu bar in the frame and return menu bar.
		setJMenuBar(menuBar);

		// Return JMenuBar
		return menuBar;

	} // End of buildMenu() method.

}
