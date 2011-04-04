package qc;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import beans.CommunicationBean;

public class BroadcastResponseBCO implements ControlObject{
	
	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		CommunicationBean commBean = (CommunicationBean) arg0.get(arg0.size()-1);
		HashMap cliParams = (HashMap) arg0.get(1);
		
		String sessionNumber = (String) cliParams.get("sessionId");
		
		HashMap<String, HashMap<String, ObjectOutputStream>> sessions = MainFrame.mainFrame.getController().getConnectionMap();
		
		HashMap<String, ObjectOutputStream> session = sessions.get(sessionNumber);
		if(session != null){
			ArrayList streams = new ArrayList(session.values());
		
		
			for(int i = 0; i < streams.size(); i++){
				ObjectOutputStream curStream = (ObjectOutputStream) streams.get(i);
				try {
					curStream.writeObject(commBean);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return commBean;
			
	}}
