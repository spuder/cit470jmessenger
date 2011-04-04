package quickConnect;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import org.quickconnect.ControlObject;

public class DeleteSessionTabVCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		if((Boolean) arg0.get(arg0.size()-1)){
			JTabbedPane tabs = (JTabbedPane) arg0.get(0);
			int indexToRemove = (Integer) arg0.get(1);
			tabs.remove(indexToRemove);
		}
		
		return null;
	}

}
