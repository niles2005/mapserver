/**
 * Copyright(c) 2010 XTWSoft, Inc.
 *
 * @author NieLei E-mail:niles2010@live.cn
 * @version create time£º2010-8-3 ÏÂÎç08:55:56
 */
package com.xtwsoft.mapserver.template;

import com.xtwsoft.mapserver.commons.xml.XmlItem;
import com.xtwsoft.mapserver.commons.xml.XmlProperty;

public class TField extends XmlItem {
	protected XmlProperty name = new XmlProperty("name");
	protected XmlProperty value = new XmlProperty("value");
	protected String[] m_fields = null;
	public TField() {
		this.m_itemName = "field";
		m_xmlProps = new XmlProperty[2];
		m_xmlProps[0] = name;
		m_xmlProps[1] = value;
		
	}

	public TGroup getOwnerGroup() {
	return (TGroup)this.getParentItem().getParentItem();
	}
	
	public TItem getOwnerItem() {
		return (TItem)this.getParentItem();
	}

	public String[] getFields() {
		return m_fields;
	}

	public XmlItem createSubItem(String name) {
		return null;
	}

	public String getName() {
		return name.getData();
	}

	public void setName(String value) {
		name.setValue(value);
	}

	public String getValue() {
		return value.getData();
	}

	public void setValue(String strValue) {
		value.setValue(strValue);
	}

	public void afterSetProperty() {
		String strFields = value.getData();
		m_fields = new String[20];
		String[] itemArr = strFields.split("\\|");
		if(itemArr.length == 20) {
			for(int i=0;i<20;i++) {
				String item = ((String)itemArr[i]).trim();
				m_fields[i] = item;
			}
		} else if(itemArr.length == 1) {
			String item = ((String)itemArr[0]).trim();
			for(int i=0;i<20;i++) {
				m_fields[i] = item;
			}
		} else {
			System.err.println(name.getName() + "'s array length is not 20 or 1:" + value.getData());
		}
	}
	
	public int[] m_intArray = null;
	public int[] getIntArray() {
		if(m_intArray != null) {
			return m_intArray;
		}
		m_intArray = new int[m_fields.length];
		for(int i=0;i<m_fields.length;i++) {
			m_intArray[i] = Integer.parseInt(m_fields[i]);
		}
		return m_intArray;
	}
	
	public float[] m_floatArray = null;
	public float[] getFloatArray() {
		if(m_floatArray != null) {
			return m_floatArray;
		}
		m_floatArray = new float[m_fields.length];
		for(int i=0;i<m_fields.length;i++) {
			m_floatArray[i] = Float.parseFloat(m_fields[i]);
		}
		return m_floatArray;
	}
	

	public static final String Area_Exist = "area_exist";
	public static final String Area_SimplifyPixel = "area_simplifypixel";
	public static final String Area_ShowPixel = "area_showpixel";
	public static final String Area_ShowRiverWidth = "area_showriverwidth";
	public static final String Area_ShowNameRange = "area_shownamerange";

	
	public static final String Line_Exist = "line_exist";
	public static final String Line_SimplifyPixel = "line_simplifypixel";
	public static final String Line_MaxAngleFilter = "line_maxanglefilter";
	public static final String Line_NameFilter = "line_namefilter";
	public static final String Line_NameBlank = "line_nameblank";
	public static final String Line_NameGroupMargin = "line_namegroupmargin";
	

	public static final String Point_Exist = "point_exist";
	public static final String Point_FontSize = "point_fontsize";
	public static final String Point_FontStyle = "point_fontstyle";
	public static final String Point_IconSize = "point_iconstyle";
	public static final String Point_LabelOrient = "point_labelorient";
	public static final String Point_LabelLevel = "point_labellevel";
	public static final String Point_LabelMargin = "point_labelmargin";
	public static final String Point_LabelCharSpace = "point_labelcharspace";
	public static final String Point_SameClassRange = "point_sameclassrange";
	public static final String Point_SameTypeRange = "point_sametyperange";
	public static final String Point_SameNameRange = "point_samenamerange";

}
