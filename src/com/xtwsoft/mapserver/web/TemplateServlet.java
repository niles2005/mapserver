package com.xtwsoft.mapserver.web;

import javax.servlet.annotation.WebServlet;

import com.xtwsoft.mapserver.template.TConfig;
import com.xtwsoft.mapserver.template.TemplateManager;

@WebServlet("/template")
public class TemplateServlet extends BaseServlet {

	@Override
	protected String doPostWork(Params params) {
		String action = params.getValue("action");
		if(action == null) {
			return WebUtil.error("unknown action!");
		}
		if("list".equals(action)) {
			return TemplateManager.getInstance().listTemplatesJSON();
		} 
		String name = params.getValue("name");
		if(name == null) {
			return WebUtil.error("unknown file name!");
		}
		TConfig tempConfig = TemplateManager.getInstance().getTemplateConfig(name);
		if(tempConfig == null) {
			return WebUtil.error("can not find template:" + name + "!");
		}
		if("content".equals(action)) {
			params.setContentType("application/xml");
			return tempConfig.getXMLContent();
		} else if("delete".equals(action)) {//delete tree node
			String nodeName = params.getValue("node");
			return tempConfig.deleteNode(nodeName);
		} else if("add".equals(action)) {//add tree node
			String nodeName = params.getValue("node");
			return tempConfig.addNode(nodeName);
		} else if("rename".equals(action)) {//rename tree node name
			String nodeName = params.getValue("node");
			return tempConfig.renameNode(nodeName);
		} else if("move".equals(action)) {//move tree node name
			String nodeName = params.getValue("node");
			return tempConfig.moveNode(nodeName);
		}
		return null;
	}


}
