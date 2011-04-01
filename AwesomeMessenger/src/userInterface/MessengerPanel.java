package userInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class MessengerPanel extends JPanel {
	
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
		receiveScroll.setPreferredSize(new Dimension(400, 200));
		sendScroll.add(sendArea);
		sendScroll.setViewportView(sendArea);
		sendScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sendScroll.setPreferredSize(new Dimension(400, 200));
		
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
		buttonPanel.setMaximumSize(new Dimension(2000, 50));
		
		
		// Add the Text Areas and Button to the Panel
		this.add(receiveScroll);
		this.add(buttonPanel);
		this.add(sendScroll);
		
	}
	public void sendMessage() {
		String msgToSend = sendArea.getText();
		sendArea.setText("");
		
		receiveArea.append("> " + msgToSend + "\n");
	}
	

}
