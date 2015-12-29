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
    	if(checkIsNull('0','softName')==false){
    		alert("软件名称不为空");
    		return false;
    	}
    	if(checkIsNull('0','softVision')==false){
    		alert("软件版本号不为空");
    		return false;
    	}
    	var softVision = document.getElementById("softVision").value;
    	var reg = /^[1-9]*[0-9]$/;
    	if(reg.test(softVision)==false){
    		alert("软件版本号只能由数字组成");
    		return false;
    	}
    	if(checkIsNull('0','equType')==false){
    		alert("设备类型不为空");
    		return false;
    	}
    	document.forms[0].submit();
    }
</SCRIPT>
<html:form action="/softDim.do" enctype="multipart/form-data">
<html:hidden property="method" value="addTblSoftDim" />
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
            <th colspan="2" class="th0">软件信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblSoftDimForm.softCode}">
          <tr>
            <td class="t0">软件编号：</td>
            <td class="t1">
            	<c:out value="${TblSoftDimForm.softCode}"/>
            	<html:hidden property="softCode" />
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">软件名称：</td>
            <td class="t1"><html:text property="softName" maxlength="50"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">软件版本号：</td>
            <td class="t1">
            <input type="text" name="softVision" id="softVision" maxlength="20"
            value="<c:out value='${TblSoftDimForm.softVision }'/>"/>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">文件名：</td>
            <td class="t1"><html:file property="file"></html:file>
				<a href="<%=path%>/softDim.do?method=downSoftDim&softCode=<c:out value='${TblSoftDimForm.softCode }'/>"><c:out value="${TblSoftDimForm.softFn}"/></a></td>
          </tr>
          <tr>
            <td class="t0">更新说明：</td>
            <td class="t1"><html:text property="updExp" maxlength="100"></html:text></td>
          </tr>
          <tr>
            <td class="t0">设备类型：</td>
            <td class="t1"><html:select property="equType">
            	<html:option value="">请选择设备类型</html:option>
            	<html:option value="s">室内机</html:option>
            	<html:option value="d">门口机</html:option>
            	<html:option value="m">管理机</html:option>
            </html:select><span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">状态：</td>
            <td class="t1">
            	<html:select property="softState">
            		<html:option value="0">禁用</html:option>
            		<html:option value="1">启用</html:option>
            	</html:select>
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
