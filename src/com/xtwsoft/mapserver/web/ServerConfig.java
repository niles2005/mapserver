package com.xtwsoft.mapserver.web;

import java.io.File;

public class ServerConfig {
	private static ServerConfig m_instance = null;
	private File m_appPath = null;
	private ServerConfig(File appPath) {
		m_appPath = appPath;
	}

	public static ServerConfig getInstance() {
		return m_instance;
	}
	
	public static void initInstance(String path) {
		if(m_instance != null) {
			return;
		}
		
		m_instance = new ServerConfig(new File(path)); 
	}
	
}
