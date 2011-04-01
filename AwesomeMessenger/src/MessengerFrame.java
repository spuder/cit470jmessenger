import java.awt.*;
import java.awt.event.*;
import java.io.*;            
import java.lang.*;           
import javax.swing.*;         
import javax.swing.text.*;
public class MessengerFrame extends JFrame {
	
	
	protected MessengerFrame() {
		MessengerPanel messPanel = new MessengerPanel(500, 500);
		this.add(messPanel);
		
		this.setSize(500, 500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		MessengerFrame messFrame = new MessengerFrame();
	}

}
