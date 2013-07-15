package com.xtwsoft.mapserver.file;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;

import com.alibaba.fastjson.JSON;

public class TestFileDatas {
	public TestFileDatas() {
		test1();
	}
	
	private void test1() {
		try {
			ObjectMapper mapper= new ObjectMapper();
			
			FileDatas fds = new FileDatas();
			FileData fd1 = new FileData("a",12);
			FileData fd2 = new FileData("b",13);
			FileData fd3 = new FileData("c",9);
			fds.addFileData(fd1);
			fds.addFileData(fd2);
			fds.addFileData(fd3);
			
			String str = mapper.writeValueAsString(fds);
			System.err.println(str);
			FileDatas fileDatas = JSON.parseObject(str,FileDatas.class);
			
//			Map filesMap= mapper.readValue(str, Map.class);
			System.out.println(((FileData)fileDatas.fetchFileData("a")).getSize());
			
			str = mapper.writeValueAsString(fileDatas);
			
//			str = mapper.writeValueAsString(filesMap);
			System.err.println(str);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void test2() {
		
	}
	
	public static void main(String[] args) {
		new TestFileDatas();
	}

}
