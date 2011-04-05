package qc;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JTabbedPane;

import org.quickconnect.ControlObject;

import ui.ChatSessionPanel;
import ui.MainFrame;

public class RemoveStoppedSessionVCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		
		HashMap params = (HashMap) arg0.get(1);
		String sessionId = (String) params.get("sessionId");
		
		JTabbedPane pane = MainFrame.mainFrame.getChatPanel().getTabbedPane();

		
		for(int i = 0 ; i < pane.getComponentCount(); i++){
			ChatSessionPanel session = (ChatSessionPanel) MainFrame.mainFrame.getChatPanel().getTabbedPane().getComponentAt(i);
			if (session.getSessionObject().getSessionId().equals(sessionId)){
				MainFrame.mainFrame.getChatPanel().getTabbedPane().remove(i);
				break;
			}
		}
		
		return null;
	}

}
