<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
	function linkOnClick(formName, commCode)
	{
		if(window.confirm('确实要删除该小区？')){
		var form = document.forms[formName];
		form.action="<%=path%>/commDim.do?method=removeTblCommDim&commCode=" + commCode;
		form.submit();
		}    			
	}
	function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
		<html:form action="/commDim.do">
		<html:hidden property="method" value="queryTblCommDim" />
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
    <span class="searchbox">小区名称<input type="text" name="strCommName" class="searchtxt" value="<c:out value='${strCommName }'/>" /></span>
    <!-- <span class="searchbox">物业公司名称<input type="text" name="strCommPro" class="searchtxt" value="<c:out value='${strCommPro }'/>" /></span> -->
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
	
	<div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">小区编号</th>
            <th class="t2">小区名称</th>
            <th class="t3">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblCommDimBo" items="${tblCommDims.list}" varStatus="e">
          <tr>
            <td class="t1"><c:out value="${TblCommDimBo.commCode}" /></td>
            <td class="t2"><c:out value="${TblCommDimBo.commName}" /></td>
            <td class="t3">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/commDim.do?method=forAddTblCommDim&commCode=<c:out value="${TblCommDimBo.commCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';"></span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblCommDimBo.commCode }"/>');"></span>
            <span class="btnoperal"><input type="button" value="区域生成" class="btn"
            	onclick="document.location.href='<%=path%>/areaDim.do?method=genAreaDim&commCode=<c:out value="${TblCommDimBo.commCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';"></span></td>
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