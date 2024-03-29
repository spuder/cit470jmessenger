package quickConnect;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import org.quickconnect.ControlObject;
import org.quickconnect.QuickConnect;

import userInterface.ChatSessionPanel;
import userInterface.MainFrame;
import userInterface.StandardTableModel;

public class DisplayFileVersionsListVCO implements ControlObject {

	JDialog dialog;
	JTable table;
	Vector list;
	//String sessionId;
	String fileId;
	
	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		dialog = new JDialog();
		HashMap params = (HashMap) arg0.get(0);
		list = (Vector) params.get("list");
		fileId = (String) params.get("fileId");
		//sessionId = (String) params.get("sesionId");
		Vector columns = new Vector();
		columns.add("File");
		columns.add("Uploader");
		columns.add("Description");
		columns.add("Timestamp");


		StandardTableModel tableModel = new StandardTableModel(list, columns);
		table = new JTable(tableModel);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(300,200));

		JButton button = new JButton("Download Version");
		JButton cancel = new JButton("Cancel");

		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {

				ArrayList params = new ArrayList();
				String[] row = (String[]) list.get(table.getSelectedRow());
				HashMap paramsMap = new HashMap();
				paramsMap.put("timestamp", (String)row[3]);
				paramsMap.put("fileId", fileId);
				//paramsMap.put("sessionId", sessionId);
				params.add(paramsMap);
				QuickConnect.handleRequest("downloadVersion", params);
				dialog.dispose();

			}
		});

		cancel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}	
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(scroll);
		panel.add(button);
		panel.add(cancel);

		dialog.setTitle("Select Version of " + fileId + ":");
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(MainFrame.mainFrame);

		dialog.getContentPane().add(panel);

		dialog.pack();
		dialog.setVisible(true);

		return null;
		
	}

}
