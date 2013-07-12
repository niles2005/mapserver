package com.xtwsoft.mapserver.template;

import java.io.File;
import java.util.Hashtable;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Templates {
	public Templates(File path) {
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
			Template template = new Template(file);
			addTemplate(template);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	

    private Map<String,Template> templates = new Hashtable<String,Template>(); 

	public Map<String,Template> getTemplates() {
		return templates;
	}

	public void setTemplates(Map<String,Template> templates) {
		this.templates = templates;
	}

    public void addTemplate(Template template) {
    	if(template != null) {
    		templates.put(template.getName(), template);
    	}
    }
    
    public Template getTemplate(String name) {
    	return templates.get(name);
    }

	public String listTemplatesJSON() {
		return JSON.toJSONString(templates);
	}

}
