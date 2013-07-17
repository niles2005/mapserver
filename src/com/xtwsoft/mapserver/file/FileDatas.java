package com.xtwsoft.mapserver.file;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSONObject;
import com.xtwsoft.mapserver.project.Project;
import com.xtwsoft.mapserver.web.FileUploader;
import com.xtwsoft.mapserver.web.MD5sum;
import com.xtwsoft.mapserver.web.Params;

public class FileDatas { 
	private Map<String,FileData> fileMap = new Hashtable<String,FileData>();
	private static ObjectMapper fileData_mapper= new ObjectMapper();

	public FileDatas() {
	}
	
	private File m_sourcePath = null;
	private Project m_project = null;
	private File m_jsonFile = null;
	public static FileDatas buildFileDatas(Project project) {
		FileDatas fileDatas = null;
		File projectPath = project.fetchProjectPath();
		if(projectPath != null && projectPath.exists()) {
			File sourcePath = new File(projectPath,"source");
			if(!sourcePath.exists()) {
				sourcePath.mkdir();
			}
			
			File jsonFile = new File(projectPath, "props.json");
			if (!jsonFile.exists()) {
				fileDatas = new FileDatas();
			}else{
				try {
					fileDatas = fileData_mapper.readValue(jsonFile, FileDatas.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
			if(fileDatas != null) {
				fileDatas.m_jsonFile = jsonFile;
				fileDatas.m_project = project;
				fileDatas.m_sourcePath = sourcePath;
			}
		}
		return fileDatas;
	}
	
	public boolean propsFileUpdate(){
		File projectPath = m_project.fetchProjectPath();
		if(projectPath != null && projectPath.exists()) {
			File sourcePath = new File(projectPath,"source");
			int fileCount = fileMap.size();
			if(sourcePath.isDirectory()){
				 File [] uploadedFiles = sourcePath.listFiles();
				 for (File file: uploadedFiles){
					 String fileName = file.getName();
					 FileData fileData = fileMap.get(fileName);
					 if(fileData == null){
						 fileData = new FileData(fileName,file.length());
						 fileData.setCreateTime(file.lastModified());
						 fileMap.put(fileName, fileData);
					 }
				 }
			}
			if (fileCount < fileMap.size()) {
				this.savePropFile();
				return true;
			}
		}
		
		return false;
	}
	
	public Project fetchProject() {
		return this.m_project;
	}
	
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
	
	public File fetchPropsFile(){
		return m_jsonFile;
	}
	
	public File fetchSourcePath() {
		return this.m_sourcePath;
	}
	
	public synchronized void savePropFile() {
		try {
			FileOutputStream fos = new FileOutputStream(m_jsonFile);
			JsonGenerator jsonGenerator = fileData_mapper.getJsonFactory().createJsonGenerator(fos, JsonEncoding.UTF8);
			jsonGenerator.writeObject(this);
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	public String listFiles() {
		try {
			ObjectMapper mapper= new ObjectMapper();
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String buildFileMD5(String fileName) {
		String md5sum = MD5sum.getHash(this.m_sourcePath.toString()+"/"+fileName, "MD5");
		
		Object fileDataObject = fetchFileData(fileName);
		if(fileDataObject instanceof JSONObject){
			System.out.println("***********JSONObject ");
			JSONObject fileDataJSON = (JSONObject)fileDataObject;
			FileData fileData = (FileData)JSONObject.toJavaObject(fileDataJSON, FileData.class); 
			fileData.setMd5Sum(md5sum);
			addFileData(fileData);
		}else{
			System.out.println("***********FileData Object");
			FileData fileData = (FileData)fileDataObject;
			fileData.setMd5Sum(md5sum);
			addFileData(fileData);
		}
		
		savePropFile();
		return md5sum;
	}
	
	public String upLoadFile(Params params) {
		return new FileUploader().doFileUpLoad(params, this);
	}
}
