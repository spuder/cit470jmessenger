package qc;

import java.sql.SQLException;
import java.util.ArrayList;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

public class CleanUpDatabaseDataBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement update = null;
		
		try {
			update = con.prepareStatement("UPDATE User SET UserLoggedIn = 0");
			update.execute();
			update = con.prepareStatement("DELETE FROM SessionParticipants");
			update.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		boolean success = true;
		return success;
	}

}
