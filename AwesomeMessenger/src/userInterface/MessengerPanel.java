package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class MessengerPanel extends JPanel{
	
	public static final int RECEIVE_SCROLL_WIDTH = 400;
	public static final int RECEIVE_SCROLL_HEIGHT = 300;
	public static final int SEND_SCROLL_WIDTH = 400;
	public static final int SEND_SCROLL_HEIGHT = 100;
	public static final int BUTTON_PANEL_MAX_H = 50;
	public static final int BUTTON_PANEL_MAX_W = 20000;
	
	// 2 Text Areas, a button and a button panel
	protected JScrollPane sendScroll = new JScrollPane();
	protected JScrollPane receiveScroll = new JScrollPane();
	protected JTextArea receiveArea = new JTextArea();
	protected JTextArea sendArea = new JTextArea();
	protected JButton sendMessageButton = new JButton();
	protected JPanel buttonPanel = new JPanel();
	
	public MessengerPanel(int x, int y) {
		
		// Set the size for this instance of the MessengerPanel
		this.setSize(x, y);
		
		// Set the Layout for the Messenger Panel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Set the values for the ScrollPanes
		receiveScroll.add(receiveArea);
		receiveScroll.setViewportView(receiveArea);
		receiveScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		receiveScroll.setPreferredSize(new Dimension(RECEIVE_SCROLL_WIDTH, RECEIVE_SCROLL_HEIGHT));
		sendScroll.add(sendArea);
		sendScroll.setViewportView(sendArea);
		sendScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sendScroll.setPreferredSize(new Dimension(SEND_SCROLL_WIDTH, SEND_SCROLL_HEIGHT));
		
		// Set the values for both Text Areas.
		receiveArea.setEditable(false);
		sendArea.setEditable(true);
		receiveArea.setBackground(Color.WHITE);
		sendArea.setBackground(Color.WHITE);
		receiveArea.setBorder(BorderFactory.createLineBorder(Color.black));
		sendArea.setBorder(BorderFactory.createLineBorder(Color.black));
		receiveArea.setLineWrap(true);
		sendArea.setLineWrap(true);
		receiveArea.setWrapStyleWord(true);
		sendArea.setWrapStyleWord(true);
		
		//have it press enter to send message
		sendArea.getInputMap().put(KeyStroke.getKeyStroke("ENTER"),"send");
		sendArea.getActionMap().put("send", new SendAction());
		
		// Set the values for the button
		sendMessageButton.setText("Send Message");
		sendMessageButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendMessage();
			}
		});
		
		// Add the Button to the buttonPanel
		buttonPanel.add(sendMessageButton);
		buttonPanel.setMaximumSize(new Dimension(BUTTON_PANEL_MAX_W, BUTTON_PANEL_MAX_H));
		
		
		// Add the Text Areas and Button to the Panel
		this.add(receiveScroll);
		this.add(buttonPanel);
		this.add(sendScroll);
		
	}
	
	// Method and inner class to send messages and do it on enter keypress
	
	public void sendMessage() {
		if(!sendArea.getText().trim().equals("")){
			String msgToSend = sendArea.getText();
			sendArea.setText("");
		
			receiveArea.append("> " + msgToSend + "\n");
		}
	}

	class SendAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			sendMessage();
		}
	}
	
}