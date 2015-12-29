<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
    function checkFrm()
    {
    	<c:if test="${empty TblCommDimForm.commCode}">
	    	if(checkIsNull('0','commName')==false){
	    		alert("小区名称不为空");
	    		return false;
	    	}
	    	if(checkIsNull('0','commPro')==false){
	    		alert("必须选择一个物业公司");
	    		return false;
	    	}
	    	if($("#commCode").val() == ""){
	    		alert("小区编号不能为空");
	    		return  false ;
	    	}else if(!checkIsNumber($("#commCode").val())){
	    		alert("输入的小区编号不合法");
	    		$("#commCode").val("");
	    		return  false ;
	    	}
	    </c:if>
    	document.forms[0].submit();
    }
</SCRIPT>
<html:form action="/commDim.do" enctype="multipart/form-data">
<html:hidden property="method" value="addTblCommDim" />
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">小区信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblCommDimForm.commCode}">
          <tr>
            <td class="t0">小区编号：</td>
            <td class="t1">
            	<c:out value="${TblCommDimForm.commCode}"/>
		<html:hidden property="commCode" />
            </td>
          </tr>
          </c:if>
          <c:if test="${empty TblCommDimForm.commCode}">
          <tr>
            <td class="t0">小区编号：</td>
            <td class="t1">
             <!-- <input name="commCode" id="commCode" maxlength="18" type="text" onKeyUp="value=value.replace(/\D/g,'')" onafterpaste="value=value.replace(/\D/g,'')" > -->
             <input name="commCode" id="commCode" maxlength="18" type="text" >
             <html:hidden property="commCode" /><span style="color:red">* 小区编号只能是数字,且不能以0开头</span></td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">小区名称：</td>
            <td class="t1"><html:text property="commName" maxlength="80"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">小区地址：</td>
            <td class="t1"><html:text property="commAdds" maxlength="100"></html:text></td>
          </tr>
          <tr>
            <td class="t0">小区面积：</td>
            <td class="t1"><html:text property="commArea" maxlength="20"></html:text></td>
          </tr>
          <tr>
            <td class="t0">小区平面图URL：</td>
            <td class="t1"><html:file property="file"></html:file>
<!--			<a href="/share/viewImg.jsp?img=/commDim/<c:out value="${TblCommDimForm.commUrl}"/>" target="blank"><c:out value="${TblCommDimForm.commUrl}"/></a>-->
			<a class="thickbox" href="/upFile/commDim/<c:out value="${TblCommDimForm.commUrl}"/>"> 
			 	<c:if test="${not empty TblCommDimForm.commUrl}">
            		<IMG alt="小区图片" width="70px" height="100px" src="<%=path%>/upFile/commDim/<c:out value="${TblCommDimForm.commUrl}"/>">
            	</c:if>
			 </a>
			<html:hidden property="commUrl"/></td>
          </tr>
           <tr>
            <td class="t0">小区物业：</td>
            <c:if test="${not empty property}">
            	<td class="t1"><c:out value="${property.propertyName}"></c:out>
            	<html:hidden property="commPro"/>
            	</td>
            </c:if>
            <c:if test="${empty property}">
            	<td class="t1">
            	<html:select property="commPro">
            		<html:option value="">请选择物业</html:option>
            		<html:options 
					   collection="propertyList"
					   property="propertyId"
					   labelProperty="propertyName"/>
	            	<%-- <c:forEach var="pro" items="${propertyList}">
		            	<html:option value='<c:out value="${pro.propertyId}"></c:out>'><c:out value="${pro.propertyName}"></c:out></html:option>
		            </c:forEach> --%>
            	</html:select>
            	<span style="color: red;"><strong>*</strong></span></td>
            </c:if>
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
</html:form>
<jsp:include page="/share/foot.jsp" />
