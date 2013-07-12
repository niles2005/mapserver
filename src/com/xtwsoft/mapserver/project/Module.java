package com.xtwsoft.mapserver.project;

import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

public class Module {
	public Module() {
	}
	
	private int[] d0 = new int[]{0,0,0,0,0,  0,0,0,0,0,  0,0,0,0,0,   0,0,0,0,0};
	private int[] d1 = new int[]{0,0,0,0,0,  0,0,0,0,0,  0,0,0,0,0,   0,0,0,0,0};
	private int[] d2 = new int[]{0,0,0,0,0,  0,0,0,0,0,  0,0,0,0,0,   0,0,0,0,0};

	public int[] getD0() {
		return d0;
	}

	public void setD0(int[] d0) {
		this.d0 = d0;
	}

	public int[] getD1() {
		return d1;
	}

	public void setD1(int[] d1) {
		this.d1 = d1;
	}

	public int[] getD2() {
		return d2;
	}

	public void setD2(int[] d2) {
		this.d2 = d2;
	}

	public static void main(String[] args) {
		try {
			Module module = new Module();
			
			Gson gson = new Gson();
			String str = gson.toJson(module);
			System.err.println(str);
			
			ObjectMapper mapper = new ObjectMapper();
			str = mapper.writeValueAsString(module);
			System.err.println(str);
			
			str = JSON.toJSONString(module);
			System.err.println(str);
			
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
	}

	
}
