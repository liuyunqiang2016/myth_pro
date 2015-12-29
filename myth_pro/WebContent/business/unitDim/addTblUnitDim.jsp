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
<html:form action="/unitDim.do"  enctype="multipart/form-data">
<html:hidden property="method" value="addTblUnitDim" />
<html:hidden property="areaCode" />
<html:hidden property="unitCode" />
<html:hidden property="commCode" />
<html:hidden property="buildingCode" />
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
            <th colspan="2" class="th0">单元信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">所属小区：</td>
            <td class="t1"><c:out value="${TblUnitDimForm.commName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">所属区域：</td>
            <td class="t1"><c:out value="${TblUnitDimForm.areaName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">所属楼宇：</td>
            <td class="t1"><c:out value="${TblUnitDimForm.buildingName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">单元编号：</td>
            <td class="t1"><c:out value="${TblUnitDimForm.unitCode}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">单元名称：</td>
            <td class="t1"><html:text property="unitName" maxlength="80"></html:text></td>
          </tr>
           <tr>
            <td class="t0">户型图：</td>
            <td class="t1"><html:file property="file"></html:file>
			<a href="/share/viewImg.jsp?img=/unitDim/<c:out value="${TblUnitDimForm.unitUrl}"/>" target="blank"><c:out value="${TblUnitDimForm.unitUrl}"/></a>
			<html:hidden property="unitUrl"/></td>
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
