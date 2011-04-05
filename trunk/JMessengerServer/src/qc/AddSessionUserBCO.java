package qc;

import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

import beans.CommunicationBean;
import beans.SessionBean;
import beans.UserBean;

import controller.ServerConnectionHandler;

public class AddSessionUserBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		HashMap params = (HashMap) arg0.get(1);
		String id = (String) params.get("sessionId");
		ServerConnectionHandler handler = (ServerConnectionHandler) arg0.get(0);
		UserBean curUser = handler.getUser();
		
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("sessionJoined");
		HashMap responseParams = new HashMap();
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		
		try {
			select = con.prepareStatement("SELECT SessionID,SessionNumber,SessionName from Session WHERE SessionNumber = ? AND SessionActive = 1");
			select.setString(1, id);
			results = select.executeQuery();
			
			if(results.next()){
				
				SessionBean session = new SessionBean();
				int sessionPk = results.getInt(1);
				session.setSessionId(id);
				session.setSessionName(results.getString(3));
			
				select = con.prepareStatement("INSERT SessionParticipants (SessionID,UserID,SessionRole) SELECT ?,u.UserID,? FROM User u WHERE u.UserName = ?");
				select.setInt(1,sessionPk);
				select.setString(3, curUser.getUsername());
				if(curUser.getRole().equals("ADMIN")){
					select.setInt(2, 1);
				} else {
					
					Connection con2 = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
					java.sql.PreparedStatement subSelect = null;
					ResultSet subResult = null;
					
					subSelect = con2.prepareStatement("SELECT * FROM Moderator m JOIN User u ON u.UserID = m.UserID WHERE u.UserName = ? AND SessionID = ?");
					subSelect.setString(1, curUser.getUsername());
					subSelect.setInt(2, sessionPk);
					subResult = subSelect.executeQuery();
					if(results.next()){
						select.setInt(2, 3);
					} else {
						select.setInt(2, 2);  // User
					}
					MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con2);
				}
				select.execute();
		
				//Add user to the output stream for that session
				HashMap<String, HashMap<String, ObjectOutputStream>> sessions = MainFrame.mainFrame.getController().getConnectionMap();
				HashMap<String,ObjectOutputStream> sessionMap = sessions.get(id);
				if(sessionMap == null){
					sessionMap = new HashMap<String, ObjectOutputStream>();
					sessions.put(id, sessionMap);
				}
				sessionMap.put(curUser.getUsername(), handler.getOutputStream());
				
				responseParams.put("session", session);
				
				responseParams.put("success", true);
			} else {
				responseParams.put("success", false);
			}
		} 
		catch (SQLException e1) {
			responseParams.put("success", false);
			e1.printStackTrace();
		}
		commBean.setParameters(responseParams);
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return commBean;
	}

}
