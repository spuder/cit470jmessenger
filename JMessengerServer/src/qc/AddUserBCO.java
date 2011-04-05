package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import beans.CommunicationBean;

import com.mysql.jdbc.Connection;

import ui.MainFrame;

public class AddUserBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		HashMap map = (HashMap) arg0.get(1);
		String password = (String) map.get("password");
		String username = (String) map.get("username");
		String role = (String) map.get("role");
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		CommunicationBean response = new CommunicationBean();
		try {
			select = con.prepareStatement("SELECT UserName from User WHERE UserName = ?");
			select.setString(1, username);
			results = select.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if (!results.next()) {
				
				java.sql.PreparedStatement insert = null;
				try {
					insert = con.prepareStatement("INSERT INTO User (UserName, UserPass, UserRole) VALUES (?,?, (SELECT CommonLookupID from CommonLookup WHERE CommonLookupDescription = ?))");
					insert.setString(1, username);
					insert.setString(2, password);
					insert.setString(3, role);
					insert.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setCommand("addUserResponse");
				HashMap responseMap = new HashMap();
				responseMap.put("success", "true");
				response.setParameters(map);
			}
			else {
				response.setCommand("addUserResponse");
				HashMap responseMap = new HashMap();
				responseMap.put("success", "false");
				response.setParameters(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return response;
	}

}
