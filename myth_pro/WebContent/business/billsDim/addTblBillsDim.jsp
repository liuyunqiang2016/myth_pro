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
    	if(checkIsNull('0','itemCode')==false){
    		alert("缴费项目不为空");
    		return false;
    	}
    	if(checkIsNull('0','roomCode')==false){
    		alert("缴费房号不为空");
    		return false;
    	}
    	if(checkIsNull('0','jfDate')==false){
    		alert("缴费时间不为空");
    		return false;
    	}
    	if(checkIsNull('0','billAmt')==false){
    		alert("费用不为空");
    		return false;
    	}
    	if(checkIsNull('0','jfDate')==false){
    		alert("缴费时间不为空");
    		return false;
    	}
    	if(checkIsNull('0','jzDate')==false){
    		alert("截至时间不为空");
    		return false;
    	}
    	var jfDate = document.getElementById('jfDate').value;
    	var jzDate = document.getElementById('jzDate').value;
    	if(jfDate>jzDate){
    		alert("缴费时间不能大于缴费截至时间");
    		return false;
    	}
    	document.forms[0].submit();
    }
</SCRIPT>
<html:form action="/billsDim.do">
<html:hidden property="method" value="addTblBillsDim" />
<%
	String strPraId ="";
	String strChlId = "";
	strPraId = (String)request.getParameter("menuPare");
	strChlId = (String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">缴费单据信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblBillsDimForm.billCode}">
          <tr>
            <td class="t0">缴费单据编号：</td>
            <td class="t1">
            	<c:out value="${TblBillsDimForm.billCode}"/>
            	<html:hidden property="billCode" />
            </td>
          </tr>
          </c:if>
          <c:if test="${!empty TblBillsDimForm.roomCode}">
          	<tr>
            <td class="t0">缴费房号：</td>
            <td class="t1">
            	<html:hidden property="roomCode"/>
            	<html:hidden property="commID"/>
            	<html:hidden property="areaID"/>
            	<html:hidden property="unitID"/>
            	<html:hidden property="buldID"/>
            	<c:forEach var="room" items="${roomList}">
            		<c:if test="${room.roomCode==TblBillsDimForm.roomCode
            				and room.unitCode == TblBillsDimForm.unitID
            				and room.areaCode == TblBillsDimForm.areaID
            				and room.commCode == TblBillsDimForm.commID
            				and room.buildingCode == TblBillsDimForm.buldID}"> 
            			<c:out value="${room.commName}"></c:out>小区
            			<c:out value="${room.areaName}"></c:out>区
            			<c:out value="${room.buildingName}"></c:out>楼宇
            			<c:out value="${room.unitName}"></c:out>单元
            			<c:out value="${room.roomCode}"></c:out>号
            		</c:if>
            	</c:forEach>
            </td>
          </tr>
           </c:if>
          <c:if test="${!empty TblBillsDimForm.itemCode}">
          <tr>
            <td class="t0">缴费项目：</td>
            <td class="t1">
            	<html:hidden property="itemCode"/>
            	<c:forEach var="item" items="${itemList}">
            		<c:if test="${item.itemCode==TblBillsDimForm.itemCode}">
            			<c:out value="${item.itemName}"></c:out>
            		</c:if>
            	</c:forEach>
            </td>
          </tr>
          </c:if>
          <c:if test="${empty TblBillsDimForm.roomCode}">
          <tr>
            <td class="t0">缴费房号：</td>
            <td class="t1">
            	<select name="roomCode">
            	<option value="">请选择缴费房号</option>
            	<c:forEach var="room" items="${roomList}">
            		<option value='<c:out value="${room.commCode}"/>;<c:out value="${room.areaCode}"/>;<c:out value="${room.buildingCode}"/>;<c:out value="${room.unitCode}"/>;<c:out value="${room.roomCode}"/>'
            			<c:if test="${room.roomCode==TblBillsDimForm.roomCode
            				and room.unitCode == TblBillsDimForm.unitID
            				and room.areaCode == TblBillsDimForm.areaID
            				and room.commCode == TblBillsDimForm.commID
            				and room.buildingCode == TblBillsDimForm.buldID}"> selected="selected"</c:if> >
            			<c:out value="${room.commName}"></c:out>小区
            			<c:out value="${room.areaName}"></c:out>区
            			<c:out value="${room.buildingName}"></c:out>楼宇
            			<c:out value="${room.unitName}"></c:out>单元
            			<c:out value="${room.roomCode}"></c:out>号
            		</option>
            	</c:forEach>
            </select><span style="color: red;"><strong>*</strong></span>
            </td>
          </tr>
          </c:if>
          <c:if test="${empty TblBillsDimForm.itemCode}">
          <tr>
            <td class="t0">缴费项目：</td>
            <td class="t1">
            	<html:hidden property="opType" value="1"/>
            	<select name="itemCode">
            	<option value="">请选择缴费项目</option>
            	<c:forEach var="item" items="${itemList}">
            		<option value='<c:out value="${item.itemCode}"/>'
            			<c:if test="${item.itemCode==TblBillsDimForm.itemCode}"> selected="selected"</c:if> >
            			<c:out value="${item.itemName}"></c:out>
            		</option>
            	</c:forEach>
            </select><span style="color: red;"><strong>*</strong></span>
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">费用：</td>
            <td class="t1"><html:text property="billAmt" maxlength="20"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">缴费时间：</td>
            <td><input type="text" name="jfDate" id="jfDate"
            value="<c:out value='${TblBillsDimForm.jfDate }'/>" onfocus="WdatePicker({dateFmt:'yyyyMMdd'});"/>
            (格式：20140214)<span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">缴费截止时间：</td>
            <td><input type="text" name="jzDate" id="jzDate"
            value="<c:out value='${TblBillsDimForm.jzDate }'/>" onfocus="WdatePicker({dateFmt:'yyyyMMdd'});"/>
            (格式：20140214)<span style="color: red;"><strong>*</strong></span>
            </td>
          </tr>
          <tr>
            <td class="t0">是否缴费：</td>
            <td class="t1">
			<html:select property="isPay">
				<html:option value="0">未缴</html:option>
				<html:option value="1">已缴</html:option>
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
