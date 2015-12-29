<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<jsp:include page="/share/top.jsp" />
<%
String path = request.getContextPath();
	String strPraId ="";
	String strChlId = "";
	strPraId = (String)request.getParameter("menuPare");
	strChlId = (String)request.getParameter("menuChild");
 %>
<SCRIPT language="javascript">
	function checkFrm()
    {
    	if(checkIsNull('0','titile')==false){
    		alert("公告标题不为空");
    		return false;
    	}
    	if(checkIsNull('0','context')==false){
    		alert("公告内容不为空");
    		return false;
    	}
    	if(checkIsNull('0','equType')==false){
    		alert("设备类型不为空");
    		return false;
    	}
    	if(checkIsNull('0','beginDate')==false){
    		alert("开始时间不为空");
    		return false;
    	}
    	if(checkIsNull('0','endDate')==false){
    		alert("结束时间不为空");
    		return false;
    	}  
    	
    	if($("input[name='smImg']").val() == "" || $("input[name='bgImg']").val() == ""){
	    	if((checkIsNull('0','sfile')==false) && (checkIsNull('0','bfile')==false)){
	    		alert("缩略图和详细图不能同时为空");
	    		return false;
	    	}
    	}
    	
    	var beginDate = document.getElementById('beginDate').value;
    	var endDate = document.getElementById('endDate').value;
    	if(beginDate>endDate){
    		alert("开始时间不能大于结束时间");
    		return false;
    	}
    	document.forms[0].submit();
    }
</SCRIPT>
<html:form action="/adDim.do"  enctype="multipart/form-data">
<html:hidden property="method" value="addTblAdDim" />
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
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
          <c:if test="${!empty TblAdDimForm.adId}">
          <tr>
            <td class="t0">广告编号：</td>
            <td class="t1">
            	<c:out value="${TblAdDimForm.adId}"/>
            	<html:hidden property="adId" />
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">广告标题：</td>
            <td class="t1"><html:text property="titile" maxlength="80"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">广告内容：</td>
            <td class="t1"><html:textarea property="context" cols="50" rows="5"></html:textarea>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">缩略图：</td>
            <td class="t1"><html:file property="sfile"></html:file>
<%--            	<a href="/share/viewImg.jsp?img=/adDim/<c:out value="${TblAdDimForm.smImg}"/>" target="blank"><c:out value="${TblAdDimForm.smImg}"/></a>--%>
            	<a  class="thickbox" href="/upFile/adDim/<c:out value="${TblAdDimForm.smImg}"/>">
            	<c:if test="${not empty TblAdDimForm.smImg}">
            		<IMG alt="广告图片" width="70px" height="100px" src="/upFile/adDim/<c:out value="${TblAdDimForm.smImg}"/>">
            	</c:if>
            	</a>
				<html:hidden property="smImg" />
            </td>
          </tr>
          <tr>
            <td class="t0">详细图：</td>
            <td class="t1"><html:file property="bfile"></html:file>
<%--            	<a href="/share/viewImg.jsp?img=/adDim/<c:out value="${TblAdDimForm.bgImg}"/>" target="blank"><c:out value="${TblAdDimForm.bgImg}"/></a>--%>
            	<a  class="thickbox" href="/upFile/adDim/<c:out value="${TblAdDimForm.bgImg}"/>"> 
            	<c:if test="${not empty TblAdDimForm.bgImg}">
            		<IMG alt="广告图片" width="70px" height="100px" src="/upFile/adDim/<c:out value="${TblAdDimForm.bgImg}"/>">
            	</c:if>
            	</a>
				<html:hidden property="bgImg" />
            </td>
          </tr>
          <tr>
            <td class="t0">有效开始时间：</td>
            <td class="t1">
            <input type="text" name="beginDate" id="beginDate"
             value="<c:out value='${TblAdDimForm.beginDate }'/>"
             onfocus="WdatePicker({dateFmt:'yyyyMMdd'});"/> 
            (格式如：20140118)<span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">有效结束时间：</td>
            <td class="t1">
			<input type="text" name="endDate" id="endDate" value="<c:out value='${TblAdDimForm.endDate }'/>"
			 onfocus="WdatePicker({dateFmt:'yyyyMMdd'});"/>
			(格式如：20140118)<span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">设备类型：</td>
            <td class="t1"><html:select property="equType">
            	<html:option value="">请选择设备类型</html:option>
            	<html:option value="s">室内机</html:option>
            	<html:option value="d">门口机</html:option>
            	<html:option value="m">管理机</html:option>
            </html:select><span style="color: red;"><strong>*</strong></span>
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
