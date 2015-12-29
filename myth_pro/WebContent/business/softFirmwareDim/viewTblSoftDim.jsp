<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<html:form action="/softFirmwareDim.do" enctype="multipart/form-data">
<html:hidden property="method" value="addTblSoftDim" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">固件信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">固件编号：</td>
            <td class="t1"><c:out value="${TblSoftDimForm.softCode}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">固件名称：</td>
            <td class="t1"><c:out value="${TblSoftDimForm.softName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">固件版本号：</td>
            <td class="t1"><c:out value="${TblSoftDimForm.softVision}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">文件名：</td>
            <td class="t1"><c:out value="${TblSoftDimForm.softFn}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">固件大小：</td>
            <td class="t1"><c:out value="${TblSoftDimForm.softSize}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">更新说明：</td>
            <td class="t1"><c:out value="${TblSoftDimForm.updExp}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">设备类型：</td>
            <td class="t1"><c:choose>
            	<c:when test="${TblSoftDimForm.equType=='s'}">室内机</c:when>
            	<c:when test="${TblSoftDimForm.equType=='d'}">门口机</c:when>
            	<c:when test="${TblSoftDimForm.equType=='m'}">管理机</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>
		  </td>
          </tr>
          <tr>
            <td class="t0">状态：</td>
            <td class="t1">
            	<c:choose>
            		<c:when test="${TblSoftDimForm.softState=='0'}">禁用</c:when>
            		<c:when test="${TblSoftDimForm.softState=='1'}">启用</c:when>
            		<c:otherwise></c:otherwise>
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
