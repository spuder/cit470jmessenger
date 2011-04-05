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
			
			// Read in new file
			while(fileSocket.isBound()){
				System.out.println("waiting for connection...");
				socket = fileSocket.accept();
				System.out.println("waiting for file...");
							   
				int bytesRead;
			    int current = 0;
				byte [] mybytearray  = new byte [(int) file.getSize()];
			    InputStream is = socket.getInputStream();
			    FileOutputStream fos = new FileOutputStream(file.getFileName());
			    BufferedOutputStream bos = new BufferedOutputStream(fos);
			    bytesRead = is.read(mybytearray,0,mybytearray.length);
			    current = bytesRead;

			  /*  do {
			       bytesRead =
			          is.read(mybytearray, current, (mybytearray.length-current));
			       if(bytesRead >= 0) current += bytesRead;
			    } while(bytesRead > -1);*/

			    bos.write(mybytearray, 0 , current);
			    bos.flush();
			    long end = System.currentTimeMillis();
			    System.out.println("file received");
			    bos.close();
			    socket.close();
			    fileSocket.close();
			    break;
			}
						
			return new File(file.getFileName());
			
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
