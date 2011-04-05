package qc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import controller.ServerConnectionHandler;

import ui.MainFrame;

import beans.CommunicationBean;
import beans.FileBean;

public class SendRequestedFileBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		ServerConnectionHandler connection = (ServerConnectionHandler) arg0.get(0);
		CommunicationBean commBean = (CommunicationBean) arg0.get(arg0.size()-1);
		HashMap params = commBean.getParameters();
		FileBean fileBean = (FileBean) params.get("file");
		File file = fileBean.getFile();
		
		// Open New Socket
		int port = MainFrame.mainFrame.getController().getSerSock().getLocalPort()-1;
		try {
			ServerSocket fileSocket = getRandomFileSocket(port);
			Socket socket;
			
			// Send Message To Client
			params.put("ip", fileSocket.getInetAddress().getHostAddress());
			params.put("port", port);
			params.put("size", (int)file.length());
			commBean.setParameters(params);
			connection.getOutputStream().writeObject(commBean);
			
			// Read in new file
			while(fileSocket.isBound()){
				System.out.println("waiting for connection...");
				socket = fileSocket.accept();
				System.out.println("waiting for file...");
							   
				byte [] mybytearray  = new byte [(int)file.length()];
			    FileInputStream fis = new FileInputStream(file);
			    BufferedInputStream bis = new BufferedInputStream(fis);
			    bis.read(mybytearray,0,mybytearray.length);
		        OutputStream os = socket.getOutputStream();
		        System.out.println("Sending..." + file.length());
			    os.write(mybytearray,0,mybytearray.length);
			    os.flush();
			    socket.close();
								
			    fileSocket.close();
			    break;
			}
						
			return new File(fileBean.getFileName());
			
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
