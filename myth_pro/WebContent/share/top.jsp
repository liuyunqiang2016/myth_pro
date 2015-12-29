<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.viatt.zhjtpro.common.Page" />
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="com.viatt.zhjtpro.bo.*" />
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
	String strPraId ="";
	String strChlId = "";
	strPraId = (String)request.getParameter("menuPare");
	strChlId = (String)request.getParameter("menuChild");
	if(strPraId==null&&strChlId==null){
		strPraId=(String)request.getAttribute("menuPare");
		strChlId=(String)request.getAttribute("menuChild");
	}
 %>
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="content-type" />
    <title>物业信息管理系统</title>
    <link href="<%=path%>/css/global.css" rel="stylesheet" type="text/css" />
    <link href="<%=path%>/css/index.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=path%>/js/checkFrm.js"></script>
    <script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div class="topmenu">
<div id="header">
  <h1 id="logo"><a href="<%=path%>/user.do?method=loginindex">这儿放标题。。。。。</a></h1>
  <div id="menu">
    <ul>
      <c:forEach var="fmenu" items="${firstmenu}">
      	<%
      		List rolemenu = (List)session.getAttribute("rolemenu");
      		for(int i=0;i<rolemenu.size();i++){
      			TblRolemenuDimBo bo = (TblRolemenuDimBo)rolemenu.get(i);
      			TblMenuDimBo menu2 = (TblMenuDimBo)pageContext.getAttribute("fmenu");
      			if(bo.getMenuCode().equals(menu2.getMenuCode())){
      	%>
      			<li>
	      			<a <%if(menu2.getMenuCode().equals(strPraId)){%> class="current"<%} %> 
	      				href="<%=path%>/index.jsp?menuPare=<c:out value='${ fmenu.menuCode}'/>">
	      			<span class="
	      			<%if(menu2.getMenuCode().equals("3596619515765")){
	      				out.print("menu1");
	      			}else if(menu2.getMenuCode().equals("3596619524921")){
	      				out.print("menu2");
	      			}else if(menu2.getMenuCode().equals("3596619540968")){
	      				out.print("menu3");
	      			}else if(menu2.getMenuCode().equals("3596619554125")){
	      				out.print("menu4");
	      			}else if(menu2.getMenuCode().equals("3596619564437")){
	      				out.print("menu5");
	      			}else if(menu2.getMenuCode().equals("3596619579203")){
	      				out.print("menu6");
	      			}else if(menu2.getMenuCode().equals("3596619591312")){
	      				out.print("menu7");
	      			}else if(menu2.getMenuCode().equals("3596619306062")){
	      				out.print("menu8");
	      			}
	      			%>
	      			"></span>
	      			<strong><c:out value="${fmenu.menuName}"/></strong>
	      			</a>
      			</li>
      	<%
      			}
      		}
      	%>
      </c:forEach>
    </ul>
  </div>
 </div>
</div>
<div id="maincontent">
 <div id="leftcontent">
   <dl class="userinfo">
     <dt><span></span>用户信息</dt>
     <dd>
        <div class="userinfobox">
           <span class="txtbox">
              <p>用户名：<a href="#"><c:out value="${user_info.logName }"/></a></p>
              <p>编号：<i><c:out value="${user_info.userCode }"/></i></p>
              <p>性别：<i>男</i></p>
              <p>职位：<i><c:out value="${user_info.userName}"/></i></p>
           </span>
           <a href="<%=path%>/user.do?method=logout"><font color="blue">退出</font></a>
        </div>
     </dd>
   </dl>
   <dl>
     <dt>菜单列表</dt>
     <dd>
       <ul>
       	 <c:forEach var="smenu" items="${secmenu}">
      	<%
      		List rolemenu = (List)session.getAttribute("rolemenu");
      		for(int i=0;i<rolemenu.size();i++){
      			TblRolemenuDimBo bo = (TblRolemenuDimBo)rolemenu.get(i);
      			TblMenuDimBo menu3 = (TblMenuDimBo)pageContext.getAttribute("smenu");
      			if(bo.getMenuCode().equals(menu3.getMenuCode()) && 
      				menu3.getMenuPar().equals(strPraId)){
      	%>
      			<li>
	      			<a <%if(menu3.getMenuCode().equals(strChlId)){%> class="current"<%} %>
	      				href="<%=path%><c:out value='${smenu.menuUrl }'/>&menuPare=<c:out value='${smenu.menuPar }'/>&menuChild=<c:out value='${smenu.menuCode }'/>">
						<c:out value="${smenu.menuName}"/>
					</a>
      			</li>
      	<%
      			}
      		}
      	%>
      </c:forEach>
       </ul>
     </dd>
   </dl>
 </div>
