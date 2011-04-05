package qc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import ui.MainFrame;

import beans.CommunicationBean;
import beans.FileBean;
import controller.ServerConnectionHandler;

public class ReceiveFileBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		ServerConnectionHandler connection = (ServerConnectionHandler) arg0.get(0);
		HashMap params = (HashMap) arg0.get(1);
		FileBean file = (FileBean) params.get("file");
		String sessionId = (String) params.get("sessionId");
		
		// Open New Socket
		int port = MainFrame.mainFrame.getController().getSerSock().getLocalPort()-1;
		try {
			ServerSocket fileSocket = getRandomFileSocket(port);
			Socket socket;
			
			// Send Message To Client
			CommunicationBean commBean = new CommunicationBean();
			params.put("ip", fileSocket.getInetAddress().getHostAddress());
			params.put("port", port);
			commBean.setCommand("sendFile");
			commBean.setParameters(params);
			connection.getOutputStream().writeObject(commBean);
			
			File tempFile = null;
			
			// Read in new file
			while(fileSocket.isBound()){
				System.out.println("waiting for connection...");
				socket = fileSocket.accept();
				System.out.println("waiting for file...");
							   
				int bytesRead;
			    int current = 0;
			    System.out.println("downloading file (" + file.getSize() + ")");
				byte [] mybytearray  = new byte [(int) file.getSize()];
			    InputStream is = socket.getInputStream();
			    tempFile =File.createTempFile(file.getFileName(), ".download");
			    FileOutputStream fos = new FileOutputStream(tempFile);
			    BufferedOutputStream bos = new BufferedOutputStream(fos);
			   // bytesRead = is.read(mybytearray,0,mybytearray.length);
			    //current = bytesRead;

			    int totalToSend = mybytearray.length;
			      int inc = 0;
			      while(totalToSend > 0){
			    	  System.out.println("receiving " + totalToSend + "more bytes");
			    	  if(totalToSend > 255){
			    		  inc = 255;
			    	  } else {
			    		  inc = totalToSend;
			    	  }
			    	  is.read(mybytearray, current, inc);
			    	  current += inc;
			    	  totalToSend -= inc;
			      }
			    
			    /*do {
			       bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
			       if(bytesRead >= 0) current += bytesRead;
			    } while(bytesRead > -1);*/

			    bos.write(mybytearray, 0 , current);
			    bos.flush();
			    long end = System.currentTimeMillis();
			    System.out.println("file received");
			    System.out.println("file size: " + tempFile.length());
			    bos.close();
			    socket.close();
			    fileSocket.close();
			    break;
			}
						
			return tempFile;
			
			} catch (IOException e) {
				// ECO about failed to upload
			e.printStackTrace();
			}
		
		return null;
	}
	
	private ServerSocket getRandomFileSocket(int startingPort){
		ServerSocket serSock = null;
		
		try{
			serSock = new ServerSocket(startingPort);
		} catch (BindException e){
			getRandomFileSocket(++startingPort);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return serSock;
	}

}
