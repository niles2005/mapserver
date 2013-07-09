package com.xtwsoft.mapserver.project;

import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

public class TestAllJsonlib {

	public TestAllJsonlib() {
		buildJsonString();
	}
	
	private void buildJsonString() {
		Projects projects = new Projects();
//		projects.setUpdateTime(new Date());
		
		for(int i=0;i<2;i++) {
			Project proj1 = new Project();
			
			proj1.setId(1L);
			proj1.setName("shanghai" + i);
			proj1.setVendor("OSM" + i);
			proj1.setImageUrl("img/osm.png" + i);
			proj1.setCreator("niles" + i);
			proj1.setInfo("Test map project for shanghai" + i);
			projects.addProject(proj1);
		}
		try {
			System.err.println("start...");
			Gson gson = new Gson();
			
			ObjectMapper mapper = new ObjectMapper();
			
			long t0 = 0;
			long t1 = System.currentTimeMillis();
			String  str = null;
			
			t0 = t1;
			str = JSON.toJSONString(projects);
			t1 = System.currentTimeMillis();
			System.err.println("fastjson:" + (t1 - t0));
			
			t0 = t1;
			str = mapper.writeValueAsString(projects);
			t1 = System.currentTimeMillis();
			System.err.println("jackson:" + (t1 - t0));

			t0 = t1;
			str = gson.toJson(projects);
			t1 = System.currentTimeMillis();
			System.err.println("Gson:" + (t1 - t0));
			
			
			
			t0 = t1;
			Projects projs2 = mapper.readValue(str, Projects.class);
			t1 = System.currentTimeMillis();
			System.err.println("jackson parse object:" + (t1 - t0));
			
			t0 = t1;
			Projects projs1 = gson.fromJson(str, Projects.class);
			t1 = System.currentTimeMillis();
			System.err.println("Gson parse object:" + (t1 - t0));
			
			t0 = t1;
			System.err.println(str);
			Projects projs0 = JSON.parseObject(str,Projects.class);
			t1 = System.currentTimeMillis();
			System.err.println("fastjson parse object:" + (t1 - t0));
			
			Project proj = projs0.getProejct("shanghai0");
			System.err.println(proj.getImageUrl());
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TestAllJsonlib();
	}

}
