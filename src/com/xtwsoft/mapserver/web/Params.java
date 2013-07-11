package com.xtwsoft.mapserver.web;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Params {
	private boolean m_initJson = false;
	private JsonObject m_json = null;
	private HttpServletRequest m_request = null;
	private HttpServletResponse m_response = null;
	public Params(HttpServletRequest request,HttpServletResponse response) {
		try {
			m_request = request;
			m_response = response;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//lazy init
	private void initJson() {
		if(!m_initJson) {
			try {
				m_initJson = true;
				BufferedReader reader = m_request.getReader();
				String str = reader.readLine();
				if(str == null) {
					return;
				}
				StringBuilder strBuff = new StringBuilder();
				while(str != null) {
					strBuff.append(str);
					str = reader.readLine();
				}
				
				JsonParser parser = new JsonParser();
				m_json = (JsonObject) parser.parse(strBuff.toString());
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public String getValue(String name) {
		String value = m_request.getParameter(name);
		if(value == null) {
			initJson();
			if(m_json != null) {
				JsonElement jsonElement = m_json.get(name);
				if(jsonElement != null) {
					value = jsonElement.getAsString();
				}
			}
		}
		return value;
	}
	
	public JsonElement getJsonElement(String name) {
		if(m_json != null) {
			return m_json.get(name);
		}
		return null;
	}
	
	public JsonObject getJsonObject(String name) {
		if(m_json != null) {
			return m_json.getAsJsonObject(name);
		}
		return null;
	}
	
	public String getParameter(String name) {
		String value = m_request.getParameter(name);
		return value;
	}
	
	public void setContentType(String contentType) {
		this.m_response.setContentType(contentType);
	}
	
	public HttpServletRequest getRequest() {
		return m_request;
	}

	public HttpServletResponse getResponse() {
		return m_response;
	}
}
