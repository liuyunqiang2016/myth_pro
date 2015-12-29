<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
	function linkOnClick(formName, OrgCode)
	{
		if(window.confirm('确实要删除该机构？')){
		var form = document.forms[formName];
		form.action="<%=path%>/orgDim.do?method=removeTblOrgDim&orgCode=" + OrgCode;
		form.submit();
		}    			
	}
	function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
		<html:form action="/orgDim.do">
		<html:hidden property="method" value="queryTblOrgDim" />
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
    <span class="searchbox">机构名称<input type="text" name="strOrgName" class="searchtxt" value="<c:out value='${strOrgName }'/>" /></span>
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
	
	<div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">机构编号</th>
            <th class="t2">机构名称</th>
            <th class="t3">机构状态</th>
            <th class="t4">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblOrgDimBo" items="${tblOrgDims.list}" varStatus="e">
          <tr>
            <td class="t1"><c:out value="${TblOrgDimBo.orgCode}" /></td>
            <td class="t2"><c:out value="${TblOrgDimBo.orgName}" /></td>
            <td class="t3">
            <c:choose>
            	<c:when test="${TblOrgDimBo.orgState=='0'}">禁用</c:when>
            	<c:when test="${TblOrgDimBo.orgState=='1'}">启用</c:when>
            </c:choose>
            </td>
            <td class="t4">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/orgDim.do?method=forAddTblOrgDim&orgCode=<c:out value="${TblOrgDimBo.orgCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';"></span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblOrgDimBo.orgCode }"/>');"></span>
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