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


<html:form action="/buildingDim.do"  enctype="multipart/form-data">
<html:hidden property="method" value="addTblBuildingDim" />
<html:hidden property="buildingCode" />
<html:hidden property="areaCode" />
<html:hidden property="commCode" />
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
            <th colspan="2" class="th0">楼宇信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">所属小区：</td>
            <td class="t1"><c:out value="${TblBuildingDimForm.commName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">所属区域：</td>
            <td class="t1"><c:out value="${TblBuildingDimForm.areaName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">楼宇编号：</td>
            <td class="t1"><c:out value="${TblBuildingDimForm.buildingCode}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">楼宇名称：</td>
            <td class="t1"><html:text property="buildingName" maxlength="80"></html:text></td>
          </tr>
          <tr>
            <td class="t0">占地面积：</td>
            <td class="t1"><html:text property="buildingArea" maxlength="20"></html:text></td>
          </tr>
          <tr>
            <td class="t0">建筑面积：</td>
            <td class="t1"><html:text property="buildingArea2" maxlength="20"></html:text></td>
          </tr>
          <tr>
            <td class="t0">位置：</td>
            <td class="t1">
            <html:file property="file"></html:file>
<!--			<a  class="thickbox" href="/share/viewImg.jsp?img=/buildingDim/<c:out value="${TblBuildingDimForm.buildingUrl}"/>" target="blank">-->
			<a  class="thickbox" href="/upFile/buildingDim/<c:out value="${TblBuildingDimForm.buildingUrl}"/>">
			    <c:if test="${not empty TblBuildingDimForm.buildingUrl}">
            		<IMG alt="楼宇图片" width="70px" height="100px" src="/upFile/buildingDim/<c:out value="${TblBuildingDimForm.buildingUrl}"/>">
            	</c:if>
			<html:hidden property="buildingUrl"/>
			</td>
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

