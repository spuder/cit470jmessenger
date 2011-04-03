package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

import beans.CommunicationBean;
import beans.SessionBean;
import beans.UserBean;

import controller.ServerConnectionHandler;

public class AddSessionUserBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		HashMap params = (HashMap) arg0.get(1);
		String id = (String) params.get("sessionId");
		ServerConnectionHandler handler = (ServerConnectionHandler) arg0.get(0);
		UserBean curUser = handler.getUser();
		
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("sessionJoined");
		HashMap responseParams = new HashMap();
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		
		//try {
			//select = con.prepareStatement("INSERT INTO SessionParticipants ");
			//select.setString(,id);
			//select.setString(, curUser.getUsername());
			//select.setString(, ) // Session Role info
			//select.execute();
			
			
			responseParams.put("success", true);
		//} 
		/*catch (SQLException e1) {
			responseParams.put("success", false);
			e1.printStackTrace();
		}*/
		commBean.setParameters(responseParams);
		return commBean;
	}

}
