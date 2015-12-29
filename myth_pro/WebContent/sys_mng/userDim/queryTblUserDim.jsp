<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName, userCode)
    	{
    		if(window.confirm('确实要删除该用户信息？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/userDim.do?method=removeTblUserDim&UserCode=" + userCode;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/userDim.do">
<html:hidden property="method" value="queryTblUserDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">用户名称<input type="text" name="strUserName" class="searchtxt" value="<c:out value='${strUserName }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">用户编号</th>
            <th class="t2">登录名</th>
            <th class="t3">用户名称</th>
            <th class="t4">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblUserDimBo" items="${tblUserDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblUserDimBo.userCode}" /></td>
            <td class="t2"><c:out value="${TblUserDimBo.logName}" /></td>
            <td class="t3"><c:out value="${TblUserDimBo.userName}" /></td>
            <td class="t4">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/userDim.do?method=forAddTblUserDim&userCode=<c:out value="${TblUserDimBo.userCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblUserDimBo.userCode }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/userDim.do?method=viewTblUserDim&UserCode=<c:out value="${TblUserDimBo.userCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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
</html:form>
<jsp:include page="/share/foot.jsp" />
