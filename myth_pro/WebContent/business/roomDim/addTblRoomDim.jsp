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
    	if(checkIsNull('0','ownerCode')==false){
    		alert("业主不为空");
    		return false;
    	}
    	document.forms[0].submit();
    }
</SCRIPT>
<html:form action="/roomDim.do">
<html:hidden property="method" value="addTblRoomDim" />
<html:hidden property="roomCode" />
<html:hidden property="unitCode" />
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
            <th colspan="2" class="th0">房号信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">所属小区：</td>
            <td class="t1"><c:out value="${TblRoomDimForm.commName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">所属区域：</td>
            <td class="t1"><c:out value="${TblRoomDimForm.areaName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">所属楼宇：</td>
            <td class="t1"><c:out value="${TblRoomDimForm.buildingName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">所属单元：</td>
            <td class="t1"><c:out value="${TblRoomDimForm.unitName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">房号：</td>
            <td class="t1"><c:out value="${TblRoomDimForm.roomCode}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">业主：</td>
            <td class="t1">
            <select name="ownerCode">
            	<option value="">请选择业主</option>
            	<c:forEach var="owner" items="${ownlist}">
            		<option value="<c:out value='${owner.ownerCode }'/>"
					<c:if test="${owner.ownerCode==TblRoomDimForm.ownerCode }"> selected="selected"</c:if>            				
            		 >
            		<c:out value="${owner.ownerName}"></c:out>
            		</option>
            	</c:forEach>
            	</select><span style="color: red;"><strong>*</strong></span>
            </td>
          </tr>
          <tr>
            <td class="t0">面积：</td>
            <td class="t1"><html:text property="roomArea" maxlength="20"></html:text></td>
          </tr>
          <tr>
            <td class="t0">用途：</td>
            <td class="t1">
            <html:select property="roomUsr">
            	<html:option value="">请选择用途</html:option>
            	<html:option value="01">出租</html:option>
            	<html:option value="02">自住</html:option>
            </html:select>
            </td>
          </tr>
          <tr>
            <td class="t0">状态：</td>
            <td class="t1">
            <html:select property="roomState">
            	<html:option value="">请选择状态</html:option>
            	<html:option value="01">出租</html:option>
            	<html:option value="02">入住</html:option>
            </html:select>
           </td>
          </tr>
          <tr>
            <td class="t0">钥匙状态：</td>
            <td class="t1">
            <html:select property="keyState">
            	<html:option value="">请选择状态</html:option>
            	<html:option value="01">使用</html:option>
            	<html:option value="02">挂失</html:option>
            </html:select>
           </td>
          </tr>
           <tr>
            <td class="t0">户型：</td>
            <td class="t1">
				<select name="htCode">
				<option value="">请选择户型</option>
				<c:forEach var="ht" items="${htlist}">
					<option value='<c:out value="${ht.htCode}"/>'  <c:if test="${TblRoomDimForm.htCode==ht.htCode }"> selected="selected"</c:if>>
					<c:out value="${ht.htName}"/>
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
