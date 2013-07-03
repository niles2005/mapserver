/**
 * Copyright(c) 2010 XTWSoft, Inc.
 *
 * @author NieLei E-mail:niles2010@live.cn
 * @version create time��2010-8-3 ����08:55:56
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
    	String[] strs = nodeName.split("\\.");
    	TItem item = findItem(strs);
    	if(item != null) {
    		if(item.removeFromParent()) {
    			return WebUtil.oKJSON();
    		}
    	}
    	return WebUtil.failedJSON("delete node:" + nodeName + " failed!");
    }
    
    public String addNode(String nodeName) {
    	if(nodeName == null) {
    		return WebUtil.error("node name is null!"); 
    	}
    	String[] strs = nodeName.split("\\.");
    	TItem item = findItem(strs);
    	if(item != null) {
    		TItem newItem = new TItem();
    		newItem.setName(nodeName);
    		item.addSubItem(newItem);
    		return WebUtil.oKJSON();
    	}
    	return WebUtil.failedJSON("add node:" + nodeName + " failed!");
    }
    
    public String renameNode(String nodeName) {
    	if(nodeName == null) {
    		return WebUtil.error("node name is null!"); 
    	}
    	int pos = nodeName.lastIndexOf("^");
    	String newName = null;
    	if(pos != -1) {
    		newName = nodeName.substring(pos + 1);
    		newName = newName.trim();
    		if(newName.length() == 0) {
    			return WebUtil.failedJSON("rename node:" + nodeName + " failed,new name is empty!");
    		}
    		if(newName.indexOf(".") != -1) {
    			return WebUtil.failedJSON("rename node:" + nodeName + " failed,new name has point!");
    		}
    		
    		nodeName = nodeName.substring(0,pos);
    	} else {
    		return WebUtil.failedJSON("rename node:" + nodeName + " failed,new name is not exist!");
    	}
    	
    	String[] strs = nodeName.split("\\.");
    	TItem item = findItem(strs);
    	if(item != null) {
    		item.setName(newName);
    		return WebUtil.oKJSON();
    	}
    	return WebUtil.failedJSON("rename node:" + nodeName + " failed!");
    }
    
    public String moveNode(String nodeName) {
    	if(nodeName == null) {
    		return WebUtil.error("node name is null!"); 
    	}
    	int pos = nodeName.lastIndexOf("^");
    	if(pos != -1) {
    		String toPath = nodeName.substring(pos + 1);
    		String fromPath = nodeName.substring(0,pos);
    		
    		String[] strsTo = toPath.split("\\.");
        	TItem toItem = findItem(strsTo);
        	if(toItem == null) {
        		return WebUtil.failedJSON("move node failed,dest node:" + toPath + " is not exist!");
        	}
        	
    		String[] strsFrom = fromPath.split("\\.");
        	TItem fromItem = findItem(strsFrom);
        	if(fromItem == null) {
        		return WebUtil.failedJSON("move node failed,source node:" + fromPath + " is not exist!");
        	}
        	fromItem.removeFromParent();
        	toItem.addSubItem(fromItem);
        	return WebUtil.oKJSON();
    	}
    	
     	return WebUtil.failedJSON("move node:" + nodeName + " failed!");
    }
    
    public static void main(String[] args) {
    	File tempFile = new File("new2.xml");
    	TConfig config = new TConfig(tempFile);
    	config.save(new File("new3.xml"));
    }


}
