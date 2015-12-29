<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
 %>
<jsp:include page="/share/top.jsp" />
<html:form action="/adDim.do"  enctype="multipart/form-data">
<html:hidden property="method" value="addTblAdDim" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">广告信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">广告编号：</td>
            <td class="t1"><c:out value="${TblAdDimForm.adId}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">广告标题：</td>
            <td class="t1"><c:out value="${TblAdDimForm.titile}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">广告内容：</td>
            <td class="t1"><c:out value="${TblAdDimForm.context}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">发布人：</td>
            <td class="t1"><c:out value="${TblAdDimForm.createUsr}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">发布时间：</td>
            <td class="t1"><c:out value="${TblAdDimForm.createDate}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">有效期：</td>
            <td class="t1"><c:out value="${TblAdDimForm.beginDate}"></c:out>
									-
			<c:out value="${TblAdDimForm.endDate}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">缩略图：</td>
            <td class="t1">
<%--            	<a href="/share/viewImg.jsp?img=/adDim/<c:out value="${TblAdDimForm.smImg}"/>" target="blank"><c:out value="${TblAdDimForm.smImg}"/></a>--%>
			<a  class="thickbox" href="/upFile/adDim/<c:out value="${TblAdDimForm.smImg}"/>">
				 <IMG alt="广告图片" width="70px" height="100px" src="/upFile/adDim/<c:out value="${TblAdDimForm.smImg}"/>"> 
			</a>
            </td>
          </tr>
          <tr>
            <td class="t0">详细图：</td>
            <td class="t1">
<%--            	<a href="/share/viewImg.jsp?img=/adDim/<c:out value="${TblAdDimForm.bgImg}"/>" target="blank"><c:out value="${TblAdDimForm.bgImg}"/></a>--%>
			<a  class="thickbox" href="/upFile/adDim/<c:out value="${TblAdDimForm.bgImg}"/>"> 
				<IMG alt="广告图片" width="70px" height="100px" src="/upFile/adDim/<c:out value="${TblAdDimForm.bgImg}"/>"> 
            </td>
          </tr>
          <tr>
            <td class="t0">设备类型：</td>
            <td class="t1"><c:choose>
            		<c:when test="${TblAdDimForm.equType=='s'}">室内机</c:when>
            		<c:when test="${TblAdDimForm.equType=='d'}">门口机</c:when>
            		<c:when test="${TblAdDimForm.equType=='m'}">管理机</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose></td>
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

