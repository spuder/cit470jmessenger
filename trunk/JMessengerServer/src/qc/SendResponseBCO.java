package qc;

import java.io.IOException;
import java.util.ArrayList;

import org.quickconnect.ControlObject;

import controller.ServerConnectionHandler;

import ui.MainFrame;

import beans.CommunicationBean;

public class SendResponseBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		CommunicationBean response = (CommunicationBean)arg0.get(arg0.size()-1);
		ServerConnectionHandler handler = (ServerConnectionHandler)arg0.get(0);
		
		try {
			handler.getOutputStream().writeObject(response);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return true;
	}

}
