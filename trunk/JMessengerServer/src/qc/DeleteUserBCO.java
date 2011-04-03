package qc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import beans.CommunicationBean;

import com.mysql.jdbc.Connection;

public class DeleteUserBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		HashMap map = (HashMap) arg0.get(1);
		String username = (String) map.get("username");
		Connection con = (Connection) ((MainFrame) arg0.get(0)).getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement delete = null;
		boolean worked = false;
		try {
			delete = con.prepareStatement("DELETE FROM User WHERE UserName=?");
			delete.setString(1, username);
			delete.execute();
			worked = true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return worked;
	}

}
