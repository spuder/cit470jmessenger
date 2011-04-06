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

public class GetFileVersionsListBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("fileVersionListResponse");
		HashMap responseParams = new HashMap();
		
		HashMap params = (HashMap) arg0.get(1);
		String id = (String) params.get("sessionId");
		String fileId = (String) params.get("fileId");
		
		//responseParams.put("sessionId", id);
		responseParams.put("fileId", fileId);
		
		Connection con = (Connection)MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		Vector sessions = new Vector();
		try {
			System.out.println("Server: Finding File Versions List");
			select = con.prepareStatement("SELECT f.FileName, u.UserName ,f.FileDescription, f.FileSendTime FROM File f JOIN User u ON u.UserID = f.UserID WHERE f.FileNumber = ? ORDER BY f.FileSendTime");
			select.setString(1, fileId);
			results = select.executeQuery();
			while(results.next()) {
				String[] session = {results.getString(1), results.getString(2), results.getString(3), results.getTimestamp(4).toString()};
				System.out.println("File: " + session[0] + " At Time:" + session[3]);
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
