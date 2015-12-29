<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName,buildingCode, commCode,areaCode)
    	{
    		if(window.confirm('确实要删除该楼宇？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/buildingDim.do?method=removeTblBuildingDim&buildingCode=" + buildingCode+"&commCode=" +commCode+"&areaCode="+areaCode;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/buildingDim.do">
<html:hidden property="method" value="queryTblBuildingDim" />
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
    			<c:out value="${area.commCode}"/>-<c:out value="${area.areaName}"/>
    		</option>
    	</c:forEach>
    </select>
    </span>
    <span class="searchbox">楼宇名称<input type="text" name="strBuildingName" class="searchtxt" value="<c:out value='${strBuildingName }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">楼宇编号</th>
            <th class="t2">楼宇名称</th>
            <th class="t3">区域名称</th>
            <th class="t4">小区名称</th>
            <th class="t4">占地面积</th>
            <th class="t4">建筑面积</th>
            <th class="t5">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblBuildingDimBo" items="${tblBuildingDims.list}" varStatus="e">
          <tr>
            <td class="t1"><c:out value="${TblBuildingDimBo.buildingCode}" /></td>
            <td class="t2"><c:out value="${TblBuildingDimBo.buildingName}" /></td>
            <td class="t3"><c:out value="${TblBuildingDimBo.areaName}" /></td>
            <td class="t4"><c:out value="${TblBuildingDimBo.commName}" /></td>
            <td class="t4"><c:out value="${TblBuildingDimBo.buildingArea}" /></td>
            <td class="t4"><c:out value="${TblBuildingDimBo.buildingArea2}" /></td>
            <td class="t5">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/buildingDim.do?method=forAddTblBuildingDim&buildingCode=<c:out value="${TblBuildingDimBo.buildingCode }"/>&commCode=<c:out value="${TblBuildingDimBo.commCode }"/>&areaCode=<c:out value="${TblBuildingDimBo.areaCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblBuildingDimBo.buildingCode }"/>', '<c:out value="${TblBuildingDimBo.commCode }"/>','<c:out value="${TblBuildingDimBo.areaCode }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="单元生成" class="btn"
            	onclick="document.location.href='<%=path%>/unitDim.do?method=genUnitDim&buildingCode=<c:out value="${TblBuildingDimBo.buildingCode }"/>&commCode=<c:out value="${TblBuildingDimBo.commCode }"/>&areaCode=<c:out value="${TblBuildingDimBo.areaCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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
