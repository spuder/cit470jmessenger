package qc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import com.mysql.jdbc.Connection;

import ui.MainFrame;

public class AddBannedUserBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		HashMap map = (HashMap) arg0.get(1);
		String username = (String) map.get("userToBan");
		String session = (String) map.get("sessionId");
		Connection con = (Connection)MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement insert = null;
		boolean success = false;
		try {
			insert = con.prepareStatement("INSERT INTO BannedUser (UserID, SessionID) VALUES ((SELECT UserID from User WHERE UserNAme = ?),(SELECT SessionID from Session WHERE SessionNumber = ?))");
			insert.setString(1, username);
			insert.setString(2, session);
			insert.execute();
			success = true;
		} catch (SQLException e) {
			success = false;
			e.printStackTrace();
		}
		
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return success;
	}

}
