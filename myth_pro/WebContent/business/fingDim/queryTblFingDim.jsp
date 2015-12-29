<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName, usrNo)
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
    		if(window.confirm('确实要删除该指纹门禁信息？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/fingDim.do?method=removeTblFingDim&usrNo=" + usrNo;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/fingDim.do">
<html:hidden property="method" value="queryTblFingDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">姓名<input type="text" name="strFingUsr" class="searchtxt" value="<c:out value='${strFingUsr }'/>" /></span>
    <span class="searchbox">证件号码<input type="text" name="strUserNo" class="searchtxt" value="<c:out value='${strUserNo }'/>" /></span>
    <span class="searchbox">指纹状态
    <select name="strFingState">
    	<option value="">请选择指纹状态</option>
    	<option value='01' <c:if test="${'01'==strFingState }"> selected="selected"</c:if>>在用</option>
    	<option value='02' <c:if test="${'02'==strFingState }"> selected="selected"</c:if>>停用</option>
    </select>
    </span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">证件号码</th>
            <th class="t2">姓名</th>
            <th class="t3">房号</th>
            <th class="t4">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblFingDimBo" items="${tblFingDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblFingDimBo.usrNo}" /></td>
            <td class="t2"><c:out value="${TblFingDimBo.fingUsr}" /></td>
            <td class="t3"><c:out value="${TblFingDimBo.roomCode}" /></td>
            <td class="t4">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/fingDim.do?method=forAddTblFingDim&usrNo=<c:out value="${TblFingDimBo.usrNo }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblFingDimBo.usrNo }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/fingDim.do?method=viewTblFingDim&usrNo=<c:out value="${TblFingDimBo.usrNo }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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
