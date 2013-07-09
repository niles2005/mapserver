package com.xtwsoft.mapserver.web;

import javax.servlet.annotation.WebServlet;

import com.xtwsoft.mapserver.project.ProjectManager;

@WebServlet("/project")
public class ProjectServlet extends BaseServlet {
	protected String doPostWork(Params params) {
		String action = params.getValue("action");
		if(action == null) {
			return WebUtil.error("unknown action!");
		}
		if("list".equals(action)) {
			return ProjectManager.getInstance().listProjectJSON();
		} 

		return null;
	}
}
