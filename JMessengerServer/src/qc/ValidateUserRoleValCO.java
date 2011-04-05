package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

import controller.ServerConnectionHandler;

public class ValidateUserRoleValCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		ServerConnectionHandler connection = (ServerConnectionHandler) arg0.get(0);
		String role = connection.getUser().getRole();
		HashMap map = (HashMap) arg0.get(1);
		String sessionId = (String) map.get("sessionId");
		if (role.equals("ADMIN")) {
			return true;
		}
		else {
			Connection con = (Connection)MainFrame.mainFrame.getController().getConnectionPool().getConnection();
			java.sql.PreparedStatement select = null;
			ResultSet results = null;
			try {
				select = con.prepareStatement("SELECT * FROM Moderator WHERE UserID = (SELECT UserID FROM User WHERE UserName = ?) AND SessionID = (SELECT SessionID FROM Session WHERE SessionNumber = ?)");
				select.setString(1, connection.getUser().getUsername());
				select.setString(2, sessionId);
				results = select.executeQuery();
				if (results.next()) {
					MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
					return true;
				}
				MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

}
