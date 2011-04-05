package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;
import beans.FileBean;

import com.mysql.jdbc.Connection;

import controller.ServerConnectionHandler;

public class StopSessionBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		HashMap params = (HashMap) arg0.get(1);
		String sessionId = (String) params.get("sessionId");
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		
		try {
			select = con.prepareStatement("UPDATE Session SET SessionActive = 0 WHERE SessionNumber = ?");
			select.setString(1, sessionId);
			select.execute();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		 MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		
		return true;
		
	}

}
