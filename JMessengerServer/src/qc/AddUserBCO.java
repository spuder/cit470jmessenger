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
		Connection con = (Connection) ((MainFrame) arg0.get(0)).getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		try {
			select = con.prepareStatement("SELECT UserName from Users WHERE UserName = ?");
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
					insert = con.prepareStatement("INSERT INTO Users (UserName, UserPass) VALUES (?,?)");
					insert.setString(1, username);
					insert.setString(2, password);
					insert.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CommunicationBean response = new CommunicationBean();
				response.setCommand("addUserResponse");
				HashMap responseMap = new HashMap();
				responseMap.put("success", true);
				response.setParameters(map);
			}
			else {
				CommunicationBean response = new CommunicationBean();
				response.setCommand("addUserResponse");
				HashMap responseMap = new HashMap();
				responseMap.put("success", false);
				response.setParameters(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
