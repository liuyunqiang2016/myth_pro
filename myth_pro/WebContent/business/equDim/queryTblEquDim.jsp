<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName, equCode)
    	{
    		if(window.confirm('确实要删除该设备信息？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/equDim.do?method=removeTblEquDim&equCode=" + equCode;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/equDim.do">
<html:hidden property="method" value="queryTblEquDim" />
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
<!--            <th class="t5">MAC地址</th>-->
            <th class="t11" colspan="2">设备地址</th>
            <th class="t6">在线状态</th>
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
<!--            <td class="t5"><c:out value="${TblEquDimBo.macAdd}" /></td>-->
            <td colspan="2" class="t11" onclick="obtinAddressInfo('<c:out value="${TblEquDimBo.equAdd}"/>','<c:out value="${TblEquDimBo.areaID}"/>','<c:out value="${TblEquDimBo.commID}"/>','<c:out value="${TblEquDimBo.unitID}"/>','<c:out value="${TblEquDimBo.buldID}"/>', this)"></td>
            <td class="t6">
            <c:choose>
            	<c:when test="${TblEquDimBo.equState=='0'}">不在线</c:when>
            	<c:when test="${TblEquDimBo.equState=='1'}">在线</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>
            </td>
            <td class="t7">
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/equDim.do?method=viewTblEquDim&equCode=<c:out value="${TblEquDimBo.equCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblEquDimBo.equCode }"/>');">
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
		$.getJSON("<%=path%>/roomDim.do?method=getJsonRoomInfo",{ roomCode:roomCodeP, areaCode:areaCodeP, commCode:commCodeP, unitCode:unitCodeP, buildingCode:buildingCodeP }, function(json){
		  if(json != null) $(object).html(json.roominfo) ;
		  
		});
	}
</SCRIPT>
