package beans;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;

public class FileBean implements Serializable {

	File file;
	String fileName;
	Timestamp timestamp;
	
	public FileBean(File file, String fileName){
		this.file = file;
		this.fileName = fileName;
		timestamp = new Timestamp(System.currentTimeMillis());
	}
	
	public FileBean(File file, String fileName, Timestamp timestamp){
		this.file = file;
		this.fileName = fileName;
		this.timestamp = timestamp;
	}
	
}
