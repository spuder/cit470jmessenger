package qc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;
import beans.FileBean;

import com.mysql.jdbc.Connection;

import controller.ServerConnectionHandler;

public class DeactivateOldFileBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		ServerConnectionHandler connection = (ServerConnectionHandler) arg0.get(0);
		HashMap params = (HashMap) arg0.get(1);
		FileBean file = (FileBean) params.get("file");
		String sessionId = (String) params.get("sessionId");
		String fileNumber = file.getFileId();
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		
		try {
			select = con.prepareStatement("UPDATE File SET FileActiveFlag = 0 WHERE FileNumber = ?");
			select.setString(1, fileNumber);
			
			select.execute();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		} 
		
		 MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		
		return true;
	}

}
