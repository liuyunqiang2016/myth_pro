<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String strPraId =(String)request.getParameter("menuPare");
String strChlId =(String)request.getParameter("menuChild");
%>
<jsp:include page="/share/top.jsp" />
<script type="text/javascript">
	function queryOnclick(){
		document.forms[0].submit();
	}
</script>
<html:form action="/logDim.do">
<html:hidden property="method" value="queryTblLogDim" />
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">时间<input type="text" name="strBegin" class="searchtxt" value="<c:out value='${strBegin }'/>"  maxlength="8"/>
    -<input type="text" name="strEnd" class="searchtxt" value="<c:out value='${strEnd }'/>"  maxlength="8"/></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t2">操作人</th>
            <th class="t3">操作时间</th>
            <th class="t4">操作内容</th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblLogDimBo" items="${tblLogDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblLogDimBo.operName}" /></td>
            <td class="t3"><c:out value="${TblLogDimBo.creTime}" /></td>
            <td class="t4"><c:out value="${TblLogDimBo.logContent}" /></td>
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
