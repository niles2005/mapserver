package com.xtwsoft.mapserver.web;

import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xtwsoft.mapserver.global.Global;
import com.xtwsoft.mapserver.project.Module;
import com.xtwsoft.mapserver.project.Project;
import com.xtwsoft.mapserver.project.Projects;

@WebServlet("/project")
public class ProjectServlet extends BaseServlet {
	protected String doPostWork(Params params) {
		String action = params.getValue("action");
		if(action == null) {
			return WebUtil.error("unknown action!");
		}
		if("list".equals(action)) {
			Projects projects = Global.getInstance().getProjects();
			return projects.listProjectJSON();
		} 

		String projectName = params.getValue("project");
		if(projectName == null) {
			return WebUtil.error("unknown project!");
		}
		Project project = Global.getInstance().getProject(projectName);
		if(project == null) {
			return WebUtil.error("can not find project:" + projectName);
		} 
		
		if("module".equals(action)) {
			return project.fetchModuleInfo();
		} else if("moduleUpdate".equals(action)) {
			JsonObject jsonObject = params.getJsonObject("module");
			Gson gson = new Gson();
			Module module = gson.fromJson(jsonObject, Module.class);
			if(module != null) {
				project.setModule(module);
			}
		}
		
		return null;
	}
}
