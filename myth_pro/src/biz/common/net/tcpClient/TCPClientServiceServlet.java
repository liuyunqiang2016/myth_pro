package biz.common.net.tcpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import biz.common.BGMLogger;

//@SuppressWarnings("serial")
public class TCPClientServiceServlet extends HttpServlet
{
	private Map services;

	private String rootPath;

	private WebApplicationContext Swac;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		super.service(request, response);
	}

	public void destroy()
	{
		BGMLogger.LogInfo("Destory the TCPClient servlet: " + getServletName());
	}

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		BGMLogger.LogInfo("Start up  the TCPClient servlet: " + getServletName());
		doInit();
		BGMLogger.LogInfo("Start up  the TCPClient servlet: " + getServletName()
				+ " OK!");
	}

	private void doInit() throws ServletException
	{
		this.services = new HashMap();
		if ((this.rootPath != null) && (this.rootPath.startsWith("./")))
			this.rootPath = getServletContext().getRealPath("/");
		if (this.rootPath == null)
		{
			this.rootPath = getServletContext().getRealPath("/");
		}
		this.rootPath = this.rootPath.replace('\\', '/');
		if (!(this.rootPath.endsWith("/")))
		{
			this.rootPath += "/";
		}
		this.Swac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());
		initTcpServiceList();
	}

	private void initTcpServiceList()
	{
		String servletContextFile = getInitParameter("servletContextFile");
		BGMLogger.LogInfo("Initialize TCPClient Servlet Context from "
				+ servletContextFile);
		String strConfFilePath = getInitParameter("confFilePath");
		TCPClientServiceFactory tcpFactory = new TCPClientServiceFactory();
		tcpFactory.setConfFilePath(this.rootPath + strConfFilePath);
		tcpFactory.initializeServiceFactory(this.rootPath + servletContextFile);
		this.services = tcpFactory.getServices();
		Object[] svcIds = this.services.keySet().toArray();
		for (int i = 0; i < svcIds.length; ++i)
		{
			TCPClient tss = (TCPClient) this.services
					.get(svcIds[i]);
			tss.setWac(Swac);
//			tss.startUp();
		}
	}

}
