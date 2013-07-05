package com.xtwsoft.mapserver.project;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class Projects {
    private List<Project> projects = new ArrayList<Project>();
    private Hashtable<String,Project> projectHash = new Hashtable<String,Project>(); 
    
    private Date updateTime = null;
    public Date getUpdateTime() { return updateTime; }
    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
 
    public List<Project> getProjects() { return projects; }
    public void setProjects(List<Project> projects) { this.projects = projects; }

    public void addProject(Project project) {
    	projects.add(project);
    	projectHash.put(project.getName(), project);
    }
    
    public Project getProejct(String name) {
    	return projectHash.get(name);
    }
    
    //must call after parse projects json
    public void storeProjectHash() {
    	for(int i=0;i<projects.size();i++) {
    		Project project = projects.get(i);
    		projectHash.put(project.getName(), project);
    	}
    }
}
