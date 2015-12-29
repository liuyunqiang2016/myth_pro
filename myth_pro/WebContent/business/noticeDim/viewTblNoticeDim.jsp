<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<html:form action="/noticeDim.do">
<html:hidden property="method" value="addTblNoticeDim" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">公告信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">公告编号：</td>
            <td class="t1"><c:out value="${TblNoticeDimForm.noticeCode}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">公告标题：</td>
            <td class="t1"><c:out value="${TblNoticeDimForm.titile}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">公告内容：</td>
            <td class="t1"><c:out value="${TblNoticeDimForm.context}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">发布人：</td>
            <td class="t1"><c:out value="${TblNoticeDimForm.createUsr}"></c:out></td>
          </tr>
           <tr>
            <td class="t0">发布时间：</td>
            <td class="t1" id="issueDate" onClick="dateformatter(this)"><c:out value="${TblNoticeDimForm.createTime}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">有效期：</td>
            <td class="t1"><c:out value="${TblNoticeDimForm.beginDate}"></c:out>
									-
			<c:out value="${TblNoticeDimForm.endDate}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">设备类型：</td>
            <td class="t1">
            	<c:choose>
            		<c:when test="${TblNoticeDimForm.equType=='s'}">室内机</c:when>
            		<c:when test="${TblNoticeDimForm.equType=='d'}">门口机</c:when>
            		<c:when test="${TblNoticeDimForm.equType=='m'}">管理机</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="operbtn">
        <span class="btnspan btnspanc"><input type="button" value="返回" class="btn" onclick="history.back(-1);"></span>
      </div>
    </div>
    
  </div>
 </div>
 <div class="clear"></div>
</div>
</html:form>
<jsp:include page="/share/foot.jsp" />

<!-- 时间格式化  add by lxj ++ -->
<script type="text/javascript">
	$(document).ready(function(){
		var strDate = $("#issueDate").trigger("click") ;
	}) ;
</script>
