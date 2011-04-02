package qc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
		CommunicationBean response = new CommunicationBean();
		try {
			select = con.prepareStatement("SELECT * from Users");
			results = select.executeQuery();
			HashMap responseMap = new HashMap();
			responseMap.put("userList", results);
			response.setCommand("UserListResponse");
			response.setParameters(responseMap);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return response;
	}

}
