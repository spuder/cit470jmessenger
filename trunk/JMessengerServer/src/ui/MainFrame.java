package ui;

import ui.PalantirSplash;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
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
import controller.ServerController;
import qc.QCCommandMappings;

public class MainFrame extends JFrame{

	private static final int MAIN_FRAME_WIDTH = 800;
	private static final int MAIN_FRAME_HEIGHT = 500;
	public static MainFrame mainFrame;
	public ArrayList params = null;
	JTabbedPane tabPane;
	ChatPanel chatPanel;
	UserAdminPanel adminPanel;

	//Menu
	JMenuBar menuBar;
	JMenu fileMenu, sessionsMenu;
	JMenuItem exitItem, newSessionItem, closeSessionItem;
	String newSessionPassword = "";

	ServerController controller;

	public MainFrame(){

		mainFrame = this;

		//Setup QC
		QCCommandMappings.mapCommands();


		try{
			//ServerLoginPane login = new ServerLoginPane();
			serverLogin();
			//ArrayList credentials = login.getParams();
			Integer portListener = (Integer) params.get(1);
			Integer port = (Integer) params.get(0);
			String un = (String) params.get(2);
			String pw = (String) params.get(3);

			controller = new ServerController(port,un,pw, portListener);
		}catch(Exception e){
			System.exit(NORMAL);
		}
		
		ArrayList cleanup = new ArrayList();
		QuickConnect.handleRequest("cleanUp", cleanup);
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ArrayList params = new ArrayList();
		QuickConnect.handleRequest("getActiveSessions", params);

		this.pack();
		
		this.setVisible(true);
		QuickConnect.handleRequest("refreshList", cleanup);

		controller.startServer();
		this.setLocationRelativeTo(null);
	}

	private void buildMenu() {
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		sessionsMenu = new JMenu("Sessions");
		exitItem = new JMenuItem("Exit");
		newSessionItem = new JMenuItem("New Session");
		closeSessionItem = new JMenuItem("Stop Session");

		menuBar.add(fileMenu);
		menuBar.add(sessionsMenu);

		fileMenu.add(exitItem);
		sessionsMenu.add(newSessionItem);
		sessionsMenu.add(closeSessionItem);

		newSessionItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("New Session Name:");
				if(name != null || !name.equals("")){
					
					HashMap facadeMap = new HashMap();
					facadeMap.put("sessionName", name);
					
					int result = JOptionPane.showConfirmDialog(null, "Would you like to set a password?","Password?", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION){
						getPassword();
						if(newSessionPassword != null || !newSessionPassword.equals("")){
							try {
								facadeMap.put("password", SHA1(newSessionPassword));
							} catch (NoSuchAlgorithmException e1) {
								e1.printStackTrace();
							} catch (UnsupportedEncodingException e1) {
								e1.printStackTrace();
							}
						}
					}
					
					ArrayList params = new ArrayList();
					params.add(this);
					params.add(facadeMap);
					QuickConnect.handleRequest("localCreateSession", params);
				}
			}
		});

		closeSessionItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList params = new ArrayList();
				HashMap mapFacade = new HashMap();
				mapFacade.put("sessionId",((ChatSessionPanel)chatPanel.getTabbedPane().getSelectedComponent())
						.getSessionObject().getSessionId());
				params.add(chatPanel.getTabbedPane().getSelectedIndex()); // index 1
				params.add(mapFacade); // index 0
				QuickConnect.handleRequest("stopSession", params);
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
		
		PalantirSplash splash = new PalantirSplash(4000);
        splash.showSplash();
        
		MainFrame mf = new MainFrame();
	}
	public void serverLogin() {
		final JLabel uNameLabel;
		final JLabel pWordLabel;
		final JLabel portLabel;
		final JLabel portListenerLabel;
		final JDialog dialog = new JDialog();
		final JTextField portListenerInput = new JTextField(4);
		final JTextField portInput = new JTextField(4);
		final JTextField uNameInput = new JTextField(15);
		final JPasswordField pWordInput = new JPasswordField(15);
		dialog.setLayout(new FlowLayout());
		portListenerLabel = new JLabel("Enter Listener port:");
		portLabel = new JLabel("Enter DB port:");
		uNameLabel = new JLabel("DB UserName:");
		pWordLabel = new JLabel("DB Password:");
		JButton ok = new JButton("Ok");
		JPanel panel1 = new JPanel(new GridLayout(5, 2, 10, 10));
		panel1.add(portListenerLabel);
		panel1.add(portListenerInput);
		panel1.add(portLabel);
		panel1.add(portInput);
		panel1.add(uNameLabel);
		panel1.add(uNameInput);
		panel1.add(pWordLabel);
		panel1.add(pWordInput);
		panel1.add(Box.createGlue());
		panel1.add(ok);
		dialog.add(panel1);
		
		pWordInput.addActionListener(new ActionListener()
		{
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e)
			{

				params = new ArrayList();

				String port = portInput.getText();
				String uName = uNameInput.getText();
				char[] pWord = pWordInput.getPassword();
				String portListener = portListenerInput.getText();
				if ((port.equals("")) || (uName.equals("")) || (pWord.length == 0) || (portListener.equals(""))) {
					JOptionPane.showMessageDialog(dialog, "Please enter valid credentials");
				}

				else {
					String password = new String(pWord);
					Integer portNumber = Integer.parseInt(portListenerInput.getText());
					Integer portInt = Integer.parseInt(portInput.getText());
					params.add(portNumber);
					params.add(portInt);
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

				String port = portInput.getText();
				String uName = uNameInput.getText();
				char[] pWord = pWordInput.getPassword();
				String portListener = portListenerInput.getText();
				if ((port.equals("")) || (uName.equals("")) || (pWord.length == 0) || (portListener.equals(""))) {
					JOptionPane.showMessageDialog(dialog, "Please enter valid credentials");
				}

				else {
					String password = new String(pWord);
					Integer portNumber = Integer.parseInt(portListenerInput.getText());
					Integer portInt = Integer.parseInt(portInput.getText());
					params.add(portNumber);
					params.add(portInt);
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
	
	private static String convToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
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
	
	public static String SHA1(String text) throws NoSuchAlgorithmException,UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();
		return convToHex(sha1hash);
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