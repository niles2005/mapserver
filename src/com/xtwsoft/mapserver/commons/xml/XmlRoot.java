/**
 * Copyright(c) 2010 XTWSoft, Inc.
 *
 */
package com.xtwsoft.mapserver.commons.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.InputStream;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public abstract class XmlRoot extends XmlItem {
	public XmlRoot() {
	}

	public String getIndent() {
		return "";
	}

	private URL getUrl(String path) {
		if (path == null) {
			return null;
		}
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		return XmlRoot.class.getResource(path);
	}

	public void load(String filePath) {
		load(getUrl(filePath));
	}

	public void load(URL url) {
		try {
			load(url.openStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean load(InputStream in) {
		if (in == null) {
			return false;
		}

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(in);
			Node node = document.getFirstChild();
			setNode(node);
			loadedNode();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean load(File file) {
		try {
			if (file == null) {
				return false;
			}
			if (!file.exists()) {
				return false;
			}
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = builder.parse(file);
			Node node = document.getFirstChild();
			setNode(node);
			loadedNode();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void loadedNode() {
	}

	public void save(String filePath) {
		File f = new File(filePath);
		save(f);
	}

	public void save(File f) {
		if (f == null || f.isDirectory()) {
			return;
		}

		try {
			f.createNewFile();

			StringBuffer strBuff = new StringBuffer();
			strBuff.append("<?xml version=\"1.0\"?>");
			// strBuff.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
			strBuff.append("\r\n");
			saveItem(strBuff);

			PrintWriter writer = new PrintWriter(new FileWriter(f));
			writer.write(strBuff.toString());
			writer.flush();
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public String getXMLContent() {
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("<?xml version=\"1.0\"?>");
		// strBuff.append("<?xml version=\"1.0\" encoding=\"GB2312\"?>");
		strBuff.append("\r\n");
		saveItem(strBuff);
		return strBuff.toString();
	}
}
