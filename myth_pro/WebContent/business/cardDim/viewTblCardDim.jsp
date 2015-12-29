<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<jsp:include page="/share/top.jsp" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">门禁卡信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">门禁卡编号：</td>
            <td class="t1"><c:out value="${TblCardDimForm.cardNo}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">房号：</td>
            <td class="t1"><c:out value="${TblCardDimForm.roomCode}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">业主名称：</td>
            <td class="t1"><c:out value="${TblCardDimForm.usrName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">身份证号码：</td>
            <td class="t1"><c:out value="${TblCardDimForm.userNo}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">状态：</td>
            <td class="t1">
            <c:choose>
            	<c:when test="${TblCardDimForm.state=='01'}">在用</c:when>
            	<c:when test="${TblCardDimForm.state=='02'}">停用</c:when>
            	<c:otherwise></c:otherwise>
            </c:choose>
          </td>
          </tr>
          <tr>
            <td class="t0">有效时间：</td>
            <td class="t1"><c:out value="${TblCardDimForm.beginDate}"></c:out>-
									<c:out value="${TblCardDimForm.endDate}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">门禁权限：</td>
            <td class="t1">
<%--               <c:forEach var="equ" items="${EquList}">--%>
<%--           		<input type="checkbox" name="fingop" value="<c:out value='${equ.equCode }'/>"--%>
<%--           			<%--%>
<%--           				List fingop = (List)request.getAttribute("FingOpList");--%>
<%--         					TblEquDimBo op = (TblEquDimBo)pageContext.getAttribute("equ");--%>
<%--         					for(int i=0;i<fingop.size();i++){--%>
<%--         						TblFingOpBo bo1 = (TblFingOpBo)fingop.get(i);--%>
<%--         						if(bo1.getEquCode().equals(op.getEquCode())){--%>
<%--         						%>--%>
<%--								checked="checked"--%>
<%--							<%--%>
<%--								break;--%>
<%--         						}--%>
<%--         					}--%>
<%--           			 %>--%>
<%--           		/>--%>
<%--           		<input type="hidden" id="<c:out value='${equ.equCode }'/>"--%>
<%--           			name="<c:out value='${equ.equCode }'/>" --%>
<%--           			value="<c:out value='${equ.equAccid }'/>"/>--%>
<%--           		<c:out value="${equ.equCode}"/>-<c:out value="${equ.equName}"/>--%>
           		<br/>
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
<jsp:include page="/share/foot.jsp" />
