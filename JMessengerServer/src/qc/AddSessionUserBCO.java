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

		System.out.println("Adding Session User");
		HashMap params = (HashMap) arg0.get(1);
		String id = (String) params.get("sessionId");
		ServerConnectionHandler handler = (ServerConnectionHandler) arg0.get(0);
		UserBean curUser = handler.getUser();
		String userRole = curUser.getRole();
		
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("sessionJoined");
		HashMap responseParams = new HashMap();
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		java.sql.PreparedStatement select1 = null;
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
				
				if(curUser.getRole().equals("USER")) {
					select1 = con.prepareStatement("SELECT ModeratorID FROM Moderator WHERE UserID = (SELECT UserID FROM User WHERE UserName = ?) AND SessionID = (SELECT SessionID FROM Session WHERE SessionNumber = ?)");
					select1.setString(1, curUser.getUsername());
					select1.setString(2, id);
					results = select1.executeQuery();
					if (results.next()) {
						userRole = "MODERATOR";
					}
					
				}
				
				
			
				select = con.prepareStatement("INSERT INTO SessionParticipants (SessionID, UserID, SessionRole) VALUES ((SELECT SessionID from Session WHERE SessionNumber = ?),(SELECT UserID FROM User WHERE UserName = ?), (SELECT CommonLookupID FROM CommonLookup WHERE CommonLookupDescription = ?))");
				select.setString(1, session.getSessionId());
				select.setString(2, curUser.getUsername());
				select.setString(3, userRole);
				
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
