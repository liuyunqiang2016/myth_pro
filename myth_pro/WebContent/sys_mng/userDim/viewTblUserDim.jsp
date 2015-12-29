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
            <th colspan="2" class="th0">用户信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">用户编号：</td>
            <td class="t1"><c:out value="${TblUserDimForm.userCode}" /></td>
          </tr>
          <tr>
            <td class="t0">登录名：</td>
            <td class="t1"><c:out value="${TblUserDimForm.logName}" /></td>
          </tr>
          <tr>
            <td class="t0">登录密码：</td>
            <td class="t1"><c:out value="${TblUserDimForm.logPwd}" /></td>
          </tr>
          <tr>
            <td class="t0">用户名称：</td>
            <td class="t1"><c:out value="${TblUserDimForm.userName}" /></td>
          </tr>
          <tr>
            <td class="t0">用户状态：</td>
            <td class="t1">
            	<c:choose>
            		<c:when test="${TblUserDimForm.userState=='0'}">禁用</c:when>
            		<c:when test="${TblUserDimForm.userState=='1'}">启用用</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            </td>
          </tr>
          <tr>
            <td class="t0">所属角色：</td>
            <td class="t1">
            	<c:forEach var="role" items="${roleList}">
            		<c:if test="${role.roleCode==TblUserDimForm.roleCode}">
            			<c:out value="${role.roleName}"/>
            		</c:if>
            	</c:forEach>
            </td>
          </tr>
          <tr>
            <td class="t0">所属部门：</td>
            <td class="t1"><c:out value="${TblUserDimForm.deptName}" /></td>
          </tr>
          <tr>
            <td class="t0">所属机构：</td>
            <td class="t1"><c:out value="${TblUserDimForm.orgCode}" /></td>
          </tr>
          <tr>
            <td class="t0">邮箱地址：</td>
            <td class="t1"><c:out value="${TblUserDimForm.emailAddress}" /></td>
          </tr>
          <tr>
            <td class="t0">联系电话：</td>
            <td class="t1"><c:out value="${TblUserDimForm.telNum}" /></td>
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
