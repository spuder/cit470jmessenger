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

public class GetFileListBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("fileListResponse");
		HashMap responseParams = new HashMap();
		
		HashMap params = (HashMap) arg0.get(1);
		String id = (String) params.get("sessionId");
		
		responseParams.put("sessionId", id);
		
		Connection con = (Connection)MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		Vector sessions = new Vector();
		try {
			System.out.println("Server: Finding File List");
			select = con.prepareStatement("SELECT f.FileName, u.UserName ,f.FileDescription, f.FileSendTime FROM File f JOIN User u ON u.UserID = f.UserID WHERE f.FileActiveFlag = 1 AND f.SessionID = (SELECT SessionID FROM Session WHERE SessionNumber = ?)");
			select.setString(1, id);
			results = select.executeQuery();
			while(results.next()) {
				String[] session = {results.getString(1), results.getString(2), results.getString(3), results.getTimestamp(4).toString()};
				System.out.println("File: " + session[0]);
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
		return commBean;
	}
	
}
