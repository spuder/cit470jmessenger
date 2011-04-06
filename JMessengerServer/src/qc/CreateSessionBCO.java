package qc;

import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.quickconnect.ControlObject;
import ui.MainFrame;
import com.mysql.jdbc.Connection;

import controller.ServerConnectionHandler;
import beans.CommunicationBean;
import beans.SessionBean;

public class CreateSessionBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		HashMap params = (HashMap) arg0.get(1);
		String name = (String) params.get("sessionName");
		String password = null;
		//possible password
		if( params.containsKey("password")){
			password = (String) params.get("password");
		}
		
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("sessionCreated");
		HashMap responseParams = new HashMap();
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		
		try {
			SessionBean session = new SessionBean();
			session.setSessionName(name);
			select = con.prepareStatement("INSERT INTO Session (SessionNumber,SessionName,SessionActive,SessionPassword) values (?,?,1,?)");
			select.setString(1, session.getSessionId());
			select.setString(2, session.getSessionName());
			if(password != null){
				select.setString(3, password);
			} else {
				select.setNull(3, java.sql.Types.VARCHAR);
			}
			select.execute();
			System.out.println("Server: Session " + session.getSessionName() + " Created with ID " + session.getSessionId());
			responseParams.put("success", true);
			responseParams.put("sessionId", session.getSessionId());
			responseParams.put("session", session);
			
			HashMap connections = MainFrame.mainFrame.getController().getConnectionMap();
			connections.put(session.getSessionId(), new HashMap<String, ObjectOutputStream>());
			Object obj = arg0.get(0);
			if( obj instanceof ServerConnectionHandler && password != null){
				System.out.println("Inserting session password for creator");
				ServerConnectionHandler handler = (ServerConnectionHandler) arg0.get(0);
				handler.getPasswordMap().put(session.getSessionId(), password);
				responseParams.put("password",password);
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
