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
		
		Connection con = (Connection) ((MainFrame) arg0.get(0)).getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		Vector users = new Vector();
		try {
			select = con.prepareStatement("SELECT * from Users");
			results = select.executeQuery();
			while(results.next()) {
				String[] user = {results.getString(2), results.getString(4)};
			}
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return users;
	}

}
