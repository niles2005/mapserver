package com.xtwsoft.mapserver.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ServerInitServlet
 */
@WebServlet(name = "InitServlet", urlPatterns = { "/ServerInitServlet" }, loadOnStartup=1)
public class MapServerInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapServerInitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig servletConfig) throws ServletException {
		try {
			String realPath = servletConfig.getServletContext().getRealPath("");
			ServerConfig.initInstance(realPath);
			
			if(ServerConfig.getInstance() == null) {
				System.err.println("init server error!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
