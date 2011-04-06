package userInterface;
import java.awt.BorderLayout;
import java.awt.Color;
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

	public void showSplash() {

		JPanel content = (JPanel)getContentPane();
		content.setBackground(Color.white);

		int width = 600;
		int height =450;
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screen.width-width)/2;
		int y = (screen.height-height)/2;
		setBounds(x,y,width,height);

		// Build the splash screen
		JLabel label = new JLabel(new ImageIcon("Images/eyeconnected.jpg"));
//		JLabel copyrt = new JLabel("Palantir v1.2 RC", JLabel.CENTER);
		JButton hidden = new JButton();
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setIndeterminate(false);
		progressBar.setVisible(true);

//		copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 14));
		content.add(progressBar, BorderLayout.SOUTH);
		content.add(label, BorderLayout.CENTER);
//		content.add(copyrt, BorderLayout.NORTH);
		Color oraRed = new Color(0, 0, 0,  255);
		content.setBorder(BorderFactory.createLineBorder(oraRed, 1));

		hidden.addActionListener(new progressBar());

		setVisible(true);
		hidden.doClick();

		try { Thread.sleep(duration); } catch (Exception e) {}

		setVisible(false);

	}

	public static class progressBar implements ActionListener{
		public void actionPerformed (ActionEvent e){
			new Thread(new thread1()).start(); 
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