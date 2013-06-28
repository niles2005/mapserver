package com.xtwsoft.mapserver.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WorkServlet
 */
@WebServlet("/work")
public class WorkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPage(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPage(request, response);
	}

	public void doPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		ServletOutputStream sos = response.getOutputStream();
		response.setContentType("text/html; charset=UTF-8");
		String retInfo = null;
		if(ServerConfig.getInstance() == null) {
			retInfo = WebUtil.error("init error!");
		} else {
			try {
				retInfo = WorkManager.getInstance().doWork(request,response);
			} catch(Exception ex) {
				ex.printStackTrace();
				retInfo = WebUtil.error("Exception:" + ex.getMessage());
			}
		}
		
		if(retInfo != null) {
			if(WebConfig.SupportJsonP) {//support jsonP
				String callback = request.getParameter("callback");
				if(callback != null) {
					retInfo = callback + "(" + retInfo + ");";
				}
			}
			
			sos.write(retInfo.getBytes("UTF-8"));
		}
	}
}
