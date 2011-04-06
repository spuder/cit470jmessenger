package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;
import org.quickconnect.QuickConnect;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

import beans.UserBean;
import controller.ServerConnectionHandler;

public class CheckForBannedUserValCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		HashMap map = (HashMap) arg0.get(1);
		String session = (String) map.get("sessionId");
		ServerConnectionHandler handler = (ServerConnectionHandler) arg0.get(0);
		UserBean curUser = handler.getUser();
		String userName = curUser.getUsername();
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		
		try {
			select = con.prepareStatement("SELECT BannedUserID FROM BannedUser WHERE UserID=(SELECT UserID FROM User WHERE UserName = ?) AND SessionID=(SELECT SessionID FROM Session WHERE SessionNumber = ?)");
			select.setString(1, userName);
			select.setString(2, session);
			results = select.executeQuery();
			
			if (results.next()) {
				ArrayList errors = new ArrayList();
				HashMap errorsMap = new HashMap();
				errorsMap.put("message", "You have been banned from this session.");
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
