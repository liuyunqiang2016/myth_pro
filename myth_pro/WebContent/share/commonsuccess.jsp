<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            <th colspan="2" class="th0">成功信息</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">操作成功：</td>
            <td class="t1"><c:out value="${message}"></c:out></td>
          </tr>
        </tbody>
      </table>
      <div class="operbtn">
        <span class="btnspan btnspanc"><input type="button" value="返回" class="btn" onclick="document.location.href='<%=path%><c:out value="${backurl }" />';"></span>
      </div>
    </div>
    
  </div>
 </div>
 <div class="clear"></div>
<jsp:include page="/share/foot.jsp" />
