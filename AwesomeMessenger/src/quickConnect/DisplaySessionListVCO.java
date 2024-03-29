package quickConnect;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
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

public class DisplaySessionListVCO implements ControlObject {

	JDialog dialog;
	JTable table;
	Vector list;

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		dialog = new JDialog();
		HashMap params = (HashMap) arg0.get(0);
		list = (Vector) params.get("list");
		Vector columns = new Vector();
		columns.add("Session");
		columns.add("Active Participants");
		columns.add("ID");


		StandardTableModel tableModel = new StandardTableModel(list, columns);
		table = new JTable(tableModel);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(300,200));

		JButton button = new JButton("Join");
		JButton cancel = new JButton("Cancel");

		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {

				ArrayList params = new ArrayList();
				String[] row = (String[]) list.get(table.getSelectedRow());
				int count = MainFrame.mainFrame.getTabs().getComponentCount();
				int found = -1;
				int i = 0;
				for (i = 0; i < count; i++) {
					ChatSessionPanel panel = (ChatSessionPanel) MainFrame.mainFrame.getTabs().getComponentAt(i);

					if (panel.getSession().getSessionId().equals(row[2])) {
						found = i;
						break;
					}
				}

				if(found<0){
					params.add((String)row[2]);
					QuickConnect.handleRequest("joinSession", params);
				} else {
					MainFrame.mainFrame.getTabs().setSelectedIndex(found);
				}
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

		dialog.setTitle("Select Chat To Join:");
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.setLocationRelativeTo(MainFrame.mainFrame);

		dialog.getContentPane().add(panel);

		dialog.pack();
		dialog.setVisible(true);

		return null;
	}

}
