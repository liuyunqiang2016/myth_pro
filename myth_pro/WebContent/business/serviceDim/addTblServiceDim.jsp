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
<html:form action="/serviceDim.do">
<html:hidden property="method" value="addTblServiceDim" />
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
            <th colspan="2" class="th0">服务信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblServiceDimForm.serCode}">
          <tr>
            <td class="t0">信息编号：</td>
            <td class="t1">
            	<c:out value="${TblServiceDimForm.serCode}"/>
            	<html:hidden property="serCode" />
            	<html:hidden property="equCode" />
            	<html:hidden property="serId" />
            	<html:hidden property="createUsr" />
            	<html:hidden property="createTime" />
            </td>
          </tr>
          
          <tr>
            <td class="t0">信息标题：</td>
            <td class="t1"><c:out value="${TblServiceDimForm.titile}"></c:out>
            	<html:hidden property="titile"/>
            </td>
          </tr>
          <tr>
            <td class="t0">信息内容：</td>
            <td class="t1"><c:out value="${TblServiceDimForm.context}"></c:out>
            	<html:hidden property="context"/>
            </td>
          </tr>
          <tr>
            <td class="t0">信息类型：</td>
            <td class="t1">
            	<c:choose>
            		<c:when test="${TblServiceDimForm.type=='S001'}">报修</c:when>
            		<c:when test="${TblServiceDimForm.type=='S002'}">报警</c:when>
            		<c:when test="${TblServiceDimForm.type=='S003'}">其他服务</c:when>
            		<c:otherwise></c:otherwise>
            	</c:choose>
            	<html:hidden property="type"/>
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">处理状态：</td>
            <td class="t1">
				<html:select property="chkState">
					<html:option value="01">已处理</html:option>
					<html:option value="02">未处理</html:option>
				</html:select>
            </td>
          </tr>
          <tr>
            <td class="t0">处理结果：</td>
            <td class="t1"><html:text property="chkRes" maxlength="150"></html:text></td>
          </tr>
        </tbody>
      </table>
      <div class="operbtn">
      	<c:if test="${TblServiceDimForm.chkState!='01'}">
        <span class="btnspan btnspanc"><input type="button" value="保存" class="btn" onclick="checkFrm()"></span>
        </c:if>
        <span class="btnspan btnspanc"><input type="button" value="取消" class="btn" onclick="history.back(-1);"></span>
      </div>
    </div>
    
  </div>
 </div>
 <div class="clear"></div>
</div>
</html:form>
<jsp:include page="/share/foot.jsp" />
