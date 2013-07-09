package com.xtwsoft.mapserver.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ServletOutputStream sos = response.getOutputStream();
		response.setContentType("text/html; charset=UTF-8");
		String retInfo = null;
		try {
			Params params = new Params(request,response);
			retInfo = doPostWork(params);
		} catch (Exception ex) {
			ex.printStackTrace();
			retInfo = WebUtil.error("Exception:" + ex.getMessage());
		}

		if (retInfo != null) {
			if (WebConfig.SupportJsonP) {// support jsonP
				String callback = request.getParameter("callback");
				if (callback != null) {
					retInfo = callback + "(" + retInfo + ");";
				}
			}
			sos.write(retInfo.getBytes("UTF-8"));
		}
	}
	
	protected abstract String doPostWork(Params params);
}
