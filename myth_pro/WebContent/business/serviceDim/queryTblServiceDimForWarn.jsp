<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName, serCode)
    	{
    		if(window.confirm('确实要删除该服务信息？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/serviceDim.do?method=removeTblServiceDim&serCode=" + serCode;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/serviceDim.do">
<html:hidden property="method" value="queryTblServiceDimForWarn" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">发布人<input type="text" name="strCreateUsr" class="searchtxt" value="<c:out value='${strCreateUsr }'/>" /></span>
    <span class="searchbox">信息标题<input type="text" name="strTitile" class="searchtxt" value="<c:out value='${strTitile }'/>" /></span>
    <span class="searchbox">处理人<input type="text" name="strChkUsr" class="searchtxt" value="<c:out value='${strChkUsr }'/>" /></span>
    <!-- span class="searchbox">信息类型
    	<select name="strInfoType">
    		<option value="">请选择信息类型</option>
    		<option value="S001" <c:if test="${strInfoType=='S001' }"> selected="selected"</c:if>>报警信息</option>
    		<option value="S002" <c:if test="${strInfoType=='S002' }"> selected="selected"</c:if>>报修信息</option>
    	</select>
    </span> -->
    <span class="searchbox">处理状态
    	<select name="strChkState">
    		<option value="">请选择处理状态</option>
    		<option value="01" <c:if test="${strChkState=='01' }"> selected="selected"</c:if>>已处理</option>
    		<option value="02" <c:if test="${strChkState=='02' }"> selected="selected"</c:if>>未处理</option>
    	</select>
    </span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">信息编号</th>
            <th class="t2">信息标题</th>
            <th class="t3">信息类型</th>
            <th class="t4">发布人</th>
            <th class="t5">发布时间</th>
            <th class="t6">处理状态</th>
            <th class="t8">处理人</th>
            <th class="t9">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblServiceDimBo" items="${tblServiceDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblServiceDimBo.serId}" /></td>
            <td class="t2"><c:out value="${TblServiceDimBo.titile}" /></td>
            <td class="t3"><c:choose>
				<c:when test="${TblServiceDimBo.type =='S001'}">报警</c:when>
				<c:when test="${TblServiceDimBo.type =='S002'}">报修</c:when>
				<c:when test="${TblServiceDimBo.type =='S003'}">其他服务</c:when>
				<c:otherwise>其他</c:otherwise>
				</c:choose></td>
			<td class="t4"><c:out value="${TblServiceDimBo.createUsr}" /></td>
			<td class="t5"><c:out value="${TblServiceDimBo.createTime}" /></td>
			<td class="t6">
				<c:choose>
					<c:when test="${TblServiceDimBo.chkState=='01'}">已处理</c:when>
					<c:when test="${TblServiceDimBo.chkState=='02'}">未处理</c:when>
					<c:otherwise>未处理</c:otherwise>
				</c:choose>
			</td>
			<td class="t8"><c:out value="${TblServiceDimBo.chkUsr}" /></td>
            <td class="t9">
            <c:if test="${TblServiceDimBo.chkState!='01'}">
            <span class="btnoperal"><input type="button" value="处理" class="btn"
            	onclick="document.location.href='<%=path%>/serviceDim.do?method=forAddTblServiceDim&serCode=<c:out value="${TblServiceDimBo.serCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            </c:if>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblServiceDimBo.serCode }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/serviceDim.do?method=viewTblServiceDim&serCode=<c:out value="${TblServiceDimBo.serCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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
