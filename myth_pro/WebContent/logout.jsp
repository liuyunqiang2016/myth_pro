<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	java.util.Enumeration en = session.getAttributeNames();
	while (en.hasMoreElements()) {
		String attributeName = (String) en.nextElement();
		session.removeAttribute(attributeName);
	}
	out.print("<script>window.parent.location.href='"+ com.viatt.zhjtpro.common.MyRequest.GetPath(request, "login.jsp") + "'</script>");
%>