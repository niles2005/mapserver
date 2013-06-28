package com.xtwsoft.mapserver.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xtwsoft.mapserver.template.TConfig;
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
		String module = request.getParameter("module");
		if(module == null) {
			return WebUtil.error("unknown module!");
		}
		if("template".equals(module)) {
			return TemplateManager.getInstance().doModuleWork(request,response);
		} else {
			
		}
		return null;
	}
	
	
}
