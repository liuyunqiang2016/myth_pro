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
    	if(checkIsNull('0','itemName')==false){
    		alert("缴费项目名称不为空");
    		return false;
    	}
    	if(checkIsNull('0','calCode')==false){
    		alert("计算方式不为空");
    		return false;
    	}
    	document.forms[0].submit();
    }
</SCRIPT>
<html:form action="/itemDim.do">
<html:hidden property="method" value="addTblItemDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">缴费项目信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblItemDimForm.itemCode}">
          <tr>
            <td class="t0">缴费项目编号：</td>
            <td class="t1">
            	<c:out value="${TblItemDimForm.itemCode}"/>
            	<html:hidden property="itemCode" />
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">缴费项目名称：</td>
            <td class="t1"><html:text property="itemName" maxlength="80"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">计算方式：</td>
            <td class="t1">
            <select name="calCode" id="calCode">
            	<option value="">请选择计算方式</option>
            	<c:forEach var="cal" items="${calList}">
            		<option value='<c:out value="${cal.calCode}"/>'
            			<c:if test="${cal.calCode==TblItemDimForm.calCode}"> selected="selected"</c:if> >
            			<c:out value="${cal.calName}"></c:out>
            		</option>
            	</c:forEach>
            </select><span style="color: red;"><strong>*</strong></span>
            </td>
          </tr>
          <tr>
            <td class="t0">收费标准：</td>
            <td class="t1"><html:text property="sfLev" maxlength="80"></html:text></td>
          </tr>
          <tr>
            <td class="t0">收费说明：</td>
            <td class="t1"><html:text property="sfExp" maxlength="80"></html:text></td>
          </tr>
        </tbody>
      </table>
      <div class="operbtn">
        <span class="btnspan btnspanc"><input type="button" value="保存" class="btn" onclick="checkFrm()"></span>
        <span class="btnspan btnspanc"><input type="button" value="取消" class="btn" onclick="history.back(-1);"></span>
      </div>
    </div>
    
  </div>
 </div>
 <div class="clear"></div>
</div>
</html:form>
<jsp:include page="/share/foot.jsp" />
