package com.xtwsoft.mapserver.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestGson {
	public TestGson() {
//		test1();
//		test2();
		test3();
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
	
	public void test3() {
		String str = "{\"projects\":{\"shanghai\":{\"id\":1,\"name\":\"shanghai\",\"vendor\":\"OSM\",\"imageUrl\":\"img/osm.png\",\"createTime\":\"Jul 11, 2013 10:04:14 AM\",\"creator\":\"niles\",\"info\":\"Test map project for shanghai\",\"module\":{\"d0\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d1\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d2\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}},\"njcc\":{\"name\":\"njcc\",\"vendor\":\"OSM\",\"imageUrl\":\"img/osm.png\",\"createTime\":\"Jul 11, 2013 10:04:14 AM\",\"creator\":\"niles\",\"info\":\"map project for njcc\",\"module\":{\"d0\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d1\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d2\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}},\"test\":{\"name\":\"test\",\"vendor\":\"OSM\",\"imageUrl\":\"img/osm.png\",\"createTime\":\"Jul 11, 2013 10:04:14 AM\",\"creator\":\"niles\",\"info\":\"Test map project\",\"module\":{\"d0\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d1\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d2\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,1,3,3,3]}}},\"updateTime\":\"Jul 11, 2013 10:04:14 AM\"}";
		System.err.println(str);
		JsonParser jsonParser = new JsonParser();
		JsonObject jo = (JsonObject)jsonParser.parse(str);
		
		JsonObject jo1 = jo.getAsJsonObject("projects");
		JsonObject jo2 = jo1.getAsJsonObject("shanghai");
		JsonElement je = jo2.get("id");
		
		System.err.println(je.getAsString());
		Gson gson = new Gson();
		Projects theProjects = gson.fromJson(jo, Projects.class);
		System.err.println(gson.toJson(theProjects));
//		gson.fromJson(str, typeOfT);
//		Projects theProjects = gson.fromJson(str, Projects.class);
//		System.err.println(gson.toJson(theProjects));
		
	}
	
	public static void main(String[] args) {
		new TestGson();
	}

}
