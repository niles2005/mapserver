package com.xtwsoft.mapserver.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class TestFastjson {
	public TestFastjson() {
//		test1();
//		test2();
//		test3();
		test4();
	}

	
	private void test1() {
		try {
			File file = new File("D:\\mywork\\mapserver\\workspace\\mapserver\\WebContent\\WEB-INF\\config\\projects.json");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			reader.close();
			
			if(line.length() > 0) {
				Projects projects = JSON.parseObject(line, Projects.class);
				System.err.println("projects number:" + projects.getProjects().size());
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void test2() {
		Projects projects = new Projects();
		projects.setUpdateTime(new Date().getTime());
		//����ʱ����������쳣��Exception in thread "main" java.lang.ClassCastException: com.alibaba.fastjson.JSONObject cannot be cast to com.xtwsoft.mapserver.project.Project
		
		Project proj1 = new Project();
		
		proj1.setId(1L);
		proj1.setName("shanghai");
		proj1.setVendor("OSM");
		proj1.setImageUrl("img/osm.png");
		proj1.setCreateTime(new Date().getTime());
		proj1.setCreator("niles");
		proj1.setInfo("Test map project for shanghai");
		projects.addProject(proj1);
		 
		Project proj2 = new Project();
		
		proj2.setName("test");
		proj2.setVendor("OSM");
		proj2.setImageUrl("img/osm.png");
		proj2.setCreateTime(new Date().getTime());
		proj2.setCreator("niles");
		proj2.setInfo("Test map project");
		projects.addProject(proj2);
		 
		Project proj3 = new Project();
		
		proj3.setName("njcc");
		proj3.setVendor("OSM");
		proj3.setImageUrl("img/osm.png");
		proj3.setCreateTime(new Date().getTime());
		proj3.setCreator("niles");
		proj3.setInfo("map project for njcc");
		projects.addProject(proj3);
		
		System.err.println(projects.getProejct("shanghai"));
		String jsonString = JSON.toJSONString(projects);
		 
		System.err.println(jsonString);
		
		Projects projs = JSON.parseObject(jsonString,Projects.class);
		System.err.println(projs.getProejct("shanghai"));
	}
	
	private void test3() {
		Projects projects = new Projects();
		
		for(int i=0;i<2;i++) {
			Project proj1 = new Project();
			
			proj1.setId(1L);
			proj1.setName("shanghai" + i);
			proj1.setVendor("OSM" + i);
			proj1.setImageUrl("img/osm.png" + i);
			proj1.setCreateTime(new Date().getTime());
			proj1.setCreator("niles" + i);
			proj1.setInfo("Test map project for shanghai" + i);
			projects.addProject(proj1);
		}
		String jsonString = JSON.toJSONString(projects);
		System.err.println(jsonString);
		Projects projs = JSON.parseObject(jsonString,Projects.class);
		System.err.println(projs.getProejct("shanghai1"));

	}
	
	public void test4() {
		try {
			String str = "{\"projects\":{\"shanghai\":{\"id\":1,\"name\":\"shanghai\",\"vendor\":\"OSM\",\"imageUrl\":\"img/osm.png\",\"createTime\":\"2353553464\",\"creator\":\"niles\",\"info\":\"Test map project for shanghai\",\"module\":{\"d0\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d1\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d2\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}},\"njcc\":{\"name\":\"njcc\",\"vendor\":\"OSM\",\"imageUrl\":\"img/osm.png\",\"createTime\":\"47457547547\",\"creator\":\"niles\",\"info\":\"map project for njcc\",\"module\":{\"d0\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d1\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d2\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]}},\"test\":{\"name\":\"test\",\"vendor\":\"OSM\",\"imageUrl\":\"img/osm.png\",\"createTime\":\"3464764745\",\"creator\":\"niles\",\"info\":\"Test map project\",\"module\":{\"d0\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d1\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],\"d2\":[0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,1,3,3,4]}}},\"updateTime\":\"5435436464\"}";
			JSONObject jsonObject = JSON.parseObject(str);
			
			Projects projects = JSON.parseObject(str,Projects.class);
			System.err.println(JSON.toJSONString(projects));
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	
	public static void main(String[] args) {
		new TestFastjson();
	}
}
