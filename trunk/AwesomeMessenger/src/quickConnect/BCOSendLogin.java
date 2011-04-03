package quickConnect;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import Client.ClientController;
import beans.CommunicationBean;
import beans.UserBean;

public class BCOSendLogin implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		ClientController controller = (ClientController)arg0.get(arg0.size()-1);
		if(controller.getSocket().isConnected()){
			
			CommunicationBean commBean = new CommunicationBean();
			HashMap params = new HashMap();
			commBean.setCommand("login");
			params.put("username", arg0.get(1));
			params.put("password", arg0.get(3));
			commBean.setParameters(params);
			try {
				controller.getoStream().writeObject(commBean);
				commBean = (CommunicationBean) controller.getiStream().readObject();
				if(commBean.getParameters().get("user") != null){
					System.out.println("Client: Successful Login");
					controller.setCurUser((UserBean)commBean.getParameters().get("user"));
					return new Boolean(true);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new Boolean(false);
		} else {
			return new Boolean(false);	
		}
		
	}

}
