package qc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;
import beans.CommunicationBean;
import beans.FileBean;

import com.mysql.jdbc.Connection;

import controller.ServerConnectionHandler;

public class GetRequestedVersionBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		ServerConnectionHandler handler = (ServerConnectionHandler) arg0.get(0);
		HashMap params = (HashMap) arg0.get(1);
		
		String fileId = (String) params.get("fileId");
		String timestamp = (String) params.get("timestamp");
		//String sessionId = (String) params.get("sessionId");
		
		CommunicationBean commBean = new CommunicationBean();
		commBean.setCommand("fileDownloadResponse");
		HashMap responseParams = new HashMap();
		
		Connection con = (Connection) MainFrame.mainFrame.getController().getConnectionPool().getConnection();
		java.sql.PreparedStatement select = null;
		ResultSet results = null;
		try {
			select = con.prepareStatement("SELECT FileName, FileData FROM File WHERE FileNumber = ? AND FileSendTime = ?");
			select.setString(1, fileId);
			select.setTimestamp(2, Timestamp.valueOf(timestamp));
			results = select.executeQuery();
			while(results.next()) {
				String fileName = results.getString(1);
				File file = File.createTempFile(fileName, ".tmp");
				FileOutputStream fos = new FileOutputStream(file);
				
				byte[] buffer = new byte[1];
				
				InputStream is = results.getBinaryStream(2);
				while (is.read(buffer) > 0) {
					fos.write(buffer);
				}
				
				fos.close();
				
				FileBean fileBean = new FileBean(file, fileName);
				fileBean.setFileId(fileId);
				responseParams.put("file", fileBean);
			}
		} 
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		commBean.setParameters(responseParams);
		return commBean;
	}

}
