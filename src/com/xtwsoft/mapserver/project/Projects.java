package com.xtwsoft.mapserver.project;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

public class Projects {
    private Map<String,Project> projects = new Hashtable<String,Project>(); 
    
    private Date updateTime = null;
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
 
    public Map<String,Project> getProjects() { return projects; }
    public void setProjects(Map<String,Project> projects) { this.projects = projects; }

    public void addProject(Project project) {
    	projects.put(project.getName(), project);
    }
    
    public Project getProejct(String name) {
    	return projects.get(name);
    }
}
