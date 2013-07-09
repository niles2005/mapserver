package com.xtwsoft.mapserver.project;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.xtwsoft.mapserver.web.ServerConfig;
import com.xtwsoft.mapserver.web.WebUtil;

public class ProjectManager {
	private ObjectMapper m_mapper = new ObjectMapper();
	private static ProjectManager m_instance = null;
	private Projects m_projects = null;
	private ProjectManager(File file) {
		try {
			m_projects = m_mapper.readValue(file, Projects.class);
			if(m_projects == null) {
				m_projects = new Projects();
			}
			System.err.println("projects number:" + this.m_projects.getProjects().size());
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void initInstance() {
		if(m_instance == null) {
			File configPath = ServerConfig.getInstance().getConfigPath();
			if(configPath != null) {
				File projectsFile = new File(configPath,"projects.json");
				if(projectsFile.exists()) {
					m_instance = new ProjectManager(projectsFile);
				}
			}
		}
	}
	
	public Project getProejct(String projectName) {
		return m_projects.getProejct(projectName);
	}
	
	public static ProjectManager getInstance() {
		return m_instance;
	}
	
	public String listProjectJSON() {
//		String jsonString = JSON.toJSONString(m_projects.getProjects());
//		return jsonString;
		try {
			String jsonString = m_mapper.writeValueAsString(m_projects.getProjects());
			return jsonString;
		} catch(Exception ex) {
			ex.printStackTrace();
			return WebUtil.exception(ex);
		}
	}
	
//	public String doModuleWork(HttpServletRequest request, HttpServletResponse response) {
//		String action = request.getParameter("action");
//		if(action == null) {
//			return WebUtil.error("unknown action!");
//		}
//		if("list".equals(action)) {
//			return listProjectJSON();
//		} 
//
//		return null;
//	}
}
