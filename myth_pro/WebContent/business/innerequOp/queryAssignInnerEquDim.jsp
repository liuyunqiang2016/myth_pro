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
	function assignOnclick(){
		var outid = $("input[name='outerEquCode']").val();
		var equcheck = document.getElementsByName("equcheck");
		var equlist="";
		var equflag=0;
		for(var i=0;i<equcheck.length;i++){
			if(equcheck[i].checked){
				equlist = equlist +equcheck[i].value+",";
				equflag = equflag+1;
			}
		}
		if(equflag==0){
			alert("请选择室内机");
			return false;
		}
		var form = document.forms[0];
    	form.action="<%=path%>/innerEquOp.do?method=removeInnerEquDim&outerId=" + outid+"&equlist="+equlist;
    	form.submit();
	}
</SCRIPT>
<html:form action="/innerEquOp.do">
<html:hidden property="method" value="queryAssignInnerEquDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
	String outerEquCode =(String)request.getParameter("outerEquCode");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<html:hidden property="outerEquCode" value="<%=outerEquCode %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">设备编号<input type="text" name="strEquCode" class="searchtxt" value="<c:out value='${strEquCode }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    <span class="btnspan"><span class="pic"></span>
    <input type="button" class="btn" value="取消权限" onclick="assignOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
        	<th class="t2"><input type="checkbox" id="allcheck" name="allcheck"/></th>
            <th class="t4">设备编号</th>
            <th class="t3">设备类型</th>
            <th class="t4">IP地址</th>
            <th class="t6">所属房号</th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblEquDimBo" items="${tblEquDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
          	<td class="t2"><input type="checkbox" id="equcheck" name="equcheck" value='<c:out value="${TblEquDimBo.equCode}" />'/></td>
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