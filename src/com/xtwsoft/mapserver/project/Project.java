package com.xtwsoft.mapserver.project;

import java.io.File;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

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
	public void doInit(File projectsPath) {
		if(m_projectPath == null || !m_projectPath.exists()) {
			m_projectPath = new File(projectsPath,this.name);
			m_projectPath.mkdir();
		}
	}
	
	public File getFilePath() {
		return m_projectPath;
	}
	
	public String listFiles() {
		File[] files = m_projectPath.listFiles();
		JSONArray json = new JSONArray();
		try {
			for (File f : files) {
				if (!f.getName().endsWith(".tmp")) {
					JSONObject jsono = new JSONObject();
					jsono.put("name", f.getName());
					jsono.put("size", f.length());
					jsono.put("time", f.lastModified());
					json.put(jsono);
				}
			}
			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}