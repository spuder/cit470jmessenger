package qc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import ui.MainFrame;

public class AddUserBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		HashMap map = (HashMap) arg0.get(1);
		String password = (String) map.get("password");
		String username = (String) map.get("username");
		Connection con = (Connection) ((MainFrame) arg0.get(0)).getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement insert = null;
		try {
			insert = con.prepareStatement("INSERT INTO Users (UserName, UserPass) VALUES (?,?)");
			insert.setString(1, username);
			insert.setString(2, password);
			insert.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

}
