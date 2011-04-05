package quickConnect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFileChooser;

import org.quickconnect.ControlObject;

import beans.FileBean;

public class DownloadFileBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		//HashMap params = (HashMap) arg0.get(0);
		//FileBean fileBean = (FileBean) params.get("file");
		File file = (File) arg0.get(arg0.size()-1);
		
		JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));  
		int returnVal = fileChooser.showSaveDialog(null);  
		
		if(returnVal == JFileChooser.APPROVE_OPTION){
			if(fileChooser.getSelectedFile()!=null){
				File theFileToSave = fileChooser.getSelectedFile();
				try {
					copy(file,theFileToSave);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}
	
	void copy(File src, File dst) throws IOException {
	    InputStream in = new FileInputStream(src);
	    OutputStream out = new FileOutputStream(dst);

	    // Transfer bytes from in to out
	    byte[] buf = new byte[1024];
	    int len;
	    while ((len = in.read(buf)) > 0) {
	        out.write(buf, 0, len);
	    }
	    in.close();
	    out.close();
	}

}
