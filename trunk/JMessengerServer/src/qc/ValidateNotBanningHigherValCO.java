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

public class ValidateNotBanningHigherValCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		ServerConnectionHandler connection = (ServerConnectionHandler) arg0.get(0);
		String role = connection.getUser().getRole();
		HashMap map = (HashMap) arg0.get(1);
		String sessionId = (String) map.get("sessionId");
		String banningUserRole = "";
		String banningUser = connection.getUser().getUsername();
		String userToBanRole = "";
		String userToBan = (String) map.get("userToBan");
		Connection con = (Connection)MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		
		try {
			select = con.prepareStatement("SELECT CommonLookupDescription FROM CommonLookup WHERE CommonLookupID = (SELECT SessionRole FROM SessionParticipants WHERE UserID = (SELECT UserID FROM User WHERE UserName = ?) AND SessionID = (SELECT SessionID FROM Session WHERE SessionNumber = ?))");
			select.setString(1, banningUser);
			select.setString(2, sessionId);
			results = select.executeQuery();
			
			while(results.next()) {
				banningUserRole = results.getString(1);
			}
			
			select = con.prepareStatement("SELECT CommonLookupDescription FROM CommonLookup WHERE CommonLookupID = (SELECT SessionRole FROM SessionParticipants WHERE UserID = (SELECT UserID FROM User WHERE UserName = ?) AND SessionID = (SELECT SessionID FROM Session WHERE SessionNumber = ?))");
			select.setString(1, userToBan);
			select.setString(2, sessionId);
			results = select.executeQuery();
			
			while(results.next()) {
				userToBanRole = results.getString(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (banningUserRole.equals("ADMIN") && userToBanRole.equals("ADMIN")) {
			MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
			ArrayList errors = new ArrayList();
			HashMap errorsMap = new HashMap();
			errorsMap.put("message", "You cannot ban this person.");
			errors.add(connection);
			errors.add(errorsMap);
			System.out.println("Sending Error");
			QuickConnect.handleError("sendError", errors);
		}
		else if (banningUserRole.equals("MODERATOR") && userToBanRole.equals("MODERATOR")) {
			MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
			ArrayList errors = new ArrayList();
			HashMap errorsMap = new HashMap();
			errorsMap.put("message", "You cannot ban this person.");
			errors.add(connection);
			errors.add(errorsMap);
			System.out.println("Sending Error");
			QuickConnect.handleError("sendError", errors);
			
		}
		else if (banningUserRole.equals("MODERATOR") && userToBanRole.equals("ADMIN")) {
			MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
			ArrayList errors = new ArrayList();
			HashMap errorsMap = new HashMap();
			errorsMap.put("message", "You cannot ban this person.");
			errors.add(connection);
			errors.add(errorsMap);
			System.out.println("Sending Error");
			QuickConnect.handleError("sendError", errors);
		}
		else {
			MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
			return true;
		}
		
		return false;
	}

}
