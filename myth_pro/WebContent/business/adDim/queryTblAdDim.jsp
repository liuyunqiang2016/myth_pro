<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<jsp:include page="/share/top.jsp" />
<%
String path = request.getContextPath();
	String strPraId ="";
	String strChlId = "";
	strPraId = (String)request.getParameter("menuPare");
	strChlId = (String)request.getParameter("menuChild");
 %>
<SCRIPT language="javascript">
    	function linkOnClick(formName, adId)
    	{
    		if(window.confirm('确实要删除该广告信息？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/adDim.do?method=removeTblAdDim&adId=" + adId;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/adDim.do">
<html:hidden property="method" value="queryTblAdDim" />
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">广告标题<input type="text" name="strTitile" class="searchtxt" value="<c:out value='${strTitile }'/>" /></span>
    <span class="searchbox">发布目标设备
    <select name="strEquType">
    	<option value="">请选择设备</option>
    	<option value='s' <c:if test="${'s'==strEquType }"> selected="selected"</c:if>>室内机</option>
    	<option value='d' <c:if test="${'d'==strEquType }"> selected="selected"</c:if>>门口机</option>
    	<option value='m' <c:if test="${'m'==strEquType }"> selected="selected"</c:if>>管理机</option>
    </select>
    </span>
    <span class="searchbox">发布时间<input type="text" name="strCreateDate" class="searchtxt" value="<c:out value='${strCreateDate }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">广告编号</th>
            <th class="t2">广告标题</th>
            <th class="t3">目标设备</th>
            <th class="t3">有效期</th>
            <th class="t4">发布时间</th>
            <th class="t5">发布人</th>
            <th class="t6">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblAdDimBo" items="${tblAdDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblAdDimBo.adId}" /></td>
            <td class="t2"><c:out value="${TblAdDimBo.titile}" /></td>
            <td class="t3">
            	<c:choose>
            		<c:when test="${TblAdDimBo.equType=='s'}">室内机</c:when>
            		<c:when test="${TblAdDimBo.equType=='d'}">门口机</c:when>
            		<c:when test="${TblAdDimBo.equType=='m'}">管理机</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            </td>
            <td class="t3"><c:out value="${TblAdDimBo.beginDate}" /> - <c:out value="${TblAdDimBo.endDate}" /></td>
            <td class="t4"><c:out value="${TblAdDimBo.createDate}" /></td>
            <td class="t5"><c:out value="${TblAdDimBo.createUsr}" /></td>
            <td class="t6">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/adDim.do?method=forAddTblAdDim&adId=<c:out value="${TblAdDimBo.adId }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblAdDimBo.adId }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/adDim.do?method=viewTblAdDim&adId=<c:out value="${TblAdDimBo.adId }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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
