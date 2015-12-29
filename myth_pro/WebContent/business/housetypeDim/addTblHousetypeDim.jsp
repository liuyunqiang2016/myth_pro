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
    	if(checkIsNull('0','htName')==false){
    		alert("户型名称不为空");
    		return false;
    	}
    	document.forms[0].submit();
    }
</SCRIPT>
<html:form action="/housetypeDim.do"  enctype="multipart/form-data">
<html:hidden property="method" value="addTblHousetypeDim" />
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
            <th colspan="2" class="th0">户型信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblHousetypeDimForm.htCode}">
          <tr>
            <td class="t0">户型编号：</td>
            <td class="t1">
            	<c:out value="${TblHousetypeDimForm.htCode}"/>
            	<html:hidden property="htCode" />
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">户型名称：</td>
            <td class="t1"><html:text property="htName" maxlength="80"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">房间数：</td>
            <td class="t1"><html:text property="roCount" maxlength="20"></html:text></td>
          </tr>
          <tr>
            <td class="t0">厅数：</td>
            <td class="t1"><html:text property="toCount" maxlength="20"></html:text></td>
          </tr>
           <tr>
            <td class="t0">户型图：</td>
            <td class="t1"><html:file property="file"></html:file>
<%--				<a href="/share/viewImg.jsp?img=/housetypeDim/<c:out value="${TblHousetypeDimForm.htUrl}"/>" target="blank"><c:out value="${TblHousetypeDimForm.htUrl}"/></a>--%>
				<a  class="thickbox" href="/upFile/housetypeDim/<c:out value="${TblHousetypeDimForm.htUrl}"/>"> 
<%--				   <c:out value="${TblHousetypeDimForm.htUrl}"/>--%>
				   <c:if test="${not empty TblHousetypeDimForm.htUrl}">
					  <IMG alt="户型图片" width="70px" height="100px" src="/upFile/housetypeDim/<c:out value="${TblHousetypeDimForm.htUrl}"/>">
				   </c:if>
				</a>
				<html:hidden property="htUrl"/></td>
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
