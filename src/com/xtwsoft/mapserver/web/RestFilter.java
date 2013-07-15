package com.xtwsoft.mapserver.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.xtwsoft.mapserver.global.Global;
import com.xtwsoft.mapserver.project.Project;

public class RestFilter implements Filter {
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			String realPath = filterConfig.getServletContext().getRealPath("");
			ServerConfig.initInstance(realPath);
			
			if(ServerConfig.getInstance() == null) {
				System.err.println("init server error!");
			}
			
			Global.getInstance();
			
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

			
//			System.err.println(realUrl);
			if(realUrl.startsWith("/")) {
				if(realUrl.length() == 1) {
					chain.doFilter(req, res);
				} else {
					if(realUrl.indexOf(".") != -1) {//angular.js  main.css
						chain.doFilter(req, res);
					} else {//
						Project project = Global.getInstance().getProject(realUrl.substring(1));
						if(project != null) {
							request.setAttribute("project", project);
							request.getRequestDispatcher(
					                "/index2.jsp").forward(request,res);
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
