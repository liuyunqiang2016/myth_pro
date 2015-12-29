<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName,unitCode,buildingCode, areaCode,commCode)
    	{
    		if(window.confirm('确实要删除该单元？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/unitDim.do?method=removeTblUnitDim&unitCode=" + unitCode+"&buildingCode=" +buildingCode+"&areaCode=" +areaCode+"&commCode=" +commCode;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/unitDim.do">
<html:hidden property="method" value="queryTblUnitDim" />
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
    <span class="searchbox">单元名称<input type="text" name="strUnitName" class="searchtxt" value="<c:out value='${strUnitName }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">单元编号</th>
            <th class="t2">单元名称</th>
            <th class="t3">所属楼宇</th>
            <th class="t4">所属区域</th>
            <th class="t5">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblUnitDimBo" items="${tblUnitDims.list}" varStatus="e">
          <tr>
            <td class="t1"><c:out value="${TblUnitDimBo.unitCode}" /></td>
            <td class="t2"><c:out value="${TblUnitDimBo.unitName}" /></td>
            <td class="t3"><c:out value="${TblUnitDimBo.buildingName}" /></td>
            <td class="t4"><c:out value="${TblUnitDimBo.areaName}" /></td>
            <td class="t5">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/unitDim.do?method=forAddTblUnitDim&unitCode=<c:out value="${TblUnitDimBo.unitCode }"/>&buildingCode=<c:out value="${TblUnitDimBo.buildingCode }"/>&areaCode=<c:out value="${TblUnitDimBo.areaCode }"/>&commCode=<c:out value="${TblUnitDimBo.commCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblUnitDimBo.unitCode }"/>', '<c:out value="${TblUnitDimBo.buildingCode }"/>', '<c:out value="${TblUnitDimBo.areaCode }"/>', '<c:out value="${TblUnitDimBo.commCode }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="房号生成" class="btn"
            	onclick="document.location.href='<%=path%>/roomDim.do?method=genRoomDim&&unitCode=<c:out value="${TblUnitDimBo.unitCode }"/>&buildingCode=<c:out value="${TblUnitDimBo.buildingCode }"/>&areaCode=<c:out value="${TblUnitDimBo.areaCode }"/>&commCode=<c:out value="${TblUnitDimBo.commCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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
