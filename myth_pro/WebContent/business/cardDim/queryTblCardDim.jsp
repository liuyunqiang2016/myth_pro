<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName, cardNo)
    	{
    		if(window.confirm('确实要删除该门禁卡信息？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/cardDim.do?method=removeTblCardDim&cardNo=" + cardNo;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/cardDim.do">
<html:hidden property="method" value="queryTblCardDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">姓名<input type="text" name="strUsrName" class="searchtxt" value="<c:out value='${strUsrName }'/>" /></span>
    <span class="searchbox">身份证<input type="text" name="strUserNo" class="searchtxt" value="<c:out value='${strUserNo }'/>" /></span>
    <span class="searchbox">卡状态
    <select name="strState">
    	<option value="">请选择卡状态</option>
    	<option value='01' <c:if test="${'01'==strState }"> selected="selected"</c:if>>在用</option>
    	<option value='02' <c:if test="${'02'==strState }"> selected="selected"</c:if>>停用</option>
    </select>
    </span>
    <span class="searchbox">卡号<input type="text" name="strCardNo" class="searchtxt" value="<c:out value='${strCardNo }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">门禁卡编号</th>
            <th class="t2">业主名称</th>
            <th class="t3">身份证</th>
            <th class="t4">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblCardDimBo" items="${tblCardDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblCardDimBo.cardNo}" /></td>
            <td class="t2"><c:out value="${TblCardDimBo.usrName}" /></td>
            <td class="t3"><c:out value="${TblCardDimBo.userNo}" /></td>
            <td class="t4">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/cardDim.do?method=forAddTblCardDim&cardNo=<c:out value="${TblCardDimBo.cardNo }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblCardDimBo.cardNo }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/cardDim.do?method=viewTblCardDim&cardNo=<c:out value="${TblCardDimBo.cardNo }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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
