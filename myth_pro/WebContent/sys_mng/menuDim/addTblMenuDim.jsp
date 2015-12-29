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
    	if(checkIsNull('0','menuName')==false){
    		alert("菜单名称不为空");
    		return false;
    	}
    	if(document.getElementById("menuLev").value=="2"){
			if(document.getElementById("menuPar").value=='0'){
    			alert("上级菜单不为空");
    			return false;
    		}	
    	}
    	document.forms[0].submit();
    }
</SCRIPT>
<html:form action="/menuDim.do">
<html:hidden property="method" value="addTblMenuDim" />
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
            <th colspan="2" class="th0">菜单信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblMenuDimForm.menuCode}">
          <tr>
            <td class="t0">菜单编号：</td>
            <td class="t1">
            	<c:out value="${TblMenuDimForm.menuCode}"/>
            	<html:hidden property="menuCode" />
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">菜单名称：</td>
            <td class="t1"><html:text property="menuName" maxlength="80"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">菜单级别：</td>
            <td class="t1">
            <select name="menuLev" id="menuLev">
            	<option value='<c:out value="${TblMenuDimForm.menuLev}"/>'>
            	<c:choose>
            		<c:when test="${TblMenuDimForm.menuLev=='1'}">一级菜单</c:when>
            		<c:when test="${TblMenuDimForm.menuLev=='2'}">二级菜单</c:when>
            	</c:choose>
            	</option>
            	<option value="1">一级菜单</option>
            	<option value="2">二级菜单</option>
            </select>
            </td>
          </tr>
          <tr>
            <td class="t0">上级菜单：</td>
            <td class="t1">
            <select name="menuPar" id="menuPar">
            <option value="0">请选择上级菜单</option>
            <c:forEach var="menu" items="${TblMenuDimForm.parMenu}">
            	<option value='<c:out value="${menu.menuCode}"/>' 
            		<c:if test="${menu.menuCode==TblMenuDimForm.menuPar and TblMenuDimForm.menuLev=='2' }"> selected="selected"</c:if>>
            	<c:out value="${menu.menuName}"/></option>
            </c:forEach>
            </select>
            </td>
          </tr>
          <tr>
            <td class="t0">菜单地址：</td>
            <td class="t1"><html:text property="menuUrl" maxlength="100" size="80"></html:text></td>
          </tr>
          <tr>
            <td class="t0">显示状态：</td>
            <td class="t1">
            	<html:select property="menuView">
            		<html:option value="1">显示</html:option>
            		<html:option value="0">不显示</html:option>
            	</html:select>
            </td>
          </tr>
          <tr>
            <td class="t0">菜单说明：</td>
            <td class="t1"><html:text property="menuExp" maxlength="100" size="80"></html:text></td>
          </tr>
        </tbody>
      </table>
      <div class="operbtn">
        <span class="btnspan btnspanc"><input type="button" value="保存" class="btn" onclick="checkFrm();"></span>
        <span class="btnspan btnspanc"><input type="button" value="取消" class="btn" onclick="history.back(-1);"></span>
      </div>
    </div>
    
  </div>
 </div>
 <div class="clear"></div>
</html:form>
<jsp:include page="/share/foot.jsp" />
