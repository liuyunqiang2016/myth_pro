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
    	if(checkIsNull('0','equName')==false){
    		alert("设备名称不为空");
    		return false;
    	}
    	if(checkIsNull('0','equType')==false){
    		alert("设备类型不为空");
    		return false;
    	}
    	document.forms[0].submit();
    }
</SCRIPT>
<html:form action="/equDim.do">
<html:hidden property="method" value="addTblEquDim" />
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
            <th colspan="2" class="th0">设备信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblEquDimForm.equCode}">
          <tr>
            <td class="t0">设备编号：</td>
            <td class="t1">
            	<c:out value="${TblEquDimForm.equCode}"/>
            	<html:hidden property="equCode" />
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">设备名称：</td>
            <td class="t1"><html:text property="equName" maxlength="80"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
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
            <td class="t0">门禁编号：</td>
            <td class="t1"><html:text property="equAccid" maxlength="10"></html:text></td>
          </tr>
          <tr>
            <td class="t0">IP地址：</td>
            <td class="t1"><html:text property="ipAdd" maxlength="50"></html:text></td>
          </tr>
          <tr>
            <td class="t0">MAC地址：</td>
            <td class="t1"><html:text property="macAdd" maxlength="50"></html:text></td>
          </tr>
          <tr>
            <td class="t0">在线状态：</td>
            <td class="t1">
            	<html:select property="equState">
            		<html:option value="0">不在线</html:option>
            		<html:option value="1">在线</html:option>
            	</html:select>
            </td>
          </tr>
           <tr>
            <td class="t0">所属房号：</td>
            <td class="t1">
            <select name="equAdd">
            	<option value="">请选择房号</option>
            	<c:forEach var="room" items="${roomList}">
            	<option value="<c:out value='${room.roomCode }'/>"
            	   <c:if test="${room.roomCode==TblEquDimForm.equAdd }"> selected="selected"</c:if>>
            		<c:out value="${room.roomCode }"/>
            	</option>
            	</c:forEach>
            </select>
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
