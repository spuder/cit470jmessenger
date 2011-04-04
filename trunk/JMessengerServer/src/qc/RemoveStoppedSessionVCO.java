package qc;

import java.util.ArrayList;

import org.quickconnect.ControlObject;

import ui.MainFrame;

public class RemoveStoppedSessionVCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		MainFrame.mainFrame.getChatPanel().getTabbedPane().remove((Integer)arg0.get(1));
		
		return null;
	}

}
