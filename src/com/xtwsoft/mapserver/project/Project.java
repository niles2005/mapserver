package com.xtwsoft.mapserver.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.xtwsoft.mapserver.file.FileDatas;


public class Project {
	
	private Long id;
	private String name;
	private String vendor;
	private String imageUrl;
	private Long createTime;
	private String creator;
	private String info;
	private Module module = new Module();
	private FileDatas fileDatas;
	private static ObjectMapper fileData_mapper= new ObjectMapper();
	
	public Module fetchModule() {
		return module;
	}
	
	public Module setModule(Module module) {
		return this.module = module;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	
	public String getVendor() {
		return this.vendor;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getImageUrl() {
		return this.imageUrl;
	}
	
	public void setCreateTime(Long date) {
		this.createTime = date;
	}
	
	public Long getCreateTime() {
		return this.createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	private File m_projectPath = null;
	private File m_sourcePath = null;
	private File m_props = null;
	public void doInit(File projectsPath) {
		if(m_projectPath == null || !m_projectPath.exists()) {
			m_projectPath = new File(projectsPath,this.name);
			m_projectPath.mkdir();
			
		}
		
		if(m_projectPath != null && m_projectPath.exists()) {
			m_sourcePath = new File(m_projectPath,"source");
			if(!m_sourcePath.exists()) {
				m_sourcePath.mkdir();
			}
			File propsPath = new File(m_projectPath,"props");
			if(!propsPath.exists()){
				propsPath.mkdir();
			}
			m_props = new File(propsPath, "props.json");
			if (!m_props.exists()) {
				fileDatas = new FileDatas();
				fileDatas.writeFileDatasJson(m_props);
			}else{
				try {
					fileDatas = fileData_mapper.readValue(m_props, FileDatas.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
	public File fetchPropsJson(){
		return m_props;
	}
	
	public FileDatas fetchFileDatas(){
		return fileDatas;
	}
	
	public File fetchProjectPath() {
		return m_projectPath;
	}
	
	public File fetchSourcePath() {
		return m_sourcePath;
	}
	
	public String listFiles() {
//		File[] files = m_sourcePath.listFiles();
//		JSONArray json = new JSONArray();
		try {
//			for (File f : files) {
//				if (!f.getName().endsWith(".tmp")) {
//					JSONObject jsono = new JSONObject();
//					jsono.put("name", f.getName());
//					jsono.put("size", f.length());
//					jsono.put("time", f.lastModified());
//					json.put(jsono);
//				}
//			}
//			return json.toString();
			ObjectMapper mapper= new ObjectMapper();
			return mapper.writeValueAsString(fileDatas);
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String fetchModuleInfo() {
		if(module == null) {
			return "{}";
		}
		return JSON.toJSONString(module);
	}
	
	
}