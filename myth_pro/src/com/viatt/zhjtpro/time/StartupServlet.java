package com.viatt.zhjtpro.time;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class StartupServlet extends HttpServlet {

	public static WebApplicationContext bean = null;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.service(request, response);
	}

	public void destroy() {
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		bean = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	}
}
