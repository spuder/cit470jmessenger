package userInterface;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


@SuppressWarnings("serial")
public class FilePanel extends JPanel {

	private static final int BUTTON_PANEL_HEIGHT = 20;
	private static Vector<String> columnNames;

	// UI Components
	private JTable table = null;
	private JScrollPane scrollTable = null;
	private JButton sendFileButton;
	private JButton resendFileButton;
	private JPanel buttonPanel;

	// Other Components
	private JFileChooser chooser;
	private File currentFile;

	//Static block to setup colum names
	static {
		columnNames = new Vector<String>();
		columnNames.add("File");
		columnNames.add("Sender");
		columnNames.add("Timestamp");
	}

	public FilePanel(){

	}

	//Sets up table with a set of vector files
	public FilePanel(int h, int w, Vector<String[]> files){

		// set panel size and width
		Dimension dimension = new Dimension(h,w);

		//setup table
		table = new JTable(new FileTableModel(files,columnNames));
		scrollTable = new JScrollPane(table);
		scrollTable.setMaximumSize(dimension);

		//setup buttons
		buttonPanel = new JPanel();
		buttonPanel.setMaximumSize(new Dimension(w,BUTTON_PANEL_HEIGHT));
		
		sendFileButton = new JButton();
		resendFileButton = new JButton();
		
		sendFileButton.setText("Send File");
		resendFileButton.setText("Resend File");

		//set button action listeners
		chooser = new JFileChooser();
		sendFileButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = chooser.showOpenDialog(sendFileButton);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					currentFile = chooser.getSelectedFile();
				}
			}
		});

		this.organizeComponents();
	}


	private void organizeComponents() {

		//add buttons to panel
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(sendFileButton);
		buttonPanel.add(resendFileButton);

		//set layout to be vertically aligned

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(buttonPanel);
		this.add(scrollTable);

	}

	public File getCurrentSendFile(){
		return currentFile;
	}
	
	public void setCurrentSendFile(File file){
		this.currentFile = file;
	}

	public JTable getTable() {
		return table;
	}

	public void setFileList(Vector list) {
		StandardTableModel newModel = new StandardTableModel(list, columnNames);
		this.table.setModel(newModel);
		this.table.repaint();
	}
	
}