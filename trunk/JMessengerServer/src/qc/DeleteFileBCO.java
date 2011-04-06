package qc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

public class DeleteFileBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		HashMap params = (HashMap) arg0.get(1);
		String fileId = (String) params.get("fileId");
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement delete = null;
		boolean worked = false;
		try {
			delete = con.prepareStatement("UPDATE File SET FileActiveFlag=0 WHERE FileNumber=? AND FileActiveFlag=1");
			delete.setString(1, fileId);
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
