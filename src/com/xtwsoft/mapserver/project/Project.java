package com.xtwsoft.mapserver.project;

import java.io.File;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import com.xtwsoft.mapserver.file.FileDataManager;
import com.xtwsoft.mapserver.file.FileDataOfProject;

public class Project {

	private Long id;
	private String name;
	private String vendor;
	private String imageUrl;
	private Date createTime;
	private String creator;
	private String info;

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
	
	public void setCreateTime(Date date) {
		this.createTime = date;
	}
	
	public Date getCreateTime() {
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
		}
	}
	
	public File getProjectPath() {
		return m_projectPath;
	}
	
	public File getSourcePath() {
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
			
			System.out.println(mapper.writeValueAsString(FileDataManager.getInstance().getFileDataManagerForProject(name).fetchFileDatas()));
			return mapper.writeValueAsString(FileDataManager.getInstance().getFileDataManagerForProject(name).fetchFileDatas());
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}