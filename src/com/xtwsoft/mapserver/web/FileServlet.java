package com.xtwsoft.mapserver.web;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.xtwsoft.mapserver.file.FileDataManager;
import com.xtwsoft.mapserver.file.FileDataOfProject;
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
			String fileName = params.getValue("name");
			String md5sum = MD5sum.getHash(project.getSourcePath().toString()+"/"+fileName, "MD5");
			(FileDataManager.getInstance().getFileDataManagerForProject(projectName).getFileData(fileName)).setMd5Sum(md5sum);
			FileDataManager.getInstance().getFileDataManagerForProject(projectName).writeFileDatasJson(new File(project.getProjectPath()+"/props","props.json"));
			return md5sum;
			
		}
		return null;
	}


}
