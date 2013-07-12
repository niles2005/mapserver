package com.xtwsoft.mapserver.web;

import javax.servlet.annotation.WebServlet;

import com.xtwsoft.mapserver.global.Global;
import com.xtwsoft.mapserver.template.Template;
import com.xtwsoft.mapserver.template.Templates;

@WebServlet("/template")
public class TemplateServlet extends BaseServlet {

	@Override
	protected String doPostWork(Params params) {
		String action = params.getValue("action");
		if(action == null) {
			return WebUtil.error("unknown action!");
		}
		if("list".equals(action)) {
			Templates templates = Global.getInstance().getTemplates();
			return templates.listTemplatesJSON();
		} 
		String name = params.getValue("name");
		if(name == null) {
			return WebUtil.error("unknown file name!");
		}
		Template template = Global.getInstance().getTemplate(name);
		if(template == null) {
			return WebUtil.error("can not find template:" + name + "!");
		}
		if("content".equals(action)) {
			params.setContentType("application/xml");
			return template.fetchXMLContent();
		} else if("delete".equals(action)) {//delete tree node
			String nodeName = params.getValue("node");
			return template.deleteNode(nodeName);
		} else if("add".equals(action)) {//add tree node
			String nodeName = params.getValue("node");
			return template.addNode(nodeName);
		} else if("rename".equals(action)) {//rename tree node name
			String nodeName = params.getValue("node");
			return template.renameNode(nodeName);
		} else if("move".equals(action)) {//move tree node name
			String nodeName = params.getValue("node");
			return template.moveNode(nodeName);
		}
		return null;
	}


}
