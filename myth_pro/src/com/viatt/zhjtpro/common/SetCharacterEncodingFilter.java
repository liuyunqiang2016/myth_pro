package com.viatt.zhjtpro.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SetCharacterEncodingFilter implements Filter {

	protected String encoding;

	protected FilterConfig filterConfig;

	protected boolean ignore;

	public SetCharacterEncodingFilter() {
		encoding = null;
		filterConfig = null;
		ignore = true;
	}

	public void destroy() {
		encoding = null;
		filterConfig = null;
	}
 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (ignore || request.getCharacterEncoding() == null) {
			String encoding = selectEncoding(request);
			if (encoding != null)
				request.setCharacterEncoding(encoding);
		}
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		com.viatt.zhjtpro.bo.TblUserDimBo bo = (com.viatt.zhjtpro.bo.TblUserDimBo) httpRequest.getSession(true).getAttribute(com.viatt.zhjtpro.bo.TblUserDimBo.USER_INFO_REFERENCE);
		String uri = httpRequest.getRequestURI();
		Object firstmenu = httpRequest.getSession().getAttribute("firstmenu");
		Object rolemenu = httpRequest.getSession().getAttribute("rolemenu");
		Object secmenu = httpRequest.getSession().getAttribute("secmenu");
		if (bo == null || firstmenu==null || rolemenu==null || secmenu==null) {
			if ((uri.endsWith(".jsp")||(uri.endsWith(".do")&&!"/userAction.do".equals(uri))) && !uri.endsWith("/login.jsp")){
				//httpResponse.getWriter().println("<script>window.parent.location.href='" + MyRequest.GetPath(httpRequest, "login.jsp") + "'</script>");
				//httpResponse.getWriter().flush();
				//return;
			}
		}
		
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		encoding = filterConfig.getInitParameter("encoding");
		String value = filterConfig.getInitParameter("ignore");
		if (value == null)
			ignore = true;
		else if (value.equalsIgnoreCase("true"))
			ignore = true;
		else if (value.equalsIgnoreCase("yes"))
			ignore = true;
		else
			ignore = false;
	}

	protected String selectEncoding(ServletRequest request) {
		return encoding;
	}
	
	
}
