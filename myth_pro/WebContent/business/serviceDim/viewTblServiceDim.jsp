<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<html:form action="/serviceDim.do">
<html:hidden property="method" value="addTblServiceDim" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">服务信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">信息编号：</td>
            <td class="t1"><c:out value="${TblServiceDimForm.serId}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">信息类型：</td>
            <td class="t1"><c:choose>
				<c:when test="${TblServiceDimForm.type =='S001'}">报修</c:when>
				<c:when test="${TblServiceDimForm.type =='S002'}">报警</c:when>
				<c:when test="${TblServiceDimForm.type =='S003'}">其他服务</c:when>
				<c:otherwise>其他</c:otherwise>
			</c:choose></td>
          </tr>
          <tr>
            <td class="t0">信息标题：</td>
            <td class="t1"><c:out value="${TblServiceDimForm.titile}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">信息内容：</td>
            <td class="t1"><c:out value="${TblServiceDimForm.context}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">发布人：</td>
            <td class="t1"><c:out value="${TblServiceDimForm.createUsr}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">发布时间：</td>
            <td class="t1"><c:out value="${TblServiceDimForm.createTime}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">处理状态：</td>
            <td class="t1">
            	<c:choose>
            		<c:when test="${TblServiceDimForm.chkState=='01'}">已处理</c:when>
            		<c:when test="${TblServiceDimForm.chkState=='02'}">未处理</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
           </td>
          </tr>
          <tr>
            <td class="t0">处理人：</td>
            <td class="t1"><c:out value="${TblServiceDimForm.chkUsr}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">处理结果：</td>
            <td class="t1"><c:out value="${TblServiceDimForm.chkRes}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">处理时间：</td>
            <td class="t1"><c:out value="${TblServiceDimForm.chkTime}"></c:out></td>
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
