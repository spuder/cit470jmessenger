package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;
import org.quickconnect.QuickConnect;

import ui.MainFrame;
import beans.UserBean;

import com.mysql.jdbc.Connection;

import controller.ServerConnectionHandler;

public class CheckPasswordValCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		if(!(arg0.get(arg0.size()-1).getClass()==Boolean.class)){
			System.out.println("Checking password");
			HashMap map = (HashMap) arg0.get(1);
			String session = (String) map.get("sessionId");
			ServerConnectionHandler handler = (ServerConnectionHandler) arg0.get(0);
			UserBean curUser = handler.getUser();
			
			Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
			java.sql.PreparedStatement select = null;
			ResultSet results = null;
			
			try {
				select = con.prepareStatement("SELECT SessionPassword FROM Session WHERE SessionNumber = ? AND SessionPassword IS NOT NULL");
				select.setString(1, session);
				results = select.executeQuery();
				
				if (results.next()) {
					
					String sessionPassword = results.getString(1);
					/******************************
					 *  CHECK PASSWORD STUFF HERE *
					 ******************************/
					
					if(handler.getPasswordMap().containsKey(session)){
						if(((String)handler.getPasswordMap().get(session)).equals(sessionPassword)){
							return true; // successful login
						} else {
							handler.getPasswordMap().remove(session); // remove the bad password
						}
					} 
					
					String password = (String) map.get("password");
					
					if(password.equals(sessionPassword)){
						handler.getPasswordMap().put(session, password); // add new password to map
						return true; // successful login
					}
					
					// HOPE IT DOESNT GET HERE
					ArrayList errors = new ArrayList();
					HashMap errorsMap = new HashMap();
					errorsMap.put("message", "Intraction with this session requires authentication");
					errors.add(handler);
					errors.add(errorsMap);
					QuickConnect.handleError("sendError", errors);
					return false;
					
					
				}else {
					MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
					return true; // NO Password
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
			
			return true;
		} else {
		
			return true;
		}
	}

}
