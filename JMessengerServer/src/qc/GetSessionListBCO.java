package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

public class GetSessionListBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		Connection con = (Connection) ((MainFrame) arg0.get(0)).getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		Vector sessions = new Vector();
		try {
			select = con.prepareStatement("SELECT SessionNumber, SessionName from Session WHERE SessionActive = 1");
			results = select.executeQuery();
			while(results.next()) {
				String[] user = {results.getString(1), results.getString(2)};
				sessions.add(user);
			}
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return sessions;
	}

}
