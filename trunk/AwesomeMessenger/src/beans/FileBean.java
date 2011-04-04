package beans;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;

public class FileBean implements Serializable {

	private static final long serialVersionUID = -2555662700007433964L;
	public static int id = 0;
	
	File file;
	String fileName;
	String desc;
	Timestamp timestamp;
	String fileId;
	
	public FileBean(File file, String fileName){
		this.file = file;
		this.fileName = fileName;
		timestamp = new Timestamp(System.currentTimeMillis());
		this.fileId = timestamp.toString() + "-" + fileName + "-" + id++; 
	}
	
	public FileBean(File file, String fileName, Timestamp timestamp){
		this.file = file;
		this.fileName = fileName;
		this.timestamp = timestamp;
		this.fileId = timestamp.toString() + "-" + fileName + "-" + id++;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	
	
}