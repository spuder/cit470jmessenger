package quickConnect;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import beans.FileBean;

public class ActualFileUploadBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		// Get the file from local
		HashMap params = (HashMap) arg0.get(0);
		FileBean file = (FileBean) params.get("file");
		File fileToSend = file.getFile().getAbsoluteFile();
		
		String ip = (String)params.get("ip");
		int port = (Integer)params.get("port");
		// Connect to socket
		try {
			Socket socket = new Socket(ip,port);
			
			// Write File
			byte [] mybytearray  = new byte [(int)fileToSend.length()];
		      FileInputStream fis = new FileInputStream(fileToSend);
		      BufferedInputStream bis = new BufferedInputStream(fis);
		      bis.read(mybytearray,0,mybytearray.length);
		      OutputStream os = socket.getOutputStream();
		      System.out.println("Sending..." + fileToSend.length());
		      os.write(mybytearray,0,mybytearray.length);
		      os.flush();
		      socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
