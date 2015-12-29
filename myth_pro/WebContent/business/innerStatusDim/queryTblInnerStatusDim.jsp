<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/innerStatusDim.do">
<html:hidden property="method" value="queryInnerStatusDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">门口机设备编号<input type="text" name="strEquCode" class="searchtxt" value="<c:out value='${strEquCode }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">门口机编号</th>
            <th class="t2">室内机编号</th>
            <th class="t3">权限</th>
            <th class="t4">操作类型</th>
            <th class="t5">发送状态</th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="bo" items="${tblInnerStatusDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${bo.outerid}" /></td>
            <td class="t2"><c:out value="${bo.innerid}" /></td>
            <td class="t3">
            	<c:choose>
            		<c:when test="${bo.optype=='1'}">开门</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            </td>
            <td class="t4">
            	<c:choose>
            		<c:when test="${bo.pertype=='0'}">新增</c:when>
            		<c:when test="${bo.pertype=='1'}">删除</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            </td>
            <td class="t5">
            	<c:choose>
            		<c:when test="${bo.sendzt=='0'}">未发送</c:when>
            		<c:when test="${bo.sendzt=='1'}">已发送</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
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
