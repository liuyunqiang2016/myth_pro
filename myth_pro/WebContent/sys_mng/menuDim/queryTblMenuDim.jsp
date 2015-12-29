<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName, MenuCode)
    	{
    		if(window.confirm('确实要删除该菜单信息？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/menuDim.do?method=removeTblMenuDim&menuCode=" + MenuCode;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/menuDim.do">
<html:hidden property="method" value="queryTblMenuDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">菜单名称<input type="text" name="strMenuName" class="searchtxt" value="<c:out value='${strMenuName }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">菜单编号</th>
            <th class="t2">菜单名称</th>
            <th class="t3">上级菜单</th>
            <th class="t4">菜单级别</th>
            <th class="t5">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblMenuDimBo" items="${tblMenuDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblMenuDimBo.menuCode}" /></td>
            <td class="t2"><c:out value="${TblMenuDimBo.menuName}" /></td>
            <td class="t3">
            	<c:forEach var="menu" items="${menuList}">
            		<c:if test="${menu.menuCode==TblMenuDimBo.menuPar}"><c:out value="${menu.menuName}"/></c:if>
            	</c:forEach>
            </td>
            <td class="t3">
            	<c:choose>
            		<c:when test="${TblMenuDimBo.menuLev=='1'}">一级菜单</c:when>
            		<c:when test="${TblMenuDimBo.menuLev=='2'}">二级菜单</c:when>
            	</c:choose>
            </td>
            <td class="t4">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/menuDim.do?method=forAddTblMenuDim&menuCode=<c:out value="${TblMenuDimBo.menuCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblMenuDimBo.menuCode }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/menuDim.do?method=viewTblMenuDim&menuCode=<c:out value="${TblMenuDimBo.menuCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            </td>
          </tr>
        </c:forEach>
     </tbody>
    </table>
   </div>   
   <div class="page"><c:out value="${pr}" escapeXml="false" /></div>
  </div>
 </div>
 <div class="clear"></div>
</div>
</html:form>
<jsp:include page="/share/foot.jsp" />
