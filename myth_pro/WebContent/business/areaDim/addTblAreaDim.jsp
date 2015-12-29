<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String strPraId ="";
String strChlId = "";
strPraId = (String)request.getParameter("menuPare");
strChlId = (String)request.getParameter("menuChild");
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
	function checkFrm()
	{
		document.forms[0].submit();
	}
</SCRIPT>
<html:form action="/areaDim.do">
<html:hidden property="method" value="addTblAreaDim" />
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<html:hidden property="areaCode" />
<html:hidden property="commCode" />
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">区域信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">所属小区：</td>
            <td class="t1"><c:out value="${TblAreaDimForm.commName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">区域编号：</td>
            <td class="t1"><c:out value="${TblAreaDimForm.areaCode}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">区域名称：</td>
            <td class="t1"><html:text property="areaName" maxlength="80"></html:text></td>
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
