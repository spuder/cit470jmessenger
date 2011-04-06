package userInterface;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;




public class PalantirSplash extends JWindow {

	private int duration;
	static JProgressBar progressBar;

	public PalantirSplash(int d) {
		duration = d;
	}

	// A simple little method to show a title screen in the center
	// of the screen for the amount of time given in the constructor
	public void showSplash() {

		JPanel content = (JPanel)getContentPane();
		content.setBackground(Color.white);

		// Set the window's bounds, centering the window
		int width = 350;
		int height =200;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);

		// Build the splash screen
		JLabel label = new JLabel(new ImageIcon("Images/palantirBG.png"));
		JLabel copyrt = new JLabel
		("Palantir v1.2 RC", JLabel.CENTER);
		JButton hidden = new JButton();

		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setIndeterminate(false);
		progressBar.setVisible(true);

		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
		content.add(progressBar, BorderLayout.SOUTH);
		content.add(label, BorderLayout.CENTER);
		content.add(copyrt, BorderLayout.NORTH);
		Color oraRed = new Color(156, 20, 20,  255);
		content.setBorder(BorderFactory.createLineBorder(oraRed, 1));

		hidden.addActionListener(new progressBar());

		// Display it
		setVisible(true);
		hidden.doClick();

		// Wait a little while, maybe while loading resources
		try { Thread.sleep(duration); } catch (Exception e) {}

		setVisible(false);

	}

	public static class progressBar implements ActionListener{
		public void actionPerformed (ActionEvent e){
			new Thread(new thread1()).start(); //Start the thread
		}
	}

	public static class thread1 implements Runnable{
		public void run(){
			for (int i=0; i <= 100; i++){ //Progressively increment variable i
				progressBar.setValue(i); //Set value
				progressBar.repaint(); //Refresh graphics
				try{Thread.sleep(25);} //Sleep 50 milliseconds
				catch (InterruptedException err){}				
			}			
		}
	}

	public void showSplashAndExit() {

		showSplash();
		
	}
}