package com.xtwsoft.mapserver.file;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import com.xtwsoft.mapserver.web.ServerConfig;

public class FileDataManager {

	private static FileDataManager m_instance = null;
	private static ObjectMapper fileData_mapper= new ObjectMapper();
	private Map<String,FileDataOfProject> managerMap = new HashMap<String,FileDataOfProject>();
	private FileDataManager(){
		
	}
	public static void initInstance() {
		if(m_instance == null){
			m_instance = new FileDataManager();
			File projectsPath = ServerConfig.getInstance().getProjectsPath();
			if(projectsPath != null) {
				File [] projects = projectsPath.listFiles();
				for (int i = 0; i < projects.length; i++) {
					if (projects[i].isDirectory()) {
						File sourceProps = new File(projects[i], "props");
						if (sourceProps.exists()) {
							File props = new File(sourceProps, "props.json");
							if (!props.exists()) {
								writeFileDatasJson(props);
							}
							m_instance.managerMap.put(projects[i].getName(),new FileDataOfProject(props));
						} else {
							if (sourceProps.mkdirs()) {
								File props = new File(sourceProps, "props.json");
								writeFileDatasJson(props);
								m_instance.managerMap.put(projects[i].getName(),new FileDataOfProject(props));
							}
						}
					}
				}
			}
		}
	}
	
	public static FileDataManager getInstance(){
		return m_instance;
	}
	
	private static synchronized void writeFileDatasJson(File sourceProps){
		try {
			FileOutputStream fos = new FileOutputStream(sourceProps);
			JsonGenerator jsonGenerator = fileData_mapper.getJsonFactory().createJsonGenerator(fos, JsonEncoding.UTF8);
			jsonGenerator.writeObject(new FileDatas());
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public FileDataOfProject getFileDataManagerForProject(String projectName){
		return managerMap.get(projectName);
	}


}
