/**
 * Copyright(c) 2010 XTWSoft, Inc.
 *
 * @author NieLei E-mail:niles2010@live.cn
 * @version create time£º2010-8-3 ÏÂÎç08:55:56
 */
package com.xtwsoft.mapserver.template;

import java.io.File;

import com.xtwsoft.mapserver.commons.xml.XmlItem;
import com.xtwsoft.mapserver.commons.xml.XmlProperty;
import com.xtwsoft.mapserver.commons.xml.XmlRoot;
import com.xtwsoft.mapserver.web.WebUtil;

public class TConfig extends XmlRoot {
	private boolean m_isValid = false;
	private File m_file = null;
	protected XmlProperty id = new XmlProperty("id");
	protected String[] m_fields = null;
	
    public TConfig(File file) {
    	m_file = file;
        this.m_itemName = "config";
        m_xmlProps = new XmlProperty[1];
		m_xmlProps[0] = id;
        try {
            this.load(file);
        	this.id.setValue("TC_" + this.hashCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public String getFileName() {
    	return m_file.getName();
    }
    
    public String getId() {
    	return this.id.getData();
    }
    
    public boolean isValid() {
    	return m_isValid;
    }
    
	public XmlItem createSubItem(String name) {
		if ("group".equals(name)) {
			return new TGroup();
		}
		return null;
	}

	public void afterSetProperty() {
	}

    public void loadedNode() {
		m_isValid = true;
    }
    
    public TItem findItem(String[] nodeNameArray) {
    	for(int i=0;i<this.m_subList.size();i++) {
    		TGroup group = (TGroup)m_subList.get(i);
    		if(group.getName().equals(nodeNameArray[0])) {
    			return group.findItem(nodeNameArray,1);
    		}
    	}
    	return null;
    }
    
    public String getTemplateInfoJSON() {
    	return "{\"name\":\"" + this.getFileName() + "\",\"id\":\"" + this.getId() + "\"}";
    }
    
    public String deleteNode(String nodeName) {
    	if(nodeName == null) {
    		return WebUtil.error("node name is null!"); 
    	}
//    	String[] strs = nodeName.split(".");
//    	TItem item = findItem(strs);
//    	if(item != null) {
//    		
//    	}
    	String status = "ok";
    	String message = "";
    	String ss = "{\"status\":\"" + status + "\"";
    	if(message.length() != 0) {
    		ss += ",\"message\":\"" + message + "\"}";
    	} else {
    		ss += "}";
    	}
    	return ss;
    }
    
    public String addNode(String nodeName) {
    	if(nodeName == null) {
    		return WebUtil.error("node name is null!"); 
    	}
    	String[] strs = nodeName.split(".");
    	TItem item = findItem(strs);
    	if(item != null) {
    		
    	}
    	String status = "ok";
    	String message = "";
    	String ss = "{\"status\":\"" + status + "\"";
    	if(message.length() != 0) {
    		ss += ",\"message\":\"" + message + "\"}";
    	} else {
    		ss += "}";
    	}
    	return ss;
    }
    
    public String renameNode(String nodeName) {
    	if(nodeName == null) {
    		return WebUtil.error("node name is null!"); 
    	}
    	String[] strs = nodeName.split(".");
    	TItem item = findItem(strs);
    	if(item != null) {
    		
    	}
    	String status = "ok";
    	String message = "";
    	String ss = "{\"status\":\"" + status + "\"";
    	if(message.length() != 0) {
    		ss += ",\"message\":\"" + message + "\"}";
    	} else {
    		ss += "}";
    	}
    	return ss;
    }
    
    public static void main(String[] args) {
    	File tempFile = new File("new2.xml");
    	TConfig config = new TConfig(tempFile);
    	config.save(new File("new3.xml"));
    }


}
