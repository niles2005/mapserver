/**
 * Copyright(c) 2010 XTWSoft, Inc.
 *
 */
package com.xtwsoft.mapserver.commons.xml;

import java.util.ArrayList;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class XmlItem implements Comparable<XmlItem> {
    protected ArrayList m_subList = new ArrayList();

    protected XmlProperty[] m_xmlProps = null;

    protected String m_itemName = null;

    protected XmlItem m_parentItem = null;

    public XmlItem() {
    }
    
    public String getItemName() {
    	return m_itemName;
    }

    public XmlProperty[] getXmlProperties() {
        return m_xmlProps;
    }

    public XmlItem getParentItem() {
        return m_parentItem;
    }

    public void setNode(Node node) {
        NamedNodeMap nodeMap = node.getAttributes();
        if (m_xmlProps != null) {
            for (int i = 0; i < nodeMap.getLength(); i++) {
                Node theNode = nodeMap.item(i);
                setProperty(theNode.getNodeName(), theNode.getNodeValue());
            }
        }
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node subNode = nodeList.item(i);
            if (subNode.getNodeType() == Node.ELEMENT_NODE) {
                String nodeName = subNode.getNodeName();

                XmlItem subItem = createSubItem(nodeName);
                if (subItem != null) {
                    subItem.setNode(subNode);
                    addSubItem(subItem);
                }
            }
        }

        afterSetProperty();
    }

    public void setProperty(String name, String value) {
        XmlProperty prop = this.getProperty(name);
        if (prop != null) {
            if (!prop.setValue(value)) {
                throw new RuntimeException("The property " + name
                                           + " set value " + value + " error!");
            }
        } else {
            throw new RuntimeException("The property " + name
                                       + " is not found!");
        }
    }

    public void afterSetProperty() {
    }

    public String getIndent() {
        return this.getParentItem().getIndent() + "	";
    }

    public void saveItem(StringBuffer strBuff) {
        strBuff.append(getIndent()).append("<").append(m_itemName);
        if (m_xmlProps != null) {
            for (int i = 0; i < m_xmlProps.length; i++) {
                m_xmlProps[i].save(strBuff);
            }
        }
        if (this.m_subList.size() == 0) {
            strBuff.append("/>\n");
        } else {
            strBuff.append(">\n");
        }

        for (int i = 0; i < m_subList.size(); i++) {
            XmlItem subItem = (XmlItem) m_subList.get(i);
            subItem.saveItem(strBuff);
        }

        if (this.m_subList.size() == 0) {
        } else {
            strBuff.append(getIndent()).append("</").append(m_itemName).append(
                    ">\n");
        }
    }

    public XmlProperty getProperty(String name) {
        if (name == null) {
            return null;
        }
        for (int i = 0; i < m_xmlProps.length; i++) {
            if (m_xmlProps[i].isName(name)) {
                return m_xmlProps[i];
            }
        }
        return null;
    }

    public XmlItem createSubItem(String name) {
        return null;
    }

    public void setParentItem(XmlItem parentItem) {
        m_parentItem = parentItem;
    }

    public void addSubItem(XmlItem subItem) {
        subItem.setParentItem(this);
        m_subList.add(subItem);
    }

    public boolean removeSubItem(XmlItem subItem) {
        subItem.m_parentItem = null;
        return m_subList.remove(subItem);
    }

    public boolean removeFromParent() {
        if(m_parentItem != null) {
            return m_parentItem.removeSubItem(this);
        }
        return false;
    }

    public ArrayList getSubList() {
        return m_subList;
    }

    public int subSize() {
        return m_subList.size();
    }

    public Object[] getChildren() {
        if (m_subList.size() == 0) {
            return new Object[0];
        }
        return m_subList.toArray();
    }

    public boolean hasChildren() {
        return m_subList.size() > 0;
    }
    
    public int compareTo(XmlItem item) {
    	return 0;
    }
}
