package qc;

import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

import beans.MessageBean;
import beans.SessionBean;

public class ParseMessageBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		HashMap params = (HashMap) arg0.get(1);
		MessageBean msg = (MessageBean) params.get("message");
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		
		try {
			select = con.prepareStatement("INSERT Message (UserID,SessionID,MessageSendTime,Message) "
					+ "SELECT UserID, (SELECT SessionID FROM Session WHERE SessionNumber = ?), ?, ? FROM User WHERE UserName = ?");
			select.setString(1, msg.getSessionid());
			select.setTimestamp(2, msg.getTimestamp());
			select.setString(3, msg.getMessage());
			select.setString(4, msg.getSender());
			select.execute();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		}
		MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		return msg;
	}

}
