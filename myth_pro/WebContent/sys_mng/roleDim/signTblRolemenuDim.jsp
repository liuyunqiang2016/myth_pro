<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:directive.page import="com.viatt.zhjtpro.bo.*" />
<jsp:directive.page import="com.viatt.zhjtpro.forms.*" />
<jsp:directive.page import="java.util.List" />
<%@ page import="com.viatt.zhjtpro.common.Page"%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    function checkFrm()
    {
    	document.forms[0].submit();
    }
    function checkbox1(subBoxName, ischecked){
    	var subBoxes = document.getElementsByName(subBoxName);
    	for(var i = 0; i < subBoxes.length; i++){
    		subBoxes[i].checked = ischecked;
    	}
    }
    function checkbox2(subBoxName, boxValueNeedChecked,ischecked){
    	var subBoxes = document.getElementsByName(subBoxName);
    	for(var i = 0; i < subBoxes.length; i++){
    		if(subBoxes[i].className == "tt0"){
    			continue ;
    		}
    		if(subBoxes[i].value == boxValueNeedChecked){
    			subBoxes[i].checked = ischecked;
    		}
    	}
    }
</SCRIPT>
<html:form action="/roleDim.do">
<html:hidden property="method" value="saveTblRolemenuDim" />
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
            <th colspan="3" class="th0">角色信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">角色名称：</td>
            <td class="t1" colspan="2"><c:out value="${role.roleName}"/></td>
            <input type="hidden" name="roleCode" value="<c:out value='${role.roleCode}'/>"/>
          </tr>
          <c:forEach var="menu" items="${menu}">
          	<tr>
          		<td class="t0">
          			<input type="checkbox"  class="tt0" name="<c:out value='${menu.menuCode }'/>" 
          				value="<c:out value='${menu.menuCode }'/>"
          				onclick="checkbox1(<c:out value='${menu.menuCode }'/>, this.checked);"
          				<%
          					List rolemenu = (List)request.getAttribute("roleMenu");
          					TblMenuDimForm menu2 = (TblMenuDimForm)pageContext.getAttribute("menu");
          					for(int i=0;i<rolemenu.size();i++){
          						TblRolemenuDimBo bo = (TblRolemenuDimBo)rolemenu.get(i);
          						if(bo.getMenuCode().equals(menu2.getMenuCode())){
          						%>
									checked="checked"
								<%
									break;
          						}
          					}
          				%>
          				>
          		</td>
          		<td class="t1" colspan="2"><c:out value="${menu.menuName}"/></td>
          	</tr>
          	 <!-- 下级菜单 -->
          	<c:forEach var="menuchild" items="${menu.parMenu}">
          	<tr>
          		<td class="t0"></td>
          		<td width="10%">
          			<input type="checkbox" name="<c:out value='${menuchild.menuPar }'/>" 
          				value="<c:out value='${menuchild.menuCode }'/>"
          				onclick="checkbox2(<c:out value='${menuchild.menuPar }'/>, <c:out value='${menuchild.menuPar }'/>, this.checked);"
          				<%
          					List rolemenu1 = (List)request.getAttribute("roleMenu");
          					TblMenuDimBo menu3 = (TblMenuDimBo)pageContext.getAttribute("menuchild");
          					for(int i=0;i<rolemenu1.size();i++){
          						TblRolemenuDimBo bo1 = (TblRolemenuDimBo)rolemenu1.get(i);
          						if(bo1.getMenuCode().equals(menu3.getMenuCode())){
          						%>
									checked="checked"
								<%
									break;
          						}
          					}
          				%>
          				>
          		</td>
          		<td width="80%"><c:out value="${menuchild.menuName}"/></td>
          	</tr>
          	</c:forEach>
          </c:forEach>
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
</div>
</html:form>
<jsp:include page="/share/foot.jsp" />
