package com.xtwsoft.mapserver.template;

import java.io.File;

public class Template {
	private File m_file = null;
	private String m_name = null;
	private TConfig m_tempConfig = null;
	
	public Template(File file) {
		m_file = file;
		m_name = file.getName();
		m_tempConfig = new TConfig(file);
	}

	public String getName() {
		return m_name;
	}

	public void setName(String name) {
		this.m_name = name;
	}

	public TConfig getTemplateConfig(String name) {
		return m_tempConfig;
	}
	
	public String getTemplateContent() {
		if(m_tempConfig != null) {
			return m_tempConfig.getXMLContent();
		}
		return "{\"error\":\"Can not find file:" + m_name + "\"}";
	}
	
	public String getXMLContent() {
		return m_tempConfig.getXMLContent();
	}
	
    public String deleteNode(String nodeName) {
    	return m_tempConfig.deleteNode(nodeName);
    }
    
    public String addNode(String nodeName) {
    	return m_tempConfig.addNode(nodeName);
    }
    
    public String renameNode(String nodeName) {
    	return m_tempConfig.renameNode(nodeName);
    }
    
    public String moveNode(String nodeName) {
    	return m_tempConfig.moveNode(nodeName);
    }	
}
