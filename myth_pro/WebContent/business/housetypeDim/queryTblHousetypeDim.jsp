<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName, htCode)
    	{
    		<c:if test="${needs_authorization eq true}">
    		if(checkIsNull('0','advancedUserName')==false)
    		{
    			alert('复核人用户名不能为空！');
    			return false;
    		}
    		if(checkIsNull('0','advancedPassword')==false)
    		{
    			alert('复核人密码密码不能为空！');
    			return false;
    		}
    		</c:if>
    		if(window.confirm('确实要删除该户型？')){
    			var form = document.forms[formName];
    			form.action="/housetypeDim.do?method=removeTblHousetypeDim&htCode=" + htCode;
    			form.submit();
    		}    			
    	}
</SCRIPT>
<html:form action="/housetypeDim.do">
<html:hidden property="method" value="queryTblHousetypeDim" />
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">户型编号</th>
            <th class="t2">户型名称</th>
            <th class="t3">房间数</th>
            <th class="t4">厅数</th>
            <th class="t5">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblHousetypeDimBo" items="${tblHousetypeDims.list}" varStatus="e">
          <tr>
            <td class="t1"><c:out value="${TblHousetypeDimBo.htCode}" /></td>
            <td class="t2"><c:out value="${TblHousetypeDimBo.htName}" /></td>
            <td class="t3"><c:out value="${TblHousetypeDimBo.roCount}" /></td>
            <td class="t4"><c:out value="${TblHousetypeDimBo.toCount}" /></td>
            <td class="t5">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/housetypeDim.do?method=forAddTblHousetypeDim&htCode=<c:out value="${TblHousetypeDimBo.htCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblHousetypeDimBo.htCode }"/>');">
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
