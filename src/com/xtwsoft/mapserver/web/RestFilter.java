package com.xtwsoft.mapserver.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.xtwsoft.mapserver.project.Project;
import com.xtwsoft.mapserver.project.ProjectManager;
import com.xtwsoft.mapserver.template.TemplateManager;

public class RestFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			String realPath = filterConfig.getServletContext().getRealPath("");
			ServerConfig.initInstance(realPath);
			
			if(ServerConfig.getInstance() == null) {
				System.err.println("init server error!");
			}
			TemplateManager.initInstance();
			
			ProjectManager.initInstance();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		String method = request.getMethod();
		if("GET".equals(method)){  
			String realUrl = request.getRequestURI().replaceFirst(
					request.getContextPath(), "");

			
			System.err.println(realUrl);
			if(realUrl.startsWith("/")) {
				if(realUrl.length() == 1) {
					chain.doFilter(req, res);
				} else {
					if(realUrl.indexOf(".") != -1) {//angular.js  main.css
						chain.doFilter(req, res);
					} else {//
						Project project = ProjectManager.getInstance().getProejct(realUrl.substring(1));
						if(project != null) {
							request.getRequestDispatcher(
					                "/index2.html").forward(request,res);
						} else {
							chain.doFilter(req, res);
						}
					}
				}
				return;
			}
		} else if("POST".equals(method)) {
			chain.doFilter(req, res);
//			m_workServlet.service(req, res);
		} else {
			chain.doFilter(req, res);
		}
		
	}

	public void destroy() {
	}

}
