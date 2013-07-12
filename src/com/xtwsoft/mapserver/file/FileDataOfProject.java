package com.xtwsoft.mapserver.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.xtwsoft.mapserver.web.ServerConfig;

public class FileDataOfProject {
	private FileDatas m_fileDatas = null;
	private static ObjectMapper fileData_mapper= new ObjectMapper();
	public FileDataOfProject(File file){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			reader.close();
			
			if(line.length() > 0) {
				m_fileDatas = JSON.parseObject(line, FileDatas.class);
			}
			if(m_fileDatas == null){
				m_fileDatas = new FileDatas();
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	
	}
	
	
	
	public void addFileData(FileData fileData){
		m_fileDatas.addFileData(fileData);
	}
	
	public FileDatas fetchFileDatas(){
		return m_fileDatas;
	}
	
	public FileData getFileData(String fileDataName){
		return m_fileDatas.fetchFileData(fileDataName);
	}
	
	public synchronized void writeFileDatasJson(File sourceProps){
		try {
			FileOutputStream fos = new FileOutputStream(sourceProps);
			JsonGenerator jsonGenerator = fileData_mapper.getJsonFactory().createJsonGenerator(fos, JsonEncoding.UTF8);

			if(m_fileDatas == null){
				jsonGenerator.writeObject(new FileDatas() );
				String jsonString = fileData_mapper.writeValueAsString(new FileDatas());
				System.out.println(jsonString);
			}else{
				jsonGenerator.writeObject(m_fileDatas);
				String jsonString = fileData_mapper.writeValueAsString(m_fileDatas);
				System.out.println(jsonString);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	

}
