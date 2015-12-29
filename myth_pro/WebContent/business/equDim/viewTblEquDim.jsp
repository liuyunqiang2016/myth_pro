<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<jsp:include page="/share/top.jsp" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">设备信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">设备编号：</td>
            <td class="t1"><c:out value="${TblEquDimForm.equCode}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">设备类型：</td>
            <td class="t1">
            <c:choose>
            	<c:when test="${TblEquDimForm.equType=='s'}">室内机</c:when>
            	<c:when test="${TblEquDimForm.equType=='d'}">门口机</c:when>
            	<c:when test="${TblEquDimForm.equType=='m'}">管理机</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>
          </td>
          </tr>
          <tr>
            <td class="t0">门禁编号：</td>
            <td class="t1"><c:out value="${TblEquDimForm.equAccid}"/></td>
          </tr>
          <tr>
            <td class="t0">IP地址：</td>
            <td class="t1"><c:out value="${TblEquDimForm.ipAdd}"/></td>
          </tr>
          <tr>
            <td class="t0">MAC地址：</td>
            <td class="t1"><c:out value="${TblEquDimForm.macAdd}"/></td>
          </tr>
          <tr>
            <td class="t0">在线状态：</td>
            <td class="t1">
            	<c:choose>
            	<c:when test="${TblEquDimForm.equState=='0'}">不在线</c:when>
            	<c:when test="${TblEquDimForm.equState=='1'}">在线</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>
            </td>
          </tr>
           <tr>
            <td class="t0">所属房号：</td>
            <td class="t1">
            	<c:out value="${TblEquDimForm.equAdd}"/>
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
