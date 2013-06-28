package com.xtwsoft.mapserver.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xtwsoft.mapserver.template.TemplateManager;


public class WorkManager {
	private static WorkManager m_instance = null;
	private WorkManager() {
	}

	public static WorkManager getInstance() {
		if(m_instance == null) {
			m_instance = new WorkManager(); 
		}
		return m_instance;
	}
	
	public String doWork(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if(action == null) {
			return WebUtil.error("unknown action!");
		}
		if(action.equals("template.list")) {
			return TemplateManager.getInstance().listTemplatesJSON();
		} else if(action.startsWith("template.file.")) {
			response.setContentType("application/xml");
			String fileName = action.substring(14);
			return TemplateManager.getInstance().getTemplateContent(fileName);
		}
		
		return null;
	}
	
}
