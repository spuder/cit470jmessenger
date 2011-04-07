package userInterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import org.quickconnect.QuickConnect;
import beans.SessionBean;
import Client.ClientController;
import quickConnect.BCOHashPassword;
import quickConnect.QCCommandMappings;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static final int MAIN_HEIGHT = 500;
	public static final int MAIN_WIDTH = 500;
	public static MainFrame mainFrame;

	private ArrayList params = null;
	private ArrayList serverParams = null;
	private ArrayList addUserParams = null;
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu chat = new JMenu("Chat");
	private JMenu moderation = new JMenu("Moderation");
	private JMenu help = new JMenu("Help");

	// Menu items.
	private JMenuItem menuItemNew     = new JMenuItem("New Chat Session", new ImageIcon(getClass().getResource("newchat.png")));
	private JMenuItem menuItemExit   = new JMenuItem("Exit", new ImageIcon(getClass().getResource("exit.png")));
	private JMenuItem menuItemSave    = new JMenuItem("Save", new ImageIcon(getClass().getResource("save.png")));
	private JMenuItem menuItemLogin  = new JMenuItem("Login", new ImageIcon(getClass().getResource("securelogin.png")));
	private JMenuItem menuItemServer    = new JMenuItem("Configure Server", new ImageIcon(getClass().getResource("configureserver.png")));
	private JMenuItem menuItemChatSession    = new JMenuItem("Join Chat", new ImageIcon(getClass().getResource("joinchat.png")));
	private JMenuItem menuItemLeaveSession = new JMenuItem("Leave Session", new ImageIcon(getClass().getResource("leavesession.png")));
	private JMenuItem menuItemRemove   = new JMenuItem("Remove File", new ImageIcon(getClass().getResource("removefile.png")));
	private JMenuItem menuItemGrant  = new JMenuItem("View Users", new ImageIcon(getClass().getResource("viewusers.png")));
	private JMenuItem menuItemClose    = new JMenuItem("Shutdown Session", new ImageIcon(getClass().getResource("shutdownsession.png")));
	private JMenuItem menuItemAdd    = new JMenuItem("Add User", new ImageIcon(getClass().getResource("adduser.png")));
	private JMenuItem menuItemHelp = new JMenuItem("Help", new ImageIcon(getClass().getResource("help.png")));
	ClientController controller = new ClientController();
	String newSessionPassword = "";

	JTabbedPane tabs = new JTabbedPane();
	ArrayList<ChatSessionPanel> chatSessions = new ArrayList<ChatSessionPanel>();

	@SuppressWarnings("unchecked")
	public MainFrame() {
		// initialize quick connect
		QCCommandMappings.mapCommands();
		MainFrame.mainFrame = this;

		// initialize server connection and login user on startup
		try{
			connectToServer();
			controller.setIpAddress((String) serverParams.get(0));
			controller.setPort((Integer) serverParams.get(1));
			menuItemLogin.setEnabled(true);
			
			userLogin();						
			params.add(0,this);
			QuickConnect.handleRequest("login", params);
		}catch(Exception e){
			System.out.println("User chose to bypass Server/User Credentials");
		}
		
		
		//Configure Layout
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setSize(MAIN_WIDTH,MAIN_HEIGHT);
		this.setLocationRelativeTo(null);


		//Set Frame Settings
		this.setTitle("Palantir");
		this.setIconImage(new ImageIcon(getClass().getResource("palantir.png")));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Organize Interface
		buildMenu();
		setAll(false);
		this.add(tabs);
		Dimension startupSize = new Dimension(700, 500);
		tabs.setPreferredSize(startupSize);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

		// ***********************************************************************************
		// ********************************** ACTION LISTENERS *******************************
		// ***********************************************************************************

		menuItemNew.addActionListener(  // Creates a new Chat Session
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String name = JOptionPane.showInputDialog("Enter New Chat Session Name");
						if(name != null || !name.equals("")){
							ArrayList params = new ArrayList();
							HashMap map = new HashMap();
							map.put("sessionName", name);
							
							int result = JOptionPane.showConfirmDialog(null, "Would you like to set a password?","Password?", JOptionPane.YES_NO_OPTION);
							if(result == JOptionPane.YES_OPTION){
								getPassword();
								if(newSessionPassword != null || !newSessionPassword.equals("")){
									try {
										System.out.println("found password:" + newSessionPassword);
										map.put("password", BCOHashPassword.SHA1(newSessionPassword));
									} catch (NoSuchAlgorithmException e1) {
										e1.printStackTrace();
									} catch (UnsupportedEncodingException e1) {
										e1.printStackTrace();
									}
								}
							}
							
							map.put("userName", MainFrame.mainFrame.getController().getCurUser().getUsername());
							params.add(map);
							QuickConnect.handleRequest("createSession", params);
						}
					} // End of actionPerformed method.
				}); // End of menuItemNew action listener.

		menuItemLeaveSession.addActionListener(  // Leaves the current Chat Session
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						ArrayList params = new ArrayList();
						ChatSessionPanel panel = (ChatSessionPanel)tabs.getSelectedComponent();
						String sessionId = panel.getSession().getSessionId();
						params.add(tabs);
						params.add(tabs.getSelectedIndex());
						params.add(sessionId);
						QuickConnect.handleRequest("leaveSession", params);
					} 
				}); 
		menuItemSave.addActionListener(  // Saves current chat dialog to text file
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JFileChooser fileChooser = new JFileChooser();
						int retVal = fileChooser.showSaveDialog(mainFrame);

						if(retVal == JFileChooser.APPROVE_OPTION){
							File file = fileChooser.getSelectedFile();
							FileOutputStream fileOutputStream;
							try {
								fileOutputStream = new FileOutputStream(file);
								int i = tabs.getSelectedIndex();
								ChatSessionPanel panel = (ChatSessionPanel) tabs.getComponentAt(i);
								String chatText = panel.aMessengerPanel.receiveArea.getText();
								fileOutputStream.write(chatText.getBytes());
								fileOutputStream.flush();
								fileOutputStream.close();
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} 
						}
					} 
				});

		menuItemExit.addActionListener(  //  Closes the application
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
		menuItemLogin.addActionListener(  // user login
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try{
							userLogin();						
							params.add(0,this);
							QuickConnect.handleRequest("login", params);
						}catch(Exception e1){
							
						}
						
					
					} 
				});
		menuItemServer.addActionListener(  //  Connect to the server
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try{
							connectToServer();
							controller.setIpAddress((String) serverParams.get(0));
							controller.setPort((Integer) serverParams.get(1));
							menuItemLogin.setEnabled(true);
						}catch(Exception e1){
							
						}
						
					}
				});
		menuItemChatSession.addActionListener(  //  Join an existing chat session
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						ArrayList params = new ArrayList();
						QuickConnect.handleRequest("sessionList", params);
					}
				});

		menuItemAdd.addActionListener( new ActionListener(){  //  add user
			@Override
			public void actionPerformed(ActionEvent arg0) {

				addUser();
				String un = (String) addUserParams.get(0);
				String pw = (String) addUserParams.get(1);
				ArrayList al = new ArrayList();
				al.add(MainFrame.mainFrame);
				HashMap map = new HashMap();
				MessageDigest md;
				String hashString = null;
				try {
					md = MessageDigest.getInstance("SHA1");
					md.update(new String(pw).getBytes());
					hashString = convToHex(md.digest());
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				map.put("username", un);
				map.put("password", hashString);
				map.put("role", "USER");
				al.add(map);
				JOptionPane.showMessageDialog(MainFrame.mainFrame, "user added");
				QuickConnect.handleRequest("addUser", al);
			}
		});
		
		menuItemClose.addActionListener(new ActionListener(){  //  close current session
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList params = new ArrayList();
				HashMap mapFacade = new HashMap();
				mapFacade.put("sessionId", ((ChatSessionPanel)MainFrame.mainFrame.getTabs().getSelectedComponent()).getSession().getSessionId());
				params.add(mapFacade); // index 0
				QuickConnect.handleRequest("closeSession", params);
			}
			
		});
		
		menuItemGrant.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList params = new ArrayList();
				HashMap mapFacade = new HashMap();
				mapFacade.put("sessionId", ((ChatSessionPanel)MainFrame.mainFrame.getTabs().getSelectedComponent()).getSession().getSessionId());
				params.add(mapFacade);
				QuickConnect.handleRequest("getUsersList", params);
			}
		});

		
		menuItemHelp.addActionListener(new ActionListener(){  //  Help
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(MainFrame.mainFrame, "It's on the blog", "The Answer",JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("mclaughlinm.jpg")));
			}
			
		});
		
		menuItemRemove.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList params = new ArrayList();
				HashMap mapFacade = new HashMap();
				mapFacade.put("sessionId", ((ChatSessionPanel)MainFrame.mainFrame.getTabs().getSelectedComponent()).getSession().getSessionId());
				int row = ((ChatSessionPanel)MainFrame.mainFrame.getTabs().getSelectedComponent()).getaFilePanel().getTable().getSelectedRow();
				String fileId = (String) ((ChatSessionPanel)MainFrame.mainFrame.getTabs().getSelectedComponent()).getaFilePanel().getTable().getModel().getValueAt(row, 2);
				System.out.println("File: " + fileId);
				mapFacade.put("fileId", fileId);
				params.add(mapFacade);
				QuickConnect.handleRequest("removeFile", params);
			}
			
		});
		
		// ***********************************************************************************
		// *************************** END OF ACTION LISTENERS *******************************
		// ***********************************************************************************
	}

	private void setIconImage(ImageIcon imageIcon) {
		// TODO Auto-generated method stub

	}

	public void newSession(SessionBean newSession) {
		ChatSessionPanel newPanel = new ChatSessionPanel(newSession);
		tabs.addTab(newSession.getSessionName(), newPanel);
		chatSessions.add(newPanel);
		this.repaint();
		this.pack();
	}

	public static void main(String[] args) {
		if ( System.getProperty( "os.name" ).toLowerCase( ).startsWith( "windows" ) )
		{
			try {
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			} catch (Exception e) {
				// If Nimbus is not available, you can set the GUI to another look and feel.
			}
		}
		
		//***Commented out for development phase**//
//        PalantirSplash splash = new PalantirSplash(4000);
//        splash.showSplash();
		
		@SuppressWarnings("unused")
		MainFrame mf = new MainFrame();
	}
	private JMenuBar buildMenu()
	{
		// Add menus to the menu bar.
		menuBar.add(file);
		menuBar.add(chat);
		menuBar.add(moderation);
		menuBar.add(help);

		// Set mnemonics for menu selections.
		file.setMnemonic('F');
		chat.setMnemonic('C');
		moderation.setMnemonic('M');
		help.setMnemonic('H');

		// Menu items for file menu.
		file.add(menuItemNew);
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

		moderation.add(menuItemGrant);
		moderation.addSeparator();
		moderation.add(menuItemRemove);
		//moderation.add(menuItemBan);
		moderation.add(menuItemClose);
		moderation.addSeparator();
		moderation.add(menuItemAdd);
		menuItemAdd.setEnabled(false);

		help.add(menuItemHelp);
		// Set mnemonics for menu item selections for file menu.
		menuItemNew.setMnemonic('N');
		menuItemExit.setMnemonic('X');
		menuItemSave.setMnemonic('S');

		// Set mnemonics for menu item selections for chat menu.
		menuItemLogin.setMnemonic('L');
		menuItemServer.setMnemonic('S');
		menuItemChatSession.setMnemonic('C');
		
		menuItemHelp.setMnemonic('H');
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
		menuItemSave.setEnabled(setter);
		menuItemLogin.setEnabled(setter);
		menuItemChatSession.setEnabled(setter);
		menuItemRemove.setEnabled(setter);
		//menuItemBan.setEnabled(setter);
		menuItemGrant.setEnabled(setter);
		menuItemClose.setEnabled(setter);
		menuItemLeaveSession.setEnabled(setter);
	}

	public ArrayList<ChatSessionPanel> getChatSessions() {
		return chatSessions;
	}


	public void connectToServer() {
		final JDialog dialog = new JDialog(this, "Server Configuration");
		dialog.setLayout(new FlowLayout());
		final JLabel ipLabel = new JLabel("Server IP:");
		final JLabel portLabel = new JLabel("Server Port:");
		final JTextField ipInput = new JTextField(10);
		final JTextField portInput = new JTextField(10);
		JButton ok = new JButton("Ok");

		JPanel panel1 = new JPanel(new GridLayout(3, 2, 10, 10));
		//		JPanel panel2 = new JPanel();
		//		JPanel panel3 = new JPanel();

		panel1.add(ipLabel);
		panel1.add(ipInput);
		panel1.add(portLabel);
		panel1.add(portInput);
		panel1.add(Box.createGlue());
		panel1.add(ok);

		dialog.add(panel1);
		//		dialog.add(panel2);
		//		dialog.add(panel3);
		portInput.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{

				serverParams = new ArrayList();

				String serverIP = ipInput.getText();
				String port = portInput.getText();

				if ((serverIP.equals("")) || (port.equals(""))) {
					JOptionPane.showMessageDialog(dialog, "Please enter valid data");
				}

				else {

					Integer portNumber = Integer.parseInt(port);
					serverParams.add(serverIP);
					serverParams.add(portNumber);
					dialog.setVisible(false);
					dialog.dispose();
				}

			}
		});
		ok.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{

				serverParams = new ArrayList();

				String serverIP = ipInput.getText();
				String port = portInput.getText();

				if ((serverIP.equals("")) || (port.equals(""))) {
					JOptionPane.showMessageDialog(dialog, "Please enter valid data");
				}

				else {

					Integer portNumber = Integer.parseInt(port);
					serverParams.add(serverIP);
					serverParams.add(portNumber);
					dialog.setVisible(false);
					dialog.dispose();
				}

			}
		});
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);
		dialog.pack();
		dialog.setVisible(true);
	}

	
	
	
	
	
	public void userLogin() {

		final JDialog dialog = new JDialog(this, "User Login");
		dialog.setLayout(new FlowLayout());
		final JLabel uNameLabel = new JLabel("Username:");
		final JLabel pWordLabel = new JLabel("Password:");
		final JTextField uNameInput = new JTextField(15);
		final JPasswordField pWordInput = new JPasswordField(15);
		JButton ok = new JButton("Ok");

		JPanel panel1 = new JPanel(new GridLayout(3, 2, 10, 10));
		//		JPanel panel2 = new JPanel();
		//		JPanel panel3 = new JPanel();

		panel1.add(uNameLabel);
		panel1.add(uNameInput);
		panel1.add(pWordLabel);
		panel1.add(pWordInput);
		panel1.add(Box.createGlue());
		panel1.add(ok);

		dialog.add(panel1);
		//		dialog.add(panel2);
		//		dialog.add(panel3);
		pWordInput.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{

				params = new ArrayList();

				String uName = uNameInput.getText();
				char[] pWord = pWordInput.getPassword();

				if ((uName.equals("")) || (pWord.length == 0)) {
					JOptionPane.showMessageDialog(dialog, "Please enter valid credentials");
				}

				else {
					String password = new String(pWord);
					params.add(uName);
					params.add(password);
					dialog.setVisible(false);
					dialog.dispose();
				}

			}
		});
		ok.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{

				params = new ArrayList();

				String uName = uNameInput.getText();
				char[] pWord = pWordInput.getPassword();

				if ((uName.equals("")) || (pWord.length  == 0)) {
					JOptionPane.showMessageDialog(dialog, "Please enter valid credentials");
				}

				else {
					String password = new String(pWord);
					params.add(uName);
					params.add(password);
					dialog.setVisible(false);
					dialog.dispose();
				}

			}
		});
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);
		dialog.pack();
		dialog.setVisible(true);
	}

	public JTabbedPane getTabs() {
		return tabs;
	}

	public void setTabs(JTabbedPane tabs) {
		this.tabs = tabs;
	}

	private void addUser() {
		final JDialog dialog = new JDialog();
		dialog.setLayout(new FlowLayout());
		final JLabel uNameLabel = new JLabel("Username:");
		final JLabel pWordLabel = new JLabel("Password:");
		final JLabel pWordLabel2 = new JLabel("Re-enter Password:");
		final JTextField uNameInput = new JTextField(15);
		final JPasswordField pWordInput = new JPasswordField(15);
		final JPasswordField pWordInput2 = new JPasswordField(15);
		JButton ok = new JButton("Ok");

		JPanel panel1 = new JPanel(new GridLayout(4, 2, 10, 10));

		panel1.add(uNameLabel);
		panel1.add(uNameInput);
		panel1.add(pWordLabel);
		panel1.add(pWordInput);
		panel1.add(pWordLabel2);
		panel1.add(pWordInput2);
		panel1.add(Box.createGlue());
		panel1.add(ok);

		dialog.add(panel1);

		pWordInput2.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{

				addUserParams = new ArrayList();

				String uName = uNameInput.getText();
				char[] pWord = pWordInput.getPassword();
				char[] pWord2 = pWordInput2.getPassword();

				if ((uName.equals("")) || (pWord.length == 0) || (pWord2.length == 0)) {
					JOptionPane.showMessageDialog(dialog, "Please enter valid credentials");
				}

				else {
					String password = new String(pWord);
					String password2 = new String(pWord2);
					if (password.equals(password2)) {
						addUserParams.add(uName);
						addUserParams.add(password);
						dialog.setVisible(false);
						dialog.dispose();
					}
					else {
						JOptionPane.showMessageDialog(dialog, "Passwords must match");
					}
				}

			}
		});

		ok.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{

				addUserParams = new ArrayList();

				String uName = uNameInput.getText();
				char[] pWord = pWordInput.getPassword();
				char[] pWord2 = pWordInput2.getPassword();

				if ((uName.equals("")) || (pWord.length == 0) || (pWord2.length == 0)) {
					JOptionPane.showMessageDialog(dialog, "Please enter valid credentials");
				}

				else {
					String password = new String(pWord);
					String password2 = new String(pWord2);
					if (password.equals(password2)) {
						addUserParams.add(uName);
						addUserParams.add(password);
						dialog.setVisible(false);
						dialog.dispose();
					}
					else {
						JOptionPane.showMessageDialog(dialog, "Passwords must match");
					}
				}

			}
		});
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);
		dialog.pack();
		dialog.setVisible(true);
	}
	
	private void configureServerLoginUser() {
		final JDialog dialog = new JDialog();
		dialog.setLayout(new FlowLayout());
		final JLabel ipLabel = new JLabel("Server IP:");
		final JLabel portLabel = new JLabel("Server Port:");
		final JLabel uNameLabel = new JLabel("Username:");
		final JLabel pWordLabel = new JLabel("Password:");
		final JTextField ipInput = new JTextField(10);
		final JTextField portInput = new JTextField(10);
		final JTextField uNameInput = new JTextField(10);
		final JPasswordField pWordInput = new JPasswordField(10);
		
		JButton cancel = new JButton("Cancel");
		JButton login = new JButton("Login");
		
		JPanel panel1 = new JPanel(new GridLayout(5, 2, 10, 10));
		
		panel1.add(ipLabel);
		panel1.add(ipInput);
		panel1.add(portLabel);
		panel1.add(portInput);
		panel1.add(uNameLabel);
		panel1.add(uNameInput);
		panel1.add(pWordLabel);
		panel1.add(pWordInput);
		panel1.add(cancel);
		panel1.add(login);
		
		dialog.add(panel1);
		
		cancel.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.setVisible(false);
						dialog.dispose();
					}
				});
		
		login.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String serverIP = ipInput.getText();
						String serverPort = portInput.getText();
						String uName = uNameInput.getText();
						char[] pWord = pWordInput.getPassword();
						if ((serverIP.equals("")) || (serverPort.equals("")) || (uName.equals("")) || (pWord.length == 0)) {
							JOptionPane.showMessageDialog(dialog, "Please enter valid data and credentials");
						}
						
						else {
							ClientController cont = MainFrame.mainFrame.getController();
							int portNumber = Integer.parseInt(serverPort);
							cont.setIpAddress(serverIP);
							cont.setPort(portNumber);
							
							params = new ArrayList();
						
							params.add(this);
							params.add(uName);
							params.add(pWord);
							QuickConnect.handleRequest("login", params);
						}
						
					}
				});
		
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);
		dialog.pack();
		dialog.setVisible(true);
	}

	private static String convToHex(byte[] data) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while(two_halfs++ < 1);
		}
		return buf.toString();
	}
	
	public void getPassword(){

		final JDialog dialog = new JDialog(MainFrame.mainFrame, "User Login");
		dialog.setLayout(new FlowLayout());
		final JLabel pWordLabel = new JLabel("Password:");
		final JPasswordField pWordInput = new JPasswordField(15);
		JButton ok = new JButton("Ok");

		JPanel panel1 = new JPanel(new GridLayout(2, 2, 10, 10));
		//		JPanel panel2 = new JPanel();
		//		JPanel panel3 = new JPanel();

		panel1.add(pWordLabel);
		panel1.add(pWordInput);
		panel1.add(Box.createGlue());
		panel1.add(ok);

		dialog.add(panel1);
		
		ActionListener go = new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{


				char[] pWord = pWordInput.getPassword();


				newSessionPassword = new String(pWord);
				dialog.setVisible(false);
				dialog.dispose();
			}
		};

		pWordInput.addActionListener(go);
		ok.addActionListener(go);
		dialog.setModal(true);
		dialog.setLocationRelativeTo(null);
		dialog.pack();
		dialog.setVisible(true);
}

}







