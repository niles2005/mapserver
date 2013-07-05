package com.xtwsoft.mapserver.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.xtwsoft.mapserver.template.TConfig;
import com.xtwsoft.mapserver.web.ServerConfig;
import com.xtwsoft.mapserver.web.WebUtil;

public class ProjectManager {
	private static ProjectManager m_instance = null;
	private Projects m_projects = null;
	private ProjectManager(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			reader.close();
			
			if(line.length() > 0) {
				System.err.println("parse projects json....");
				m_projects = JSON.parseObject(line, Projects.class);
				m_projects.storeProjectHash();
			}
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
		String jsonString = JSON.toJSONString(m_projects.getProjects());
		return jsonString;
	}
	
	public String doModuleWork(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if(action == null) {
			return WebUtil.error("unknown action!");
		}
		if("list".equals(action)) {
			return listProjectJSON();
		} 

		return null;
	}
	
	
	public static void main(String[] args) {

		Projects projects = new Projects();
		projects.setUpdateTime(new Date());
		
		Project proj1 = new Project();
		
		proj1.setId(1L);
		proj1.setName("shanghai");
		proj1.setVendor("OSM");
		proj1.setImageUrl("img/osm.png");
		proj1.setCreateTime(new Date());
		proj1.setCreator("niles");
		proj1.setInfo("Test map project for shanghai");
		projects.addProject(proj1);
		 
		Project proj2 = new Project();
		
		proj2.setName("test");
		proj2.setVendor("OSM");
		proj2.setImageUrl("img/osm.png");
		proj2.setCreateTime(new Date());
		proj2.setCreator("niles");
		proj2.setInfo("Test map project");
		projects.addProject(proj2);
		 
		Project proj3 = new Project();
		
		proj3.setName("njcc");
		proj3.setVendor("OSM");
		proj3.setImageUrl("img/osm.png");
		proj3.setCreateTime(new Date());
		proj3.setCreator("niles");
		proj3.setInfo("map project for njcc");
		projects.addProject(proj3);
		 
		String jsonString = JSON.toJSONString(projects);
		 
		System.out.println(jsonString);
		
		Projects projs = JSON.parseObject(jsonString,Projects.class);
		System.err.println(projs.getUpdateTime());
		
//		File file = new File("D:\\mywork\\mapserver\\workspace\\mapserver\\WebContent\\WEB-INF\\config\\projects.json");
//		ProjectManager.initInstance(file);
//		
//		Projects projects = ProjectManager.getInstance().m_projects;
//		System.err.println(projects.getUpdateTime());
	}

}
