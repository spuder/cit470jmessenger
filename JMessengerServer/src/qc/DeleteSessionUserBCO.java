package qc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

import beans.UserBean;

import controller.ServerConnectionHandler;

public class DeleteSessionUserBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		UserBean user = ((ServerConnectionHandler) arg0.get(0)).getUser();
		String username = user.getUsername();
		HashMap map = (HashMap) arg0.get(1);
		String sessionId = (String) map.get("sessionId");
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		boolean worked = false;
		try {
			java.sql.PreparedStatement delete = con.prepareStatement("DELETE FROM SessionParticipants WHERE UserID = (SELECT UserID FROM User WHERE UserName = ?) AND SessionID = (SELECT SessionID FROM Session WHERE SessionNumber = ?)");
			delete.setString(1, username);
			delete.setString(2, sessionId);
			delete.execute();
			worked = true;
		} catch (SQLException e) {
			worked = false;
			e.printStackTrace();
		}
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return worked;
	}

}
