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

public class DisplaySessionUsersListVCO implements ControlObject {

		JDialog dialog;
		JTable table;
		Vector list;
		
		@Override
		public Object handleIt(ArrayList<Object> arg0) {

			dialog = new JDialog();
			HashMap params = (HashMap) arg0.get(0);
			list = (Vector) params.get("list");
			Vector columns = new Vector();
			columns.add("User");
			columns.add("Role");
			
			
			StandardTableModel tableModel = new StandardTableModel(list, columns);
			table = new JTable(tableModel);
			JScrollPane scroll = new JScrollPane(table);
			scroll.setPreferredSize(new Dimension(300,200));
			
			JButton banButton = new JButton("Ban");
			JButton modButton = new JButton("Make Moderator");
			JButton cancel = new JButton("Done");
			
			banButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					ArrayList params = new ArrayList();
					String[] row = (String[]) list.get(table.getSelectedRow());
					HashMap paramMap = new HashMap();
					paramMap.put("userToBan",(String)row[0]);
					paramMap.put("sessionId", ((ChatSessionPanel)MainFrame.mainFrame.getTabs().getSelectedComponent()).getSession().getSessionId());
					params.add(paramMap);
					QuickConnect.handleRequest("banUser", params);
					dialog.dispose();
				}
			});
			
			modButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					String[] row = (String[]) list.get(table.getSelectedRow());
					if(!(row[1].equals("ADMIN") || row[1].equals("MODERATOR"))){
						ArrayList params = new ArrayList();
						HashMap paramMap = new HashMap();
						paramMap.put("userName",(String)row[0]);
						paramMap.put("sessionId", ((ChatSessionPanel)MainFrame.mainFrame.getTabs().getSelectedComponent()).getSession().getSessionId());
						params.add(paramMap);
						QuickConnect.handleRequest("makeModerator", params);
						dialog.dispose();
					}
				}
				
			});
			
			cancel.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dialog.dispose();
				}	
			});
			
			JPanel panel = new JPanel();
			JPanel buttonPanel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			
			buttonPanel.add(banButton);
			buttonPanel.add(modButton);
			panel.add(scroll);
			panel.add(buttonPanel);
			panel.add(cancel);
			
			dialog.setTitle("Select User To Moderate:");
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setLocationRelativeTo(MainFrame.mainFrame);
			
			dialog.getContentPane().add(panel);
			
			dialog.pack();
			dialog.setVisible(true);
			
			return null;
		}

}
