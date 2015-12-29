<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<jsp:include page="/share/top.jsp" />
<%
    String path = request.getContextPath();
	String strPraId ="";
	String strChlId = "";
	strPraId = (String)request.getParameter("menuPare");
	strChlId = (String)request.getParameter("menuChild");
 %>
<SCRIPT language="javascript">
	function linkOnClick(formName, areaCode,commCode)
	{
		if(window.confirm('确实要删除该区域？')){
			var form = document.forms[formName];
			form.action="<%=path%>/areaDim.do?method=removeTblAreaDim&areaCode=" + areaCode+"&commCode=" +commCode;
			form.submit();
		}    			
	}
	function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/areaDim.do">
<html:hidden property="method" value="queryTblAreaDim" />
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
    <span class="searchbox">区域名称<input type="text" name="strAreaName" class="searchtxt" value="<c:out value='${strAreaName }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">区域编号</th>
            <th class="t2">区域名称</th>
            <th class="t2">小区名称</th>
            <th class="t3">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblAreaDimBo" items="${tblAreaDims.list}" varStatus="e">
          <tr>
            <td class="t1"><c:out value="${TblAreaDimBo.areaCode}" /></td>
            <td class="t2"><c:out value="${TblAreaDimBo.areaName}" /></td>
            <td class="t2"><c:out value="${TblAreaDimBo.commName}" /></td>
            <td class="t3">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/areaDim.do?method=forAddTblAreaDim&areaCode=<c:out value="${TblAreaDimBo.areaCode }"/>&commCode=<c:out value="${TblAreaDimBo.commCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblAreaDimBo.areaCode }"/>', '<c:out value="${TblAreaDimBo.commCode }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="楼宇生成" class="btn"
            	onclick="document.location.href='<%=path%>/buildingDim.do?method=genBuildingDim&commCode=<c:out value="${TblAreaDimBo.commCode }"/>&areaCode=<c:out value="${TblAreaDimBo.areaCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span></td>
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