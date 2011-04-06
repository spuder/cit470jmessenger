package quickConnect;

import java.io.IOException;
import java.util.ArrayList;
import org.quickconnect.ControlObject;

import beans.CommunicationBean;
import beans.MessageBean;
import userInterface.MainFrame;

public class BCOsendMessage implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		MainFrame mainFrame = (MainFrame) arg0.get(0);
		CommunicationBean commBean = (CommunicationBean) arg0.get(1);
		commBean.getParameters().put("sessionId", ((MessageBean)commBean.getParameters().get("message")).getSessionid());
		try {
			mainFrame.getController().getoStream().writeObject(commBean);
		} catch (IOException e) { e.printStackTrace(); }
		return null;
	}
}