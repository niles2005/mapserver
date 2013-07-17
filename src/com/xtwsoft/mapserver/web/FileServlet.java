package com.xtwsoft.mapserver.web;

import javax.servlet.annotation.WebServlet;

import com.xtwsoft.mapserver.file.FileDatas;
import com.xtwsoft.mapserver.global.Global;
import com.xtwsoft.mapserver.project.Project;

@WebServlet("/file")
public class FileServlet extends BaseServlet {

	@Override
	protected String doPostWork(Params params) {
		String projectName = params.getParameter("project");
		if(projectName == null) {
			return WebUtil.error("unknown project!");
		}
		Project project = Global.getInstance().getProject(projectName);
		System.out.println("project name fromm params is "+projectName);
		if(project == null) {
			return WebUtil.error("can not find project:" + projectName);
		} 
		
		String action = params.getParameter("action");
		if(action == null) {
			return WebUtil.error("unknown action!");
		}
		
		
		
		FileDatas fileDatas = project.fetchFileDatas();
		if("list".equals(action)) {
			return fileDatas.listFiles();
		} else if("upload".equals(action)) {
			return fileDatas.upLoadFile(params);
		} else if("md5sum".equals(action)){
			String fileName = params.getValue("name");
			return fileDatas.buildFileMD5(fileName);
		} else if("propsupdate".equals(action)){
			if (fileDatas.propsFileUpdate()) {
				return fileDatas.listFiles();
			}
		}
		return null;
	}


}
