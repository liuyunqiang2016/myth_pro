<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    	function checkFrm()
    	{
    		document.forms[0].submit();
    	}
</SCRIPT>
<html:form action="/fingDim.do">
<html:hidden property="method" value="addTblFingDim" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">指纹门禁信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">指纹门禁卡编号：</td>
            <td class="t1"><c:out value="${TblFingDimForm.usrNo}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">姓名：</td>
            <td class="t1"><c:out value="${TblFingDimForm.fingUsr}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">房号：</td>
            <td class="t1"><c:out value="${TblFingDimForm.roomCode}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">指纹1：</td>
            <td class="t1">
            <html:textarea property="fingImg1" readonly="true"  rows="6" cols="60"></html:textarea>
            </td>
          </tr>
          <tr>
            <td class="t0">指纹2：</td>
            <td class="t1">
             <html:textarea property="fingImg2" readonly="true"  rows="6" cols="60"></html:textarea>
            </td>
          </tr>
          <tr>
            <td class="t0">状态：</td>
            <td class="t1">
            <c:choose>
            	<c:when test="${TblFingDimForm.fingState=='01'}">在用</c:when>
            	<c:when test="${TblFingDimForm.fingState=='02'}">停用</c:when>
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
