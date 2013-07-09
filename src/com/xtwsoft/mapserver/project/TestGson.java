package com.xtwsoft.mapserver.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

public class TestGson {
	public TestGson() {
//		test1();
		test2();
	}

	
	private void test1() {
		try {
			File file = new File("D:\\mywork\\mapserver\\workspace\\mapserver\\WebContent\\WEB-INF\\config\\projects.json");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			reader.close();
			
			if(line.length() > 0) {
				Gson gson = new Gson();
				Projects projects = gson.fromJson(line, Projects.class);
				System.err.println("projects number:" + projects.getProjects().size());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void test2() {
		Projects projects = new Projects();
		projects.setUpdateTime(new Date());
		
		Project proj1 = new Project();
		
		proj1.setId(1L);
		proj1.setName("shanghai");
		proj1.setVendor("OSM");
		proj1.setImageUrl("img/osm.png");
		proj1.setCreateTime(new Date());
		proj1.setCreator("niles");
		proj1.setInfo("Test map project for shanghai");
		projects.addProject(proj1);
		 
		Project proj2 = new Project();
		
		proj2.setName("test");
		proj2.setVendor("OSM");
		proj2.setImageUrl("img/osm.png");
		proj2.setCreateTime(new Date());
		proj2.setCreator("niles");
		proj2.setInfo("Test map project");
		projects.addProject(proj2);
		 
		Project proj3 = new Project();
		
		proj3.setName("njcc");
		proj3.setVendor("OSM");
		proj3.setImageUrl("img/osm.png");
		proj3.setCreateTime(new Date());
		proj3.setCreator("niles");
		proj3.setInfo("map project for njcc");
		projects.addProject(proj3);
		
//		try {
			Gson gson = new Gson();
			String str = gson.toJson(projects);
			System.err.println(str);
			
			Projects theProjects = gson.fromJson(str, Projects.class);
			System.err.println("projects number:" + theProjects.getProjects().size());
			
			System.err.println(gson.toJson(theProjects));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
	}
	
	public static void main(String[] args) {
		new TestGson();
	}

}
