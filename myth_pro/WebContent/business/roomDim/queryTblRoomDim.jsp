<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName,roomCode,unitCode,buildingCode, areaCode,commCode)
    	{
    		if(window.confirm('确实要删除该房号？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/roomDim.do?method=removeTblRoomDim&areaCode=" + areaCode+"&commCode=" +commCode
    				+"&unitCode=" + unitCode+"&buildingCode=" +buildingCode+"&roomCode=" +roomCode;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/roomDim.do">
<html:hidden property="method" value="queryTblRoomDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">用途
    <select name="strRoomUsr">
    	<option value="">请选择用途</option>
    	<option value='01' <c:if test="${'01'==strRoomUsr }"> selected="selected"</c:if>>出租</option>
    	<option value='02' <c:if test="${'02'==strRoomUsr }"> selected="selected"</c:if>>自住</option>
    </select>
    </span>
    <span class="searchbox">状态
    <select name="strRoomState">
    	<option value="">请选择状态</option>
    	<option value='01' <c:if test="${'01'==strRoomState }"> selected="selected"</c:if>>出租</option>
    	<option value='02' <c:if test="${'02'==strRoomState }"> selected="selected"</c:if>>入住</option>
    </select>
    </span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">房号</th>
            <!--
            <th class="t2">业主姓名</th>
            <th class="t3">房间状态</th>
            <th class="t4">钥匙状态</th>
             -->
            <th class="t5">所属单元</th>
            <th class="t6">所属楼宇</th>
            <th class="t7">所属区域</th>
            <th class="t8">所属小区</th>
            <th class="t9">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblRoomDimBo" items="${tblRoomDims.list}" varStatus="e">
          <tr>
            <td class="t1"><c:out value="${TblRoomDimBo.roomCode}" /></td>
            <!-- 
            <td class="t2"><c:out value="${TblRoomDimBo.ownerCode}" /></td>
            <td class="t3">
            	<c:choose>
            		<c:when test="${TblRoomDimBo.roomState=='01'}">出租</c:when>
            		<c:when test="${TblRoomDimBo.roomState=='02'}">入住</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            </td>
            <td class="t4"><c:out value="${TblRoomDimBo.keyState}" /></td>
             -->
            <td class="t4"><c:out value="${TblRoomDimBo.unitName}" /></td>
            <td class="t4"><c:out value="${TblRoomDimBo.buildingName}" /></td>
            <td class="t4"><c:out value="${TblRoomDimBo.areaName}" /></td>
            <td class="t4"><c:out value="${TblRoomDimBo.commName}" /></td>
            <td class="t5">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/roomDim.do?method=forAddTblRoomDim&roomCode=<c:out value="${TblRoomDimBo.roomCode }"/>&commCode=<c:out value="${TblRoomDimBo.commCode }"/>&areaCode=<c:out value="${TblRoomDimBo.areaCode }"/>&buildingCode=<c:out value="${TblRoomDimBo.buildingCode }"/>&unitCode=<c:out value="${TblRoomDimBo.unitCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblRoomDimBo.roomCode }"/>', '<c:out value="${TblRoomDimBo.unitCode }"/>', '<c:out value="${TblRoomDimBo.buildingCode }"/>', '<c:out value="${TblRoomDimBo.areaCode }"/>', '<c:out value="${TblRoomDimBo.commCode }"/>', '<c:out value="${TblRoomDimBo.commCode }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/roomDim.do?method=viewTblRoomDim&roomCode=<c:out value="${TblRoomDimBo.roomCode }"/>&commCode=<c:out value="${TblRoomDimBo.commCode }"/>&areaCode=<c:out value="${TblRoomDimBo.areaCode }"/>&buildingCode=<c:out value="${TblRoomDimBo.buildingCode }"/>&unitCode=<c:out value="${TblRoomDimBo.unitCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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
