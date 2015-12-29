<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    function checkFrm()
    {
    	if(checkIsNull('0','roleName')==false){
    		alert("角色名称不为空");
    		return false;
    	}
    	document.forms[0].submit();
    }
</SCRIPT>
<html:form action="/roleDim.do">
<html:hidden property="method" value="addTblRoleDim" />
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
            <th colspan="2" class="th0">角色信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblRoleDimForm.roleCode}">
          <tr>
            <td class="t0">角色编号：</td>
            <td class="t1">
            	<c:out value="${TblRoleDimForm.roleCode}"/>
            	<html:hidden property="roleCode" />
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">角色名称：</td>
            <td class="t1"><html:text property="roleName" maxlength="80"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
        </tbody>
      </table>
      <div class="operbtn">
        <span class="btnspan btnspanc"><input type="button" value="保存" class="btn" onclick="checkFrm()"></span>
        <span class="btnspan btnspanc"><input type="button" value="取消" class="btn" onclick="history.back(-1);"></span>
      </div>
    </div>
    
  </div>
 </div>
 <div class="clear"></div>
</html:form>
<jsp:include page="/share/foot.jsp" />
