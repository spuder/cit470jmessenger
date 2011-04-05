package qc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import com.mysql.jdbc.Connection;

import controller.ServerConnectionHandler;

import beans.FileBean;
import beans.SessionBean;

public class InsertNewFileBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {
		
		ServerConnectionHandler connection = (ServerConnectionHandler) arg0.get(0);
		HashMap params = (HashMap) arg0.get(1);
		FileBean file = (FileBean) params.get("file");
		String sessionId = (String) params.get("sessionId");
		File actualFile = (File) arg0.get(arg0.size()-1);
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		
		try {
			select = con.prepareStatement("INSERT File (UserID,SessionID,FileSendTime,FileName,FileDescription,FileData,FileActiveFlag,FileNumber) " +
							"SELECT (SELECT UserID FROM User WHERE UserName = ?), (SELECT SessionID FROM Session WHERE SessionNumber = ?)," +
							"?,?,?,?,1,? FROM DUAL");
			select.setString(1, connection.getUser().getUsername());
			select.setString(2, sessionId);
			select.setTimestamp(3, file.getTimestamp());
			select.setString(4, file.getFileName());
			select.setString(5, file.getDesc());

			FileInputStream fis = new FileInputStream(actualFile);
			select.setBinaryStream(6, fis, actualFile.length());
			
			select.setString(7, file.getFileId());
			
			select.execute();
		} 
		catch (SQLException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 MainFrame.mainFrame.getController().getConnectionPool().returnConnection(con);
		
		return true;
	}

}
