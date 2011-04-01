package userInterface;

import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements KeyListener {
	
	
	MessengerPanel aMessengerPanel;
	
	
	@SuppressWarnings("unchecked")
	public MainFrame() {
		Vector vector = new Vector();

		String[] set1 = {"main.java","tyler","10:22:33"};
		String[] set2 = {"messagePanel.java", "sam", "11:22:31"};
		String[] set3 = {"filePanel.java", "skyler", "12:22:22"};
		vector.add(set1);
		vector.add(set2);
		vector.add(set3);

		aMessengerPanel = new MessengerPanel(300, 400);
		FilePanel aFilePanel = new FilePanel(250, 400, vector);
		aFilePanel.setPreferredSize(new Dimension(260,400));
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		this.setTitle("Palantir - ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		 
		this.add(aMessengerPanel);
		this.add(aFilePanel);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
		aMessengerPanel.sendMessageButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//aMessengerPanel.send();
			}
		});
		
	}
	public static void main(String[] args) {
		MainFrame bob = new MainFrame();
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			aMessengerPanel.sendMessage();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
