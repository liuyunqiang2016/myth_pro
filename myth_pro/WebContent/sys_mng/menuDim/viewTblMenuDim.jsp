<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<html:form action="/softDim.do" enctype="multipart/form-data">
<html:hidden property="method" value="addTblSoftDim" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">菜单信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">菜单编号：</td>
            <td class="t1"><c:out value="${TblMenuDimForm.menuCode}"/></td>
          </tr>
          <tr>
            <td class="t0">菜单名称：</td>
            <td class="t1"><c:out value="${TblMenuDimForm.menuName}"/></td>
          </tr>
          <tr>
            <td class="t0">菜单级别：</td>
            <td class="t1">
            	<c:choose>
            		<c:when test="${TblMenuDimForm.menuLev=='1'}">一级菜单</c:when>
            		<c:when test="${TblMenuDimForm.menuLev=='2'}">二级菜单</c:when>
            	</c:choose>
            </td>
          </tr>
          <tr>
            <td class="t0">上级菜单：</td>
            <td class="t1">
            	<c:forEach var="menu" items="${menuList}">
            		<c:if test="${menu.menuCode==TblMenuDimForm.menuPar}"><c:out value="${menu.menuName}"/></c:if>
            	</c:forEach>
            </td>
          </tr>
          <tr>
            <td class="t0">菜单地址：</td>
            <td class="t1"><c:out value="${TblMenuDimForm.menuUrl}"/></td>
          </tr>
          <tr>
            <td class="t0">显示状态：</td>
            <td class="t1">
            	<c:choose>
            		<c:when test="${TblMenuDimForm.menuView=='0'}">不显示</c:when>
            		<c:when test="${TblMenuDimForm.menuView=='1'}">显示</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            </td>
          </tr>
          <tr>
            <td class="t0">菜单说明：</td>
            <td class="t1"><c:out value="${TblMenuDimForm.menuExp}"/></td>
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
