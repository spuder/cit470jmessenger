package quickConnect;

import java.io.IOException;
import java.util.ArrayList;
import org.quickconnect.ControlObject;

import beans.CommunicationBean;
import userInterface.MainFrame;

public class BCOsendMessage implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		MainFrame mainFrame = (MainFrame) arg0.get(0);
		try {
			mainFrame.getController().getoStream().writeObject((CommunicationBean)arg0.get(1));
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}
}