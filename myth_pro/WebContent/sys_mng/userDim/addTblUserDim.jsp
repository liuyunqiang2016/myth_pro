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
    	if(checkIsNull('0','logName')==false){
    		alert("登录账号不为空");
    		return false;
    	}
    	if(checkIsNull('0','userName')==false){
    		alert("用户名称不为空");
    		return false;
    	}
    	if(checkIsNull('0','logPwd')==false){
    		alert("用户密码不为空");
    		return false;
    	}
    	if(checkIsNull('0','roleCode')==false){
    		alert("角色不为空");
    		return false;
    	}else if ("3602849065226" == $('#roleCode option:selected').val()){
    		if(checkIsNull('0','propertyId')==false){
        		alert("角色所属物业公司不为空");
        		return false;
        	}
    	}
    	if(checkIsNull('0','emailAddress')==false){
    		alert("邮箱地址不为空");
    		return false;
    	}
    	if(checkIsNull('0','telNum')==false){
    		alert("联系电话不为空");
    		return false;
    	}
    	document.forms[0].submit();
    }
    function showPro()
    {
    	if ("3602849065226" == $('#roleCode option:selected').val()){
    		document.getElementById("propertyId").style.display = "block";
    	}
    }
</SCRIPT>
<html:form action="/userDim.do">
<html:hidden property="method" value="addTblUserDim" />
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
            <th colspan="2" class="th0">用户信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblUserDimForm.userCode}">
          <tr>
            <td class="t0">用户编号：</td>
            <td class="t1">
            	<c:out value="${TblUserDimForm.userCode}"/>
            	<html:hidden property="userCode" />
            	<html:hidden property="logPwd"/>
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">登录账号：</td>
            <td class="t1"><html:text property="logName" maxlength="80"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">用户名称：</td>
            <td class="t1"><html:text property="userName" maxlength="100"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">用户密码：</td>
            <td class="t1"><html:text property="logPwd" maxlength="100"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">用户状态：</td>
            <td class="t1">
            <html:select property="userState">
            	<html:option value="1">启用</html:option>
            	<html:option value="0">禁用</html:option>
            </html:select>
            </td>
          </tr>
          <tr>
            <td class="t0">所属角色：</td>
            <td class="t1">
            	<select id="roleCode" name="roleCode" onchange="showPro();">
            		<option value="">请选择角色</option>
            		<c:forEach var="role" items="${roleList}">
            			<option value='<c:out value="${role.roleCode}"/>'
            			 <c:if test="${role.roleCode== TblUserDimForm.roleCode }"> selected="selected"</c:if>>
            				<c:out value="${role.roleName}"/>
            			</option>
            		</c:forEach>
            	</select><span style="color: red;"><strong>*</strong></span>
            	<select id="propertyId" name="propertyId" style="display:none;">
            		<option value="">请选择管理员所属物业公司</option>
            		<c:forEach var="pro" items="${propertyList}">
            			<option value='<c:out value="${pro.propertyId}"/>'>
            				<c:out value="${pro.propertyName}"/>
            			</option>
            		</c:forEach>
            	</select><span style="color: red;display:none;"><strong>*</strong></span>
           	</td>
          </tr>
          <tr>
            <td class="t0">所属部门：</td>
            <td class="t1"><html:text property="deptName" maxlength="100"></html:text></td>
          </tr>
          <tr>
            <td class="t0">所属机构：</td>
            <td class="t1"><html:text property="orgCode" maxlength="100"></html:text></td>
          </tr>
          <tr>
            <td class="t0">邮箱地址：</td>
            <td class="t1"><html:text property="emailAddress" maxlength="100"></html:text>
            <span style="color: red;"><strong>*忘记密码找回重要信息</strong></span></td>
            
          </tr>
          <tr>
            <td class="t0">联系电话：</td>
            <td class="t1"><html:text property="telNum" maxlength="100"></html:text>
            <span style="color: red;"><strong>*忘记密码找回重要信息</strong></span></td>
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
