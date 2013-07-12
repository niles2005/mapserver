package com.xtwsoft.mapserver.file;

import java.util.Hashtable;
import java.util.Map;

public class FileDatas { 
	private Map<String,FileData> fileMap = new Hashtable<String,FileData>();

	public Map<String, FileData> getFileMap() {
		return fileMap;
	}

	public void setFileMap(Map<String, FileData> fileDatas) {
		this.fileMap = fileDatas;
	}
	
	public void addFileData(FileData fileData){
		fileMap.put(fileData.getName(), fileData);
	}
	
	public FileData fetchFileData(String fileDataName){
		return fileMap.get(fileDataName);
	}

}
