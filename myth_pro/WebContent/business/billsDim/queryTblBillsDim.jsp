<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%><%
String path = request.getContextPath();
String strPraId ="";
String strChlId = "";
strPraId = (String)request.getParameter("menuPare");
strChlId = (String)request.getParameter("menuChild");
%>

<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName, billCode,ItemCode,roomCode)
    	{
    		if(window.confirm('确实要删除该缴费项目信息？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/billsDim.do?method=removeTblBillsDim&itemCode=" + ItemCode + "&billCode=" + billCode
    				+"&roomCode="+roomCode;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/billsDim.do">
<html:hidden property="method" value="queryTblBillsDim" />
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">缴费单据编号</th>
            <th class="t2">缴费项目编号</th>
            <th class="t3">缴费房号</th>
            <th class="t4">费用</th>
            <th class="t4">缴费时间</th>
            <th class="t4">是否缴费</th>
            <th class="t5">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblBillsDimBo" items="${tblBillsDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblBillsDimBo.billCode}" /></td>
            <td class="t2">
            	<c:forEach var="item" items="${itemList}">
            		<c:if test="${item.itemCode==TblBillsDimBo.itemCode}">
            			<c:out value="${item.itemName}"/>
            		</c:if>
            	</c:forEach>
            </td>
            <td class="t3">
            	<c:forEach var="room" items="${roomList}">
            		<c:if test="${room.roomCode==TblBillsDimBo.roomCode
            				and room.unitCode == TblBillsDimBo.unitID
            				and room.areaCode == TblBillsDimBo.areaID
            				and room.commCode == TblBillsDimBo.commID
            				and room.buildingCode == TblBillsDimBo.buldID}"> 
            			<c:out value="${room.commName}"></c:out>小区
            			<c:out value="${room.areaName}"></c:out>区
            			<c:out value="${room.buildingName}"></c:out>楼宇<br/>
            			<c:out value="${room.unitName}"></c:out>单元
            			<c:out value="${room.roomCode}"></c:out>号
            		</c:if>
            	</c:forEach>
            </td>
            <td class="t4"><c:out value="${TblBillsDimBo.billAmt}" /></td>
            <td class="t4"><c:out value="${TblBillsDimBo.jfDate}" /></td>
            <td class="t6">
            	<c:choose>
            		<c:when test="${TblBillsDimBo.isPay=='0'}">未缴</c:when>
            		<c:when test="${TblBillsDimBo.isPay=='1'}">已缴</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            </td>
            <td class="t5">
            <span class="btnoperal"><input type="button" value="新增" class="btn"
            	onclick="document.location.href='<%=path%>/billsDim.do?method=forAddTblBillsDim&billCode=<c:out value="${TblBillsDimBo.billCode }"/>&roomCode=<c:out value="${TblBillsDimBo.roomCode }"/>&commID=<c:out value="${TblBillsDimBo.commID }"/>&areaID=<c:out value="${TblBillsDimBo.areaID }"/>&unitID=<c:out value="${TblBillsDimBo.unitID }"/>&buldID=<c:out value="${TblBillsDimBo.buldID }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/billsDim.do?method=forAddTblBillsDim&billCode=<c:out value="${TblBillsDimBo.billCode }"/>&itemCode=<c:out value="${TblBillsDimBo.itemCode }"/>&roomCode=<c:out value="${TblBillsDimBo.roomCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblBillsDimBo.billCode }"/>','<c:out value="${TblBillsDimBo.itemCode }"/>','<c:out value="${TblBillsDimBo.roomCode }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/billsDim.do?method=viewTblBillsDim&&billCode=<c:out value="${TblBillsDimBo.billCode }"/>&itemCode=<c:out value="${TblBillsDimBo.itemCode }"/>&roomCode=<c:out value="${TblBillsDimBo.roomCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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
