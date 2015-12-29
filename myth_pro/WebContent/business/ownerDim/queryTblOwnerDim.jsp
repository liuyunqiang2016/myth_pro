<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String strPraId ="";
	String strChlId = "";
	strPraId = (String)request.getParameter("menuPare");
	strChlId = (String)request.getParameter("menuChild");
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    function linkOnClick(formName, ownerCode)
    {
    	if(window.confirm('确实要删除该业主信息？')){
    		var form = document.forms[formName];
    		form.action="<%=path%>/ownerDim.do?method=removeTblOwnerDim&ownerCode=" + ownerCode;
    		form.submit();
    	}    			
    }
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/ownerDim.do">
<html:hidden property="method" value="queryTblOwnerDim" />
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">业主名称<input type="text" name="strOwnerName" class="searchtxt" value="<c:out value='${strOwnerName }'/>" /></span>
    <span class="searchbox">身份证<input type="text" name="strOwnerNo" class="searchtxt" value="<c:out value='${strOwnerNo }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">业主编号</th>
            <th class="t2">业主名称</th>
            <th class="t3">身份证</th>
            <th class="t4">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblOwnerDimBo" items="${tblOwnerDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblOwnerDimBo.ownerCode}" /></td>
            <td class="t2"><c:out value="${TblOwnerDimBo.ownerName}" /></td>
            <td class="t3"><c:out value="${TblOwnerDimBo.ownerNo}" /></td>
            <td class="t4">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/ownerDim.do?method=forAddTblOwnerDim&ownerCode=<c:out value="${TblOwnerDimBo.ownerCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblOwnerDimBo.ownerCode }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/ownerDim.do?method=viewTblOwnerDim&ownerCode=<c:out value="${TblOwnerDimBo.ownerCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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
