/**
 * Copyright(c) 2010 XTWSoft, Inc.
 *
 * @author NieLei E-mail:niles2010@live.cn
 * @version create time£º2010-8-3 ÏÂÎç08:55:56
 */
package com.xtwsoft.mapserver.template;

import com.xtwsoft.mapserver.commons.xml.XmlItem;
import com.xtwsoft.mapserver.commons.xml.XmlProperty;

public class TGroup extends XmlItem {
	protected XmlProperty name = new XmlProperty("name");
	public TGroup() {
		this.m_itemName = "group";
		m_xmlProps = new XmlProperty[1];
		m_xmlProps[0] = name;
	}

	public XmlItem createSubItem(String name) {
		if ("item".equals(name)) {
			return new TItem();
		}
		return null;
	}

	public String getName() {
		return name.getData();
	}

	public void setName(String value) {
		name.setValue(value);
	}

	public void afterSetProperty() {
	}
	
    public TItem findItem(String[] nodeNameArray,int index) {
    	for(int i=0;i<this.m_subList.size();i++) {
    		TItem item = (TItem)m_subList.get(i);
    		if(item.getName().equals(nodeNameArray[index])) {
    			if(index == nodeNameArray.length - 1) {
    				return item;
    			}
    			item.findItem(nodeNameArray, index + 1);
    		}
    	}
    	return null;
    }
	
}
