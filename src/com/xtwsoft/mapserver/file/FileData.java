package com.xtwsoft.mapserver.file;

public class FileData {
	
	private String name;
	private long size;
	private long createTime;
	private String md5Sum;
	
	public FileData(){
		
	}
	public FileData(String name, long size) {
		this.name = name;
		this.size = size;
		setCreateTime(System.currentTimeMillis());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	public String getMd5Sum() {
		return md5Sum;
	}
	public void setMd5Sum(String md5Sum) {
		this.md5Sum = md5Sum;
	}
	
	


}
