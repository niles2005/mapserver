package com.xtwsoft.mapserver.project;

import java.io.File;

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
//	private File m_sourcePath = null;
//	private File m_props = null;
	public void doInit(File projectsPath) {
		if(m_projectPath == null || !m_projectPath.exists()) {
			m_projectPath = new File(projectsPath,this.name);
			m_projectPath.mkdir();
			
		}
		
		if(m_projectPath != null && m_projectPath.exists()) {
			fileDatas = FileDatas.buildFileDatas(this);
<<<<<<< HEAD
//			m_sourcePath = new File(m_projectPath,"source");
//			if(!m_sourcePath.exists()) {
//				m_sourcePath.mkdir();
//			}
//			File propsPath = new File(m_projectPath,"props");
//			if(!propsPath.exists()){
//				propsPath.mkdir();
//			}
//			m_props = new File(propsPath, "props.json");
//			if (!m_props.exists()) {
//				fileDatas = new FileDatas(this);
//				fileDatas.writeFileDatasJson(m_props);
//			}else{
//				try {
//					fileDatas = fileData_mapper.readValue(m_props, FileDatas.class);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
			
=======
		
>>>>>>> c881596ce276b64676346ea6d11fb1ae10485a5a
		}
	}
	
	public FileDatas fetchFileDatas(){
		return fileDatas;
	}
	
	public File fetchProjectPath() {
		return m_projectPath;
	}
	
	
	public String fetchModuleInfo() {
		if(module == null) {
			return "{}";
		}
		return JSON.toJSONString(module);
	}
	
	
}