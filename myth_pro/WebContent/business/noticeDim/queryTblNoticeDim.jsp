<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function linkOnClick(formName, noticeCode)
    	{
    		if(window.confirm('确实要删除该公告信息？')){
    			var form = document.forms[formName];
    			form.action="<%=path%>/noticeDim.do?method=removeTblNoticeDim&noticeCode=" + noticeCode;
    			form.submit();
    		}    			
    	}
    function queryOnclick(){
    	var reg = /^\d{1,14}$/;
    	var strCreateTime = document.getElementById("strCreateTime").value;
    	if(strCreateTime!=""){
    		if(reg.test(strCreateTime) === false){  
	       		alert("发布时间格式：yyyyMMddHHmmss");  
	       		return  false;  
	   		}
    	}
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/noticeDim.do">
<html:hidden property="method" value="queryTblNoticeDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="title"><div class="border">
    <span class="searchbox">公告标题<input type="text" name="strTitile" class="searchtxt" value="<c:out value='${strTitile }'/>" /></span>
    <span class="searchbox">发布时间<input type="text" name="strCreateTime" id="strCreateTime" class="searchtxt" value="<c:out value='${strCreateTime }'/>" /></span>
    
    <span class="btnspan btnspanl"><span class="pic pic8"></span>
    <input type="button" class="btn" value="查询" onclick="queryOnclick();"/></span>
    </div></div>
    
    <div class="tablecontent">
      <table class="table" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th class="t1">公告编号</th>
            <th class="t2">公告标题</th>
            <th class="t3">有效期</th>
            <th class="t4">发布时间</th>
            <th class="t5">发布人</th>
            <th class="t6">操作 </th>
          </tr>
        </thead>
     <tbody>
     	<c:forEach var="TblNoticeDimBo" items="${tblNoticeDims.list}" varStatus="e">
          <tr <c:if test="${e.count%2==1}"> class="split" </c:if>>
            <td class="t1"><c:out value="${TblNoticeDimBo.noticeCode}" /></td>
            <td class="t2"><c:out value="${TblNoticeDimBo.titile}" /></td>
            <td class="t3"><c:out value="${TblNoticeDimBo.beginDate}" /> - <c:out value="${TblNoticeDimBo.endDate}" /></td>
            <td class="t4" onclick="dateformatter(this)"><c:out value="${TblNoticeDimBo.createTime}" /></td>
            <td class="t5"><c:out value="${TblNoticeDimBo.createUsr}" /></td>
            <td class="t6">
            <span class="btnoperal"><input type="button" value="修改" class="btn"
            	onclick="document.location.href='<%=path%>/noticeDim.do?method=forAddTblNoticeDim&noticeCode=<c:out value="${TblNoticeDimBo.noticeCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
            </span>
            <span class="btnoperal"><input type="button" value="删除" class="btn"
            	onclick="linkOnClick('0', '<c:out value="${TblNoticeDimBo.noticeCode }"/>');">
            </span>
            <span class="btnoperal"><input type="button" value="查看" class="btn"
            	onclick="document.location.href='<%=path%>/noticeDim.do?method=viewTblNoticeDim&noticeCode=<c:out value="${TblNoticeDimBo.noticeCode }"/>&menuPare=<%=strPraId%>&menuChild=<%=strChlId%>';">
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

<!-- 时间格式化  add by lxj ++ -->
<script type="text/javascript">
	$(document).ready(function(){
		$(".table tbody tr .t4").each(function(){
			$(this).trigger("click");
		});
	}) ;
</script>
