<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
	function linkOnClick(formName, RoleCode)
	{
		if(window.confirm('确实要删除该角色？')){
		var form = document.forms[formName];
		form.action="<%=path%>/roleDim.do?method=removeTblRoleDim&roleCode=" + RoleCode;
		form.submit();
		}    			
	}
	function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
		<html:form action="/roleDim.do">
		<html:hidden property="method" value="queryTblRoleDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title">
    <div class="border">
    <span class="searchbox">角色名称<input type="text" name="strRoleName" class="searchtxt" value="<c:out value='${strRoleName }'/>" /></span>
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
	
	<div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">角色编号</th>
            <th class="t2">角色名称</th>
            <th class="t3">机构编号</th>
            <th class="t4">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblRoleDimBo" items="${tblRoleDims.list}" varStatus="e">
          <tr>
            <td class="t1"><c:out value="${TblRoleDimBo.roleCode}" /></td>
            <td class="t2"><c:out value="${TblRoleDimBo.roleName}" /></td>
            <td class="t3"><c:out value="${TblRoleDimBo.orgCode}" /></td>
            <td class="t4">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/roleDim.do?method=forAddTblRoleDim&roleCode=<c:out value="${TblRoleDimBo.roleCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';"></span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblRoleDimBo.roleCode }"/>');"></span>
            <span class="btnoperal"><input type="button" value="分配菜单" class="btn"
            	onclick="document.location.href='<%=path%>/roleDim.do?method=signTblRolemenuDim&roleCode=<c:out value="${TblRoleDimBo.roleCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';"></span>
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
</html:form>
<jsp:include page="/share/foot.jsp" />     		