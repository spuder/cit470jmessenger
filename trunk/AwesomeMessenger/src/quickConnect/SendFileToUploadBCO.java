package quickConnect;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;

import beans.CommunicationBean;
import beans.FileBean;

public class SendFileToUploadBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		File uploadFile = (File)arg0.get(0);
		String sessionId = (String)arg0.get(1);
		
		String description = (String)arg0.get(2);
		
		CommunicationBean commBean = new CommunicationBean();
		
		FileBean fileBean = new FileBean(uploadFile, uploadFile.getName());
		fileBean.setDesc(description);
		
		HashMap params = new HashMap();
		
		params.put("file", fileBean);
		params.put("sessionId", sessionId);
		
		commBean.setCommand("uploadFile");
		commBean.setParameters(params);
		
		try {
			MainFrame.mainFrame.getController().getoStream().writeObject(commBean);
		} catch (IOException e) { e.printStackTrace(); }
				
		return null;
	}

}
