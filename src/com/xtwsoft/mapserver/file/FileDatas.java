package com.xtwsoft.mapserver.file;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

public class FileDatas { 
	private Map<String,FileData> fileMap = new Hashtable<String,FileData>();
	private static ObjectMapper fileData_mapper= new ObjectMapper();

	public Map<String, FileData> getFileMap() {
		return fileMap;
	}

	public void setFileMap(Map<String, FileData> fileDatas) {
		this.fileMap = fileDatas;
	}
	
	public void addFileData(FileData fileData){
		fileMap.put(fileData.getName(), fileData);
	}
	
	public Object fetchFileData(String fileDataName){
		return fileMap.get(fileDataName);
	}
	
	public  synchronized void writeFileDatasJson(File propsPath){
		try {
			FileOutputStream fos = new FileOutputStream(propsPath);
			JsonGenerator jsonGenerator = fileData_mapper.getJsonFactory().createJsonGenerator(fos, JsonEncoding.UTF8);
			jsonGenerator.writeObject(this);
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
