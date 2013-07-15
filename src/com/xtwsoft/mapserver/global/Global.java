package com.xtwsoft.mapserver.global;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.google.gson.Gson;
import com.xtwsoft.mapserver.project.Project;
import com.xtwsoft.mapserver.project.Projects;
import com.xtwsoft.mapserver.template.Template;
import com.xtwsoft.mapserver.template.Templates;
import com.xtwsoft.mapserver.web.ServerConfig;

public class Global {
	private static Global m_instance= null;
	
	public static Global getInstance() {
		if(m_instance == null) {
			m_instance = new Global();
		}
		return m_instance;
	}
	
	private Global() {
		initGlobal();
	}

	private Projects m_projects = null;
	
	private Templates m_templates = null;
	
	private boolean m_init = false;
	private void initGlobal() {
		try {
			if(m_init) {
				return;
			}
			m_init = true;
			Gson gson = new Gson();

			//init projects
			File projectsFile = ServerConfig.getInstance().getProjectsFile();
			String projectsLine = readJSONFileContent(projectsFile);
			if(projectsLine != null) {
				System.out.println("get projects from projects.json");
				m_projects = gson.fromJson(projectsLine, Projects.class);
				m_projects.doInit();
			}
			if(m_projects == null) {
				System.out.println("get projects from projects path files");
				m_projects = new Projects();
				m_projects.doInit();
			}

			//init templates
			File templatesPath = ServerConfig.getInstance().getTemplatesPath();
			m_templates = new Templates(templatesPath);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	
	public String readJSONFileContent(File file) {
		try {
			if(file == null) {
				return null;
			}
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			String content = "";
			while(line != null) {
				content += line;
				line = reader.readLine();
			}
			reader.close();
			return content;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Projects getProjects() {
		return m_projects;
	}

	public Project getProject(String projectName) {
		return m_projects.getProejct(projectName);
	}

	public Templates getTemplates() {
		return m_templates;
	}

	public Template getTemplate(String templateName) {
		return m_templates.getTemplate(templateName);
	}

	
}
