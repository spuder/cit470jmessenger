package quickConnect;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import userInterface.MainFrame;

import beans.FileBean;

public class ActualReceiveFileBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		// Get the file from local
		HashMap params = (HashMap) arg0.get(0);
		FileBean file = (FileBean) params.get("file");
		File fileToSend = file.getFile().getAbsoluteFile();
		
		File tempFile = null;
		
		String ip = MainFrame.mainFrame.getController().getIpAddress();//(String)params.get("ip");
		int port = (Integer)params.get("port");
		int size = (Integer)params.get("size");
		// Connect to socket
		try {
			Socket socket = new Socket(ip,port);
			int bytesRead;
		    int current = 0;
		    System.out.println("downloading file (" + size + ")");
			byte [] mybytearray  = new byte [size];
		    InputStream is = socket.getInputStream();
		    tempFile = File.createTempFile(file.getFileName(), ".download");
		    FileOutputStream fos = new FileOutputStream(tempFile);
		    BufferedOutputStream bos = new BufferedOutputStream(fos);
		    bytesRead = is.read(mybytearray,0,mybytearray.length);
		    current = bytesRead;
	
		    FileDescriptor fd = fos.getFD();
		  /*  do {
		       bytesRead =
		          is.read(mybytearray, current, (mybytearray.length-current));
		       if(bytesRead >= 0) current += bytesRead;
		    } while(bytesRead > -1);*/
	
		    bos.write(mybytearray, 0 , current);
		    bos.flush();
		    long end = System.currentTimeMillis();
		    System.out.println("file received. size " + tempFile.length());
		    bos.close();
		    socket.close();
		
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempFile;
	}

}
