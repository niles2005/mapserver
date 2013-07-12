package com.xtwsoft.mapserver.project;

import java.io.File;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.xtwsoft.mapserver.web.ServerConfig;
import com.xtwsoft.mapserver.web.WebUtil;

public class Projects {
    private Map<String,Project> projects = new Hashtable<String,Project>(); 
    
    private Long updateTime = null;
    public Long getUpdateTime() { return updateTime; }
    public void setUpdateTime(Long updateTime) { this.updateTime = updateTime; }
 
    public Map<String,Project> getProjects() { return projects; }
    public void setProjects(Map<String,Project> projects) { this.projects = projects; }

    public void addProject(Project project) {
    	projects.put(project.getName(), project);
    }
    
    public Project getProejct(String name) {
    	return projects.get(name);
    }
    
    public void doInit() {
    	File projectsPath = ServerConfig.getInstance().getProjectsPath();
    	Iterator iters = projects.values().iterator();
    	while(iters.hasNext()) {
    		Project project = (Project)iters.next();
    		project.doInit(projectsPath);
    	}
    }
    
	public String listProjectJSON() {
		return JSON.toJSONString(projects);
	}
    
}
