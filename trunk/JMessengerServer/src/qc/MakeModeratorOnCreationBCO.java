package qc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import beans.CommunicationBean;

import com.mysql.jdbc.Connection;

public class MakeModeratorOnCreationBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		HashMap map = (HashMap) arg0.get(1);
		CommunicationBean comBean = (CommunicationBean) arg0.get(2);
		HashMap responseMap = comBean.getParameters();
		String username = (String) map.get("userName");
		String session = (String) responseMap.get("sessionId");
		Connection con = (Connection)MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement insert = null;
		java.sql.PreparedStatement update = null;
		String success = "false";
		try {
			insert = con.prepareStatement("INSERT INTO Moderator (UserID, SessionID) VALUES ((SELECT UserID FROM User WHERE UserName=?),(SELECT SessionID FROM Session WHERE SessionNumber=?))");
			insert.setString(1, username);
			insert.setString(2, session);
			insert.execute();
			update = con.prepareStatement("UPDATE SessionParticipants SET SessionRole = (SELECT CommonLookupID FROM CommonLookup WHERE CommonLookupDescription = 'MODERATOR') WHERE UserID = (SELECT UserID From User WHERE UserName = ?) AND SessionID = (SELECT SessionID FROM Session WHERE SessionNumber = ?)");
			update.setString(1, username);
			update.setString(2, session);
			update.execute();
			success = "true";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return success;
	}

}
