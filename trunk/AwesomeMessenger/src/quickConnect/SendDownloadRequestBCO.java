package quickConnect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;

import beans.CommunicationBean;

public class SendDownloadRequestBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		CommunicationBean commBean = new CommunicationBean();
		HashMap params = new HashMap();
		
		params.put("fileId", arg0.get(0));
		
		commBean.setCommand("requestDownload");
		commBean.setParameters(params);
		
		try {
			MainFrame.mainFrame.getController().getoStream().writeObject(commBean);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to contact server");
			e.printStackTrace();
		}
		
		return null;
	}

}
