package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;
import org.quickconnect.QuickConnect;

import ui.MainFrame;
import beans.FileBean;
import beans.MessageBean;
import beans.UserBean;

import com.mysql.jdbc.Connection;

import controller.ServerConnectionHandler;

public class CheckForBannedUserForFileValCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		HashMap params = (HashMap) arg0.get(1);
		String sessionId = (String) params.get("sessionId");
		ServerConnectionHandler handler = (ServerConnectionHandler) arg0.get(0);
		UserBean curUser = handler.getUser();
		String userName = curUser.getUsername();
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		
		try {
			select = con.prepareStatement("SELECT BannedUserID FROM BannedUser WHERE UserID=(SELECT UserID FROM User WHERE UserName = ?) AND SessionID=(SELECT SessionID FROM Session WHERE SessionNumber = ?)");
			select.setString(1, userName);
			select.setString(2, sessionId);
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
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
