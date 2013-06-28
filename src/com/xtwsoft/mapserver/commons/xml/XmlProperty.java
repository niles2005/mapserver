/**
 * Copyright(c) 2010 XTWSoft, Inc.
 *
 */
package com.xtwsoft.mapserver.commons.xml;

public class XmlProperty
{
    protected String m_name = null;

    public boolean isName(String theName)
    {
        return theName.equals(m_name);
    }

    public String getName()
    {
        return m_name;
    }

    private String m_data = null;

    public XmlProperty(String name)
    {
        m_name = name;
    }

    public String getData()
    {
        return m_data;
    }

    public void save(StringBuffer strBuff)
    {
        strBuff.append(" ").append(m_name).append("=\"");
        if(m_data != null) {
            strBuff.append(m_data);
        }
        strBuff.append("\"");
    }

    public boolean setValue(String data)
    {
        this.m_data = data;
        return true;
    }

}
