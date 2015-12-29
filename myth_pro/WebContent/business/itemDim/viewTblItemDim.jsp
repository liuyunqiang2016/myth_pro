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
            <th colspan="2" class="th0">缴费项目信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">缴费项目编号：</td>
            <td class="t1"><c:out value="${TblItemDimForm.itemCode}"/></td>
          </tr>
          <tr>
            <td class="t0">缴费项目名称：</td>
            <td class="t1"><c:out value="${TblItemDimForm.itemName}"/></td>
          </tr>
           <tr>
            <td class="t0">计算方式：</td>
            <td class="t1">
            	<c:forEach var="cal" items="${calList}">
            		<c:if test="${cal.calCode==TblItemDimForm.calCode}">
            			<c:out value="${cal.calName}"/>
            		</c:if>
            	</c:forEach>
            </td>
          </tr>
           <tr>
            <td class="t0">收费标准：</td>
            <td class="t1"><c:out value="${TblItemDimForm.sfLev}"/></td>
          </tr>
           <tr>
            <td class="t0">收费说明：</td>
            <td class="t1"><c:out value="${TblItemDimForm.sfExp}"/></td>
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
