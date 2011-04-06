package qc;

import java.sql.SQLException;
import java.util.ArrayList;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

import beans.UserBean;
import controller.ServerConnectionHandler;

public class LogOffUserBCO implements ControlObject{

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		UserBean user = ((ServerConnectionHandler) arg0.get(0)).getUser();
		String username = user.getUsername();
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		boolean worked = true;
		
		try {
			java.sql.PreparedStatement delete = con.prepareStatement("UPDATE User SET UserLoggedIn = 0 WHERE UserName = ? AND UserActiveFlag = 1");
			delete.setString(1, username);
			delete.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return worked;
	}

}
