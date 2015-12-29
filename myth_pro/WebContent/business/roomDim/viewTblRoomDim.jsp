<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<html:form action="/roomDim.do">
<html:hidden property="method" value="addTblRoomDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">房号信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">所属小区：</td>
            <td class="t1"><c:out value="${TblRoomDimForm.commName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">所属区域：</td>
            <td class="t1"><c:out value="${TblRoomDimForm.areaName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">所属楼宇：</td>
            <td class="t1"><c:out value="${TblRoomDimForm.buildingName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">所属单元：</td>
            <td class="t1"><c:out value="${TblRoomDimForm.unitName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">房号：</td>
            <td class="t1"><c:out value="${TblRoomDimForm.roomCode}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">业主：</td>
            <td class="t1">
            	<c:forEach var="owner" items="${ownlist}">
					<c:if test="${owner.ownerCode==TblRoomDimForm.ownerCode }">
						<c:out value="${owner.ownerName}"></c:out>
					</c:if>            				
            	</c:forEach>
            </td>
          </tr>
          <tr>
            <td class="t0">面积：</td>
            <td class="t1"><c:out value="${TblRoomDimForm.roomArea}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">用途：</td>
            <td class="t1">
            <c:choose>
            	<c:when test="${TblRoomDimForm.roomUsr=='01'}">出租</c:when>
            	<c:when test="${TblRoomDimForm.roomUsr=='02'}">自住</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>
            </td>
          </tr>
          <tr>
            <td class="t0">状态：</td>
            <td class="t1">
            <c:choose>
            	<c:when test="${TblRoomDimForm.roomState=='01'}">出租</c:when>
            	<c:when test="${TblRoomDimForm.roomState=='02'}">自住</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>
           </td>
          </tr>
          <tr>
            <td class="t0">钥匙状态：</td>
            <td class="t1">
            <c:choose>
            	<c:when test="${TblRoomDimForm.keyState=='01'}">使用</c:when>
            	<c:when test="${TblRoomDimForm.keyState=='02'}">挂失</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>
           </td>
          </tr>
           <tr>
            <td class="t0">户型：</td>
            <td class="t1">
				<c:forEach var="ht" items="${htlist}">
					<c:if test="${TblRoomDimForm.htCode==ht.htCode }"> 
						<c:out value="${ht.htName}"/>
					</c:if>
				</c:forEach>
			</td>
          </tr>
          <tr>
            <td class="t0">家属联系方式：</td>
            <td class="t1">
				<c:forEach var="owner" items="${ownlist}">
					<c:if test="${owner.ownerCode==TblRoomDimForm.ownerCode }">
						<c:out value="${owner.ownerRelationNumber}"></c:out>
					</c:if>            				
            	</c:forEach>
			</td>
          </tr>
        </tbody>
      </table>
      <div class="operbtn">
        <span class="btnspan btnspanc"><input type="button" value="返回" class="btn" onclick="history.back(-1);"></span>
      </div>
    </div>
    
  </div>
 </div>
 <div class="clear"></div>
</div>
</html:form>
<jsp:include page="/share/foot.jsp" />
