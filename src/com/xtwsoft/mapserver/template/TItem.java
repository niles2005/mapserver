/**
 * Copyright(c) 2010 XTWSoft, Inc.
 *
 * @author NieLei E-mail:niles2010@live.cn
 * @version create time£º2010-8-3 ÏÂÎç08:55:56
 */
package com.xtwsoft.mapserver.template;

import java.util.Hashtable;

import com.xtwsoft.mapserver.commons.xml.XmlItem;
import com.xtwsoft.mapserver.commons.xml.XmlProperty;

public class TItem extends XmlItem {
	private XmlProperty name = new XmlProperty("name");
	public TItem() {
		this.m_itemName = "item";
		m_xmlProps = new XmlProperty[1];
		m_xmlProps[0] = name;
	}
	
	public TGroup getOwnerGroup() {
		return (TGroup)this.getParentItem();
	}

	public String getName() {
		return name.getData();
	}

	public void setName(String value) {
		name.setValue(value);
	}

	public XmlItem createSubItem(String name) {
		if ("field".equals(name)) {
			return new TField();
		} else if("item".equals(name)) {
			return new TItem();
		}
		return null;
	}
	
	public void storeFields(Hashtable hash) {
		for(int i=0;i<this.m_subList.size();i++) {
			TField field = (TField)m_subList.get(i);
			String value = field.getValue().trim();
			if(value.length() == 0) {
				System.err.println(this.getName() + ":" + field.getName() + "'s field value is null!");
				continue;
			}
			hash.put(field.getName(), field);
		}
	}
	
	public void afterSetProperty() {
	}
	
    public int compareTo(XmlItem item) {
    	TItem other = (TItem)item;
    	return this.getName().compareTo(other.getName());
    }
}
