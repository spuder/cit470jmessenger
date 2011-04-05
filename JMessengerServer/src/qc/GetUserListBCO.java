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

public class GetUserListBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		Vector users = new Vector();
		try {
			select = con.prepareStatement("SELECT u.UserName, c.CommonLookupDescription from User u JOIN CommonLookup c WHERE u.UserRole = c.CommonLookupID AND UserActiveFlag=1");
			results = select.executeQuery();
			while(results.next()) {
				String[] user = {results.getString(1), results.getString(2)};
				users.add(user);
			}
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return users;
	}

}
