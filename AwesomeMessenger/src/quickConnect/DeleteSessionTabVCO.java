package quickConnect;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import org.quickconnect.ControlObject;

import userInterface.ChatSessionPanel;
import userInterface.MainFrame;

public class DeleteSessionTabVCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		if((Boolean) arg0.get(arg0.size()-1)){
			JTabbedPane tabs = (JTabbedPane) arg0.get(0);
			int indexToRemove = (Integer) arg0.get(1);
			String id =(String)arg0.get(2);
			tabs.remove(indexToRemove);
			ArrayList<ChatSessionPanel> panels = MainFrame.mainFrame.getChatSessions();
			for(int i =0 ; i< panels.size(); i++){
				if(panels.get(i).getSession().getSessionId().equals(id)){
					MainFrame.mainFrame.getChatSessions().remove(i);
				}
			}
		}
		
		return null;
	}

}
