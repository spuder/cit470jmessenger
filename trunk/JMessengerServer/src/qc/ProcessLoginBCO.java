package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import beans.CommunicationBean;
import beans.UserBean;

import com.mysql.jdbc.Connection;

import controller.ServerConnectionHandler;

public class ProcessLoginBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		//Fetch credentials
		HashMap params = (HashMap) arg0.get(arg0.size()-1);
		String un = (String) params.get("username");
		String pw = (String) params.get("password");
		
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("loginResponse");
		HashMap responseParams = new HashMap();
		
		//Check SQL
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		try {
			select = con.prepareStatement("SELECT u.UserName, c.CommonLookupDescription from User u JOIN CommonLookup c ON u.UserRole = c.CommonLookupID"
					+ " WHERE u.UserName = ? AND u.UserPass = ? AND u.UserActiveFlag = 1");
			select.setString(1, un);
			select.setString(2, pw);
			
			results = select.executeQuery();
			while(results.next()) {
				UserBean user = new UserBean();
				user.setUsername(results.getString(1));
				user.setRole(results.getString(2));
				ServerConnectionHandler handler = (ServerConnectionHandler)arg0.get(0);
				handler.setUser(user);
				
				responseParams.put("user", user);
				responseParams.put("response", false);
				System.out.println("Server: Login Successful as " + user.getUsername());
			}
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		commBean.setParameters(responseParams);
		return commBean;
	}

}
