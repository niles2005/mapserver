package com.xtwsoft.mapserver.web;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.xtwsoft.mapserver.global.Global;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xtwsoft.mapserver.file.FileData;
import com.xtwsoft.mapserver.file.FileDataManager;
import com.xtwsoft.mapserver.file.FileDataOfProject;
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
			String md5sum = MD5sum.getHash(project.fetchSourcePath().toString()+"/"+fileName, "MD5");
			FileDataOfProject projectFileData = FileDataManager.getInstance().getFileDataManagerForProject(projectName);
			
			Object fileDataObject = projectFileData.getFileData(fileName);
			if(fileDataObject instanceof JSONObject){
				JSONObject fileDataJSON = (JSONObject)fileDataObject;
				FileData fileData = (FileData)JSONObject.toJavaObject(fileDataJSON, FileData.class); 
				fileData.setMd5Sum(md5sum);
				projectFileData.addFileData(fileData);
			}else{
				FileData fileData = (FileData)fileDataObject;
				fileData.setMd5Sum(md5sum);
				projectFileData.addFileData(fileData);
			}
			
			projectFileData.writeFileDatasJson(new File(project.fetchProjectPath()+"/props","props.json"));
			return md5sum;

			
		}
		return null;
	}


}
