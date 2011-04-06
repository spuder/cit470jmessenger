package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;
import org.quickconnect.QuickConnect;

import ui.MainFrame;
import beans.UserBean;

import com.mysql.jdbc.Connection;

import controller.ServerConnectionHandler;

public class CheckForSessionPasswordValCO implements ControlObject {

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
			select = con.prepareStatement("SELECT SessionPassword FROM Session WHERE SessionNumber = ? AND SessionPassword IS NOT NULL");
			select.setString(1, session);
			results = select.executeQuery();
			
			if (results.next()) {
				if( handler.getPasswordMap().containsKey(session) || map.containsKey("password")){
					return true; // IS PASSWORD AND THEY HAVE SENT ONE
				} else {
					ArrayList errors = new ArrayList();
					errors.add(handler);
					errors.add(map);
					QuickConnect.handleError("sendPromptLogin", errors);
					MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
					return null;
				}
			}
			else {
				MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
				return true; // NO Password
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return true;
	}

}
