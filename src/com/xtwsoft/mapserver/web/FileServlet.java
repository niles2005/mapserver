package com.xtwsoft.mapserver.web;

import javax.servlet.annotation.WebServlet;

import com.xtwsoft.mapserver.project.Project;
import com.xtwsoft.mapserver.project.ProjectManager;

@WebServlet("/file")
public class FileServlet extends BaseServlet {

	@Override
	protected String doPostWork(Params params) {
		String projectName = params.getParameter("project");
		if(projectName == null) {
			return WebUtil.error("unknown project!");
		}
		Project project = ProjectManager.getInstance().getProejct(projectName);
		if(project == null) {
			return WebUtil.error("can not find project:" + projectName);
		} 
		
		String action = params.getParameter("action");
		if(action == null) {
			return WebUtil.error("unknown action!");
		}
		if("list".equals(action)) {
			return project.listFiles();
		} else if("upload".equals(action)) {
			return new FileUploader().doFileUpLoad(params, project);
		} else if("md5sum".equals(action)){
			System.out.println(project.fetchSourcePath().toString()+"/"+params.getValue("name"));
			System.out.println(MD5sum.getHash(project.fetchSourcePath().toString()+"/"+params.getValue("name"), "MD5"));
			return MD5sum.getHash(project.fetchSourcePath().toString()+"/"+params.getValue("name"), "MD5");
			
		}
		return null;
	}


}
