package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

public class GetSessionUsersBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		HashMap map = (HashMap) arg0.get(1);
		String session = (String) map.get("sessionId");
		System.out.println("Session: " +session);
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		Vector users = new Vector();
		try {
			select = con.prepareStatement("SELECT u.UserName, c.CommonLookupDescription from User u, CommonLookup c, SessionParticipants sp, Session s WHERE sp.SessionRole = c.CommonLookupID AND sp.SessionID = s.SessionID AND s.SessionNumber = ? AND sp.UserID = u.UserID");
			select.setString(1, session);
			results = select.executeQuery();
			while(results.next()) {
				String[] user = {results.getString(1), results.getString(2)};
				users.add(user);
			}
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return users;
	}

}
