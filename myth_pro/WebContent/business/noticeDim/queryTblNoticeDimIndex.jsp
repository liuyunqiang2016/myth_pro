<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/taglib/c.tld" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:directive.page import="java.util.List" />
<jsp:directive.page import="com.viatt.zhjtpro.bo.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="content-type" />
    <title>物业信息管理系统</title>
    <meta name="keywords" content="keyword ..." />
    <meta name="Description" content="description ..." />
    <!--<link href="favicon.ico" rel="shortcut icon" />-->
    <link href="css/global.css" rel="stylesheet" type="text/css" />
    <link href="css/index.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="./js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="./js/setTab.js"></script>
</head>
	<body>
		<div class="topmenu">
<div id="header">
  <h1 id="logo"><a href="<%=path%>/user.do?method=loginindex">这儿放标题</a></h1>
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
	      			<a href="<%=path%>/index.jsp?menuPare=<c:out value='${fmenu.menuCode }'/>">
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
      <!-- 
      <li><a href="sys_mng/menu_mng/index.jsp"><span class="menu8"></span><strong>系统管理</strong></a></li>
      <li><a href="#"><span class="menu1"></span><strong>物业信息管理</strong></a></li>
      <li><a href="#"  class="current"><span class="menu2"></span><strong>业主信息管理</strong></a></li>
      <li><a href="#"><span class="menu3"></span><strong>门禁管理</strong></a></li>
      <li><a href="#"><span class="menu4"></span><strong>服务信息管理</strong></a></li>
      <li><a href="#"><span class="menu5"></span><strong>缴费管理</strong></a></li>
      <li><a href="#"><span class="menu6"></span><strong>设备信息管理</strong></a></li>
      <li><a href="#"><span class="menu7"></span><strong>公共信息管理</strong></a></li>
       -->
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
              <p>用户名：<a href="#"><c:out value="${user_info.userName }"/></a></p>
              <p>编号：<i><c:out value="${user_info.userCode }"/></i></p>
              <p>性别：<i>男</i></p>
              <p>职位：<i><c:out value="${user_info.deptName }"/></i></p>
           </span>
        </div>
     </dd>
   </dl>
   <dl class="datacount">
     <dt><span></span>数据统计</dt>
     <dd>
       <ul>
         <li><a class="current" href="#">小区数：<i><c:out value="${comm }"/></i></a></li>
         <li><a href="#">楼宇数：<i><c:out value="${build }"/></i></a></li>
         <li><a href="#">单元数：<i><c:out value="${unit }"/></i></a></li>
         <li><a href="#">房号数：<i><c:out value="${room }"/></i></a></li>
         <li><a href="#">在线设备数：<i><c:out value="${equ }"/></i></a></li>
         <li><a href="<%=path%>/user.do?method=logout">退出登录</a></li>
       </ul>
     </dd>
   </dl>
 </div>
 <div id="rightcontent">
    <div class="inner">
       <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">公告编号</th>
            <th class="t2">公告标题</th>
            <th class="t3">有效期</th>
            <th class="t4">发布时间</th>
            <th class="t5">发布人</th>
            <th class="t6">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblNoticeDimBo" items="${tblNoticeDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblNoticeDimBo.noticeCode}" /></td>
            <td class="t2"><c:out value="${TblNoticeDimBo.titile}" /></td>
            <td class="t3"><c:out value="${TblNoticeDimBo.beginDate}" /> - <c:out value="${TblNoticeDimBo.endDate}" /></td>
            <td class="t4"><c:out value="${TblNoticeDimBo.createTime}" /></td>
            <td class="t5"><c:out value="${TblNoticeDimBo.createUsr}" /></td>
            <td class="t6">
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/noticeDim.do?method=viewTblNoticeDim&noticeCode=<c:out value="${TblNoticeDimBo.noticeCode }"/>';">
            </span>
            </td>
          </tr>
        </c:forEach>
     </tbody>
    </table>
   </div>   
   <div class="page"><c:out value="${pr}" escapeXml="false" /></div>
    </div>
 </div>

 <div class="clear"></div>
</div>
<div id="footer">
<p>Copyright@2007-2013 深圳市密思科技有限公司  版权所有</p>
</div>
<script language="javascript" type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
<script language="javascript" type="text/javascript" src="js/global.js"></script>
<script language="javascript" type="text/javascript" src="js/addnew.js"></script>
	</body>
</html>
