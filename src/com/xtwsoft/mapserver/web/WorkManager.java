package com.xtwsoft.mapserver.web;


public class WorkManager {
	private static WorkManager m_instance = null;
	private WorkManager() {
	}

	public static WorkManager getInstance() {
		if(m_instance == null) {
			m_instance = new WorkManager(); 
		}
		return m_instance;
	}
	
	public String doWork(String id,String action) {
		
		return null;
	}
	
}
