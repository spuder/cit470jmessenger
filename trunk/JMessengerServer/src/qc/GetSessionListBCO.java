package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import beans.CommunicationBean;

import com.mysql.jdbc.Connection;

public class GetSessionListBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("sessionListResponse");
		HashMap responseParams = new HashMap();
		
		Connection con = (Connection)MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		Vector sessions = new Vector();
		try {
			select = con.prepareStatement("SELECT SessionNumber, SessionName from Session WHERE SessionActive = 1");
			results = select.executeQuery();
			while(results.next()) {
				String[] session = {results.getString(2), results.getString(1)};
				sessions.add(session);
			}
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		responseParams.put("list",sessions);
		commBean.setParameters(responseParams);
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return commBean;
	}

}
