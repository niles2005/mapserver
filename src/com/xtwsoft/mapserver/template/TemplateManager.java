package com.xtwsoft.mapserver.template;

import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xtwsoft.mapserver.web.ServerConfig;
import com.xtwsoft.mapserver.web.WebUtil;

public class TemplateManager {
	private static TemplateManager m_instance = null;
	private File m_tempPath = null;
	private Hashtable<String,TConfig> m_confitHash = new Hashtable<String,TConfig>();
	
	private TemplateManager(File tempsPath) {
		m_tempPath = tempsPath;
		if(m_tempPath.exists()) {
			workTempPath(m_tempPath);
		}
	}
	
	public static TemplateManager getInstance() {
		return m_instance;
	}

	public static void initInstance() {
		if(m_instance == null) {
			File datasPath = ServerConfig.getInstance().getDatasPath();
			if(datasPath != null) {
				File tempsPath = new File(datasPath,"templates");
				m_instance = new TemplateManager(tempsPath);
			}
		}
	}
	
	private void workTempPath(File path) {
		File[] files = path.listFiles();
		if(files != null) {
			for(int i=0;i<files.length;i++) {
				File theFile = files[i];
				workTempFile(theFile);
			}
		}
	}
	
	private void workTempFile(File file) {
		String fileName = file.getName();
		if(!fileName.toLowerCase().endsWith(".xml")) {
			return;
		}
		try {
			TConfig tempConfig = new TConfig(file);
			m_confitHash.put(fileName, tempConfig);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String listTemplatesJSON() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("[");
		Iterator iters = m_confitHash.values().iterator();
		while(iters.hasNext()) {
			TConfig tempConfig = (TConfig)iters.next();
			if(strBuff.length() > 1) {
				strBuff.append(",");
			}
			strBuff.append(tempConfig.getTemplateInfoJSON());
		}
		strBuff.append("]");
		
		return strBuff.toString();
	}

	public String getTemplateContent(String name) {
		TConfig tempConfig = m_confitHash.get(name);
		if(tempConfig != null) {
			return tempConfig.getXMLContent();
		}
		return "{\"error\":\"Can not find file:" + name + "\"}";
	}
	
	public TConfig getTemplateConfig(String name) {
		return m_confitHash.get(name);
	}
	
	public String doModuleWork(HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if(action == null) {
			return WebUtil.error("unknown action!");
		}
		if("list".equals(action)) {
			return listTemplatesJSON();
		} 
		String name = request.getParameter("name");
		if(name == null) {
			return WebUtil.error("unknown file name!");
		}
		TConfig tempConfig = getTemplateConfig(name);
		if(tempConfig == null) {
			return WebUtil.error("can not find template:" + name + "!");
		}
		if("content".equals(action)) {
			response.setContentType("application/xml");
			return tempConfig.getXMLContent();
		} else if("delete".equals(action)) {//delete tree node
			String nodeName = request.getParameter("node");
			return tempConfig.deleteNode(nodeName);
		} else if("add".equals(action)) {//add tree node
			String nodeName = request.getParameter("node");
			return tempConfig.addNode(nodeName);
		} else if("rename".equals(action)) {//rename tree node name
			String nodeName = request.getParameter("node");
			return tempConfig.renameNode(nodeName);
		}
		return null;
	}
	
}
