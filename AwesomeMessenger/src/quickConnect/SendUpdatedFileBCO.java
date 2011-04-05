package quickConnect;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;
import beans.CommunicationBean;
import beans.FileBean;

public class SendUpdatedFileBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		File uploadFile = (File)arg0.get(0);
		String sessionId = (String)arg0.get(1);
		String fileNumber = (String)arg0.get(2);
		String description = (String)arg0.get(3);
		
		CommunicationBean commBean = new CommunicationBean();
		
		FileBean fileBean = new FileBean(uploadFile, uploadFile.getName());
		fileBean.setDesc(description);
		fileBean.setFileId(fileNumber);
		fileBean.setSize(uploadFile.length());
		
		HashMap params = new HashMap();
		
		params.put("file", fileBean);
		params.put("sessionId", sessionId);
		
		commBean.setCommand("updateFile");
		commBean.setParameters(params);
		
		try {
			MainFrame.mainFrame.getController().getoStream().writeObject(commBean);
		} catch (IOException e) { e.printStackTrace(); }
				
		return null;
	}

}
