<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
   	function linkOnClick(formName,propertyId)
   	{
   		if(window.confirm('确实要删除该物业公司？')){
   			var form = document.forms[formName];
   			form.action="<%=path%>/propertyDim.do?method=removeTblPropertyDim&propertyId=" + propertyId;
   			form.submit();
   		}    			
   	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/propertyDim.do">
<html:hidden property="method" value="queryTblPropertyDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">小区名称
    <select name="strCommCode">
    	<option value="">请选择小区</option>
    	<c:forEach var="comm" items="${commlist}">
    		<option value='<c:out value="${comm.commCode }"/>' <c:if test="${comm.commCode==strCommCode }"> selected="selected"</c:if>>
    			<c:out value="${comm.commName}"/>
    		</option>
    	</c:forEach>
    </select>
    </span>
    <span class="searchbox">区域名称
    <select name="strAreaCode">
    	<option value="">请选择区域</option>
    	<c:forEach var="area" items="${arealist}">
    		<option value='<c:out value="${area.areaCode }"/>' <c:if test="${area.areaCode==strAreaCode }"> selected="selected"</c:if>>
<%--    			<c:out value="${area.commName}"/>-<c:out value="${area.areaName}"/>--%>
				<c:out value="${area.areaName}"/>
    		</option>
    	</c:forEach>
    </select>
    </span>
     <span class="searchbox">楼宇名称
    <select name="strBuildingCode">
    	<option value="">请选择区域</option>
    	<c:forEach var="build" items="${buildinglist}">
    		<option value='<c:out value="${build.buildingCode }"/>' <c:if test="${build.buildingCode==strBuildingCode }"> selected="selected"</c:if>>
<%--    			<c:out value="${build.commName}"/>-<c:out value="${build.areaName}"/>-<c:out value="${build.buildingName}"/>--%>
    			<c:out value="${build.buildingName}"/>
    		</option>
    	</c:forEach>
    </select>
    </span>
    <span class="searchbox">单元名称<input type="text" name="strPropertyName" class="searchtxt" value="<c:out value='${strPropertyName }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">物业公司编号</th>
            <th class="t2">物业公司名称</th>
            <th class="t3">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblPropertyDimBo" items="${tblPropertyDims.list}" varStatus="e">
          <tr>
            <td class="t1"><c:out value="${TblPropertyDimBo.propertyId}" /></td>
            <td class="t2"><c:out value="${TblPropertyDimBo.propertyName}" /></td>
            <td class="t3">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/propertyDim.do?method=forAddTblPropertyDim&propertyId=<c:out value="${TblPropertyDimBo.propertyId}"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>'">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblPropertyDimBo.propertyId}"/>');">
            </span>
          </tr>
        </c:forEach>
     </tbody>
    </table>
   </div>   
   <div class="page"><c:out value="${pr}" escapeXml="false" /></div>
  </div>
 </div>
 <div class="clear"></div>
</html:form>
<jsp:include page="/share/foot.jsp" />
