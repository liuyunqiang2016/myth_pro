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
<html:form action="/innerEquOp.do">
<html:hidden property="method" value="queryOuterEquDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">设备编号<input type="text" name="strEquCode" class="searchtxt" value="<c:out value='${strEquCode }'/>" /></span>
    <span class="searchbox">IP地址<input type="text" name="strIpAdd" class="searchtxt" value="<c:out value='${strIpAdd }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t4">设备编号</th>
            <th class="t3">设备类型</th>
            <th class="t4">IP地址</th>
            <th class="t6">所属房号</th>
            <th class="t7">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblEquDimBo" items="${tblEquDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t4"><c:out value="${TblEquDimBo.equCode}" /></td>
            <td class="t3">
            <c:choose>
            	<c:when test="${TblEquDimBo.equType=='s'}">室内机</c:when>
            	<c:when test="${TblEquDimBo.equType=='d'}">门口机</c:when>
            	<c:when test="${TblEquDimBo.equType=='m'}">管理机</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>
            </td>
            <td class="t4"><c:out value="${TblEquDimBo.ipAdd}" /></td>
            <td class="t6">
            	<c:forEach var="room" items="${roomList}">
            		<c:if test="${room.roomCode==TblEquDimBo.equAdd
            				and room.unitCode == TblEquDimBo.unitID
            				and room.areaCode == TblEquDimBo.areaID
            				and room.commCode == TblEquDimBo.commID
            				and room.buildingCode == TblEquDimBo.buldID}"> 
            			<c:out value="${room.commName}"></c:out>小区
            			<c:out value="${room.areaName}"></c:out>区
            			<c:out value="${room.buildingName}"></c:out>楼宇<br/>
            			<c:out value="${room.unitName}"></c:out>单元
            			<c:out value="${room.roomCode}"></c:out>号
            		</c:if>
            	</c:forEach>
            </td>
            <td class="t7">
            <span class="btnoperal"><input type="button" value="分配权限" class="btn"
            	onclick="document.location.href='<%=path%>/innerEquOp.do?method=queryInnerEquDim&outerEquCode=<c:out value="${TblEquDimBo.equCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="取消权限" class="btn"
            	onclick="document.location.href='<%=path%>/innerEquOp.do?method=queryAssignInnerEquDim&outerEquCode=<c:out value="${TblEquDimBo.equCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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

<!-- 显示房号 -->
<SCRIPT language="javascript" type="text/javascript">	
	$(document).ready(function(){
		$(".t11").each(function(){
			$(this).trigger("click") ;
		});
	}) ;

	function obtinAddressInfo(roomCodeP, areaCodeP,commCodeP, unitCodeP,  buildingCodeP, object){
		$.getJSON("<%=path%>/oomDim.do?method=getJsonRoomInfo",{ roomCode:roomCodeP, areaCode:areaCodeP, commCode:commCodeP, unitCode:unitCodeP, buildingCode:buildingCodeP }, function(json){
		  if(json != null) $(object).html(json.roominfo) ;
		  
		});
	}
</SCRIPT>
