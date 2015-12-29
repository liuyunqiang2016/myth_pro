<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<html:form action="/itemDim.do" enctype="multipart/form-data">
<html:hidden property="method" value="addTblItemDim" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">缴费单据信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">缴费单据编号：</td>
            <td class="t1"><c:out value="${TblBillsDimForm.billCode}"/></td>
          </tr>
          <tr>
            <td class="t0">缴费项目编号：</td>
            <td class="t1">
            	<c:forEach var="item" items="${itemList}">
            		<c:if test="${item.itemCode==TblBillsDimForm.itemCode}">
            			<c:out value="${item.itemName}"/>
            		</c:if>
            	</c:forEach>
            </td>
          </tr>
          <tr>
            <td class="t0">缴费房号：</td>
            <td class="t1"><c:out value="${TblBillsDimForm.roomCode}"/></td>
          </tr>
          <tr>
            <td class="t0">费用：</td>
            <td class="t1"><c:out value="${TblBillsDimForm.billAmt}"/></td>
          </tr>
          <tr>
            <td class="t0">缴费时间：</td>
            <td class="t1"><c:out value="${TblBillsDimForm.jfDate}"/></td>
          </tr>
          <tr>
            <td class="t0">缴费截止时间：</td>
            <td class="t1"><c:out value="${TblBillsDimForm.jzDate}"/></td>
          </tr>
          <tr>
            <td class="t0">是否缴费：</td>
            <td class="t1">
            <c:choose>
            	<c:when test="${TblBillsDimForm.isPay=='0'}">未缴</c:when>
            	<c:when test="${TblBillsDimForm.isPay=='1'}">已缴</c:when>
            </c:choose>
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
