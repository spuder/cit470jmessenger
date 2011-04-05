package qc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

public class MakeModeratorBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		HashMap map = (HashMap) arg0.get(1);
		String username = (String) map.get("userName");
		String session = (String) map.get("sessionId");
		Connection con = (Connection)MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement insert = null;
		String success = "false";
		try {
			insert = con.prepareStatement("INSERT INTO Moderator (UserID, SessionID) VALUES ((SELECT UserID FROM User WHERE UserName=?),(SELECT SessionID FROM Session WHERE SessionNumber=?))");
			insert.setString(1, username);
			insert.setString(2, session);
			insert.execute();
			success = "true";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
	}

}
