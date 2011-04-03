package qc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

public class UpdateRoleBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		HashMap map = (HashMap) arg0.get(1);
		String username = (String) map.get("username");
		String role = (String) map.get("role");
		Connection con = (Connection) ((MainFrame) arg0.get(0)).getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement update = null;
		boolean worked = false;
		try {
			update = con.prepareStatement("UPDATE User SET UserRole=(SELECT CommonLookupID from CommonLookup WHERE CommonLookupDescription=?) WHERE UserName=?");
			update.setString(1, role);
			update.setString(2, username);
			update.execute();
			worked = true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return worked;
	}

}
