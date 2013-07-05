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

public class RestFilter implements Filter {
	private WorkServlet m_workServlet = new WorkServlet();
	public void init(FilterConfig config) throws ServletException {
		System.err.println();
//		String scanPackage = config.getInitParameter("scanPackage");
//
//		if (scanPackage == null || scanPackage.length() == 0) {
//			throw new NullPointerException();
//		}
//
//		if(scanPackage.toLowerCase().endsWith(".xml")){
//			servletFactory = new XmlServletFactoryImpl(scanPackage);
//		}else{
//			servletFactory = new AnnotationServletFactoryImpl(scanPackage);
//		}
//		
//		// 提供外部程序访问servlet工厂入口
//		config.getServletContext().setAttribute("servletFactory", servletFactory);
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		String realUrl = request.getRequestURI().replaceFirst(
				request.getContextPath(), "");

		
		System.err.println(realUrl);
		if(realUrl.startsWith("/")) {
			int paramSize = request.getParameterMap().size();
			if(realUrl.length() == 1) {
				if(paramSize == 0) {
					chain.doFilter(req, res);
				} else {
					m_workServlet.service(req, res);
				}
				return;
			} else {
				if(paramSize == 0) {
					if(realUrl.indexOf(".") != -1) {//angular.js  main.css
						chain.doFilter(req, res);
					} else {//
						request.getRequestDispatcher(
				                "/index2.html").forward(request,res);
					}
					return;
				} else {
					Project project = ProjectManager.getInstance().getProejct(realUrl.substring(1));
					System.err.println("call:" + realUrl.substring(1) + "->" + project);
					if(project != null) {
						m_workServlet.service(req, res);
						return;
					}
				}
			}
		}
		
		chain.doFilter(req, res);
	}

	public void destroy() {
	}

}
