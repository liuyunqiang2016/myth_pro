<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">业主信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">业主编号：</td>
            <td class="t1"><c:out value="${TblOwnerDimForm.ownerCode}"/></td>
          </tr>
          <tr>
            <td class="t0">业主名称：</td>
            <td class="t1"><c:out value="${TblOwnerDimForm.ownerName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">业主性别：</td>
            <td class="t1">
            	<c:choose>
            		<c:when test="${TblOwnerDimForm.ownerSex=='0'}">男</c:when>
            		<c:when test="${TblOwnerDimForm.ownerSex=='1'}">女</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
          </td>
          </tr>
          <tr>
            <td class="t0">身份证号码：</td>
            <td class="t1"><c:out value="${TblOwnerDimForm.ownerNo}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">出生日期：</td>
            <td class="t1"><c:out value="${TblOwnerDimForm.ownerDate}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">家庭电话：</td>
            <td class="t1"><c:out value="${TblOwnerDimForm.ownerTel}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">移动电话：</td>
            <td class="t1"><c:out value="${TblOwnerDimForm.ownerMob}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">邮编：</td>
            <td class="t1"><c:out value="${TblOwnerDimForm.ownerPc}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">地址：</td>
            <td class="t1"><c:out value="${TblOwnerDimForm.ownerAdds}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">家属联系方式：</td>
            <td class="t1"><c:out value="${TblOwnerDimForm.ownerRelationNumber}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">房间信息：</td>
            <td class="t1">
				<%-- <c:forEach var="room" items="${roomList}">
				<a href="<%=path%>/roomDim.do?method=viewTblRoomDim&roomCode=<c:out value='${room.roomCode}'/>&unitCode=<c:out value='${room.unitCode}'/>&commCode=<c:out value='${room.commCode}'/>&areaCode=<c:out value='${room.areaCode}'/>&buildingCode=<c:out value='${room.buildingCode}'/>">
					<c:out value="${room.roomCode}"/>
				</a>
				</c:forEach> --%>
				<c:out value="${TblOwnerDimForm.roomInfo}"></c:out>
			</td>
          </tr>
          <tr>
            <td class="t0">门禁卡信息：</td>
            <td class="t1">
				<%-- <c:forEach var="card" items="${cardList}">
					<a href="<%=path%>/cardDim.do?method=viewTblCardDim&cardNo=<c:out value='${card.cardNo}'/>"><c:out value="${card.cardNo}"/></a>
				</c:forEach> --%>
				<c:out value="${TblOwnerDimForm.cardId}"></c:out>
			</td>
          </tr>
          <tr>
            <td class="t0">指纹信息：</td>
            <td class="t1">
				<%-- <c:forEach var="fing" items="${fingList}">
					<a href="<%=path%>/fingDim.do?method=viewTblFingDim&usrNo=<c:out value='${fing.usrNo}'/>"><c:out value="${fing.usrNo}"/></a>
				</c:forEach> --%>
				<c:out value="${TblOwnerDimForm.fingId}"></c:out>
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
<jsp:include page="/share/foot.jsp" />
