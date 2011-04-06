package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;
import org.quickconnect.QuickConnect;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

import controller.ServerConnectionHandler;

public class CheckForLoggedInUserValCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		HashMap params = (HashMap) arg0.get(arg0.size()-1);
		String username = (String) params.get("username");
		
		ServerConnectionHandler handler = (ServerConnectionHandler) arg0.get(0);
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		
		try {
			select = con.prepareStatement("SELECT UserID FROM User WHERE UserName=? AND UserLoggedIn = 1 AND UserActiveFlag = 1");
			select.setString(1, username);
			results = select.executeQuery();
			if (results.next()) {
				ArrayList errors = new ArrayList();
				HashMap errorsMap = new HashMap();
				errorsMap.put("message", "This user is already logged in.");
				errors.add(handler);
				errors.add(errorsMap);
				QuickConnect.handleError("sendError", errors);
			}
			else {
				MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return false;
	}

}
