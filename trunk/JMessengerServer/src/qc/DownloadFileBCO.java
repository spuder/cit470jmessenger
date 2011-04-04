package qc;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.quickconnect.ControlObject;

import beans.FileBean;

public class DownloadFileBCO implements ControlObject {

	@Override
	public Object handleIt(ArrayList<Object> arg0) {

		HashMap params = (HashMap) arg0.get(0);
		FileBean fileBean = (FileBean) params.get("file");
		File file = fileBean.getFile();
		
		return null;
	}

}
