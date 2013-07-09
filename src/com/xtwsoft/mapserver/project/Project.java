package com.xtwsoft.mapserver.project;

import java.io.File;
import java.util.Date;

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
	
	public File getFilePath() {
		return null;
	}
	
	public String listFiles() {
		return null;
	}
	
}