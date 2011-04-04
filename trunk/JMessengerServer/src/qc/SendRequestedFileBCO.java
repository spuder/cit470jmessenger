package qc;

import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import controller.ServerConnectionHandler;

public class SendRequestedFileBCO implements ControlObject{
	
	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		ServerConnectionHandler handler = (ServerConnectionHandler) arg0.get(0);
		HashMap params = (HashMap) arg0.get(1);
		
		String fileId = (String) params.get("fileId");
		
		
		
		return null;
	}
	
}
