package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
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

	ServerController controller;

	public MainFrame(){

		mainFrame = this;

		//Setup QC
		QCCommandMappings.mapCommands();


		//ServerLoginPane login = new ServerLoginPane();
		serverLogin();
		//ArrayList credentials = login.getParams();
		Integer portListener = (Integer) params.get(1);
		Integer port = (Integer) params.get(0);
		String un = (String) params.get(2);
		String pw = (String) params.get(3);

		controller = new ServerController(port,un,pw, portListener);
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

		ArrayList params = new ArrayList();
		QuickConnect.handleRequest("getActiveSessions", params);

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
				params.add(mapFacade); // index 0
				params.add(chatPanel.getTabbedPane().getSelectedIndex()); // index 1
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
		MainFrame mf = new MainFrame();
	}
	public void serverLogin() {
		final JLabel uNameLabel;
		final JLabel pWordLabel;
		final JLabel portLabel;
		final JLabel portListenerLabel;
		final JDialog dialog = new JDialog();
		dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
		portListenerLabel = new JLabel("Enter Listener port:");
		portLabel = new JLabel("Enter DB port:");
		uNameLabel = new JLabel("DB UserName:");
		pWordLabel = new JLabel("DB Password:");
		final JTextField portListenerInput = new JTextField(4);
		final JTextField portInput = new JTextField(4);
		final JTextField uNameInput = new JTextField(15);
		final JPasswordField pWordInput = new JPasswordField(15);
		JButton ok = new JButton("Ok");
		JPanel panel5 = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel panel4 = new JPanel();
		panel5.add(portListenerLabel);
		panel5.add(portListenerInput);
		panel4.add(portLabel);
		panel4.add(portInput);
		panel1.add(uNameLabel);
		panel1.add(uNameInput);
		panel2.add(pWordLabel);
		panel2.add(pWordInput);
		panel3.add(ok);
		dialog.add(panel5);
		dialog.add(panel4);
		dialog.add(panel1);
		dialog.add(panel2);
		dialog.add(panel3);
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
				if ((port.equals("")) || (uName.equals("")) || (pWord.equals("")) || (portListener.equals(""))) {
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
				if ((port.equals("")) || (uName.equals("")) || (pWord.equals("")) || (portListener.equals(""))) {
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

}