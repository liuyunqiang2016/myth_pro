<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
%>
<jsp:directive.page import="com.viatt.zhjtpro.bo.*" />
<jsp:directive.page import="java.util.List" />
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript">
	function checkFrm()
    {
    	if(checkIsNull('0','usrNo')==false){
    		alert("证件号码不为空");
    		return false;
    	}
    	if(checkIsNull('0','fingUsr')==false){
    		alert("姓名不为空");
    		return false;
    	}
    	if(checkIsNull('0','roomCode')==false){
    		alert("房号不为空");
    		return false;
    	}
    	if(checkIsNull('0','fingImg1')==false){
    		alert("指纹1不为空");
    		return false;
    	}
    	if(checkIsNull('0','fingImg2')==false){
    		alert("指纹2不为空");
    		return false;
    	}
    	if(checkIsNull('0','fingState')==false){
    		alert("指纹状态不为空");
    		return false;
    	}
    	document.forms[0].submit();
    }
    /*
    设备类型: 2 (0：有驱动USB设备，1：串口设备，2：无驱UDISK设备)
    串口号(1-16)：1  (当设备类型值为1时有效)
    波特率: 6   (当设备类型值为1时有效可传入：1:9600/9600,2:19200/9600,4:38400/9600,6:57600/9600,12:115200/9600)
    特征值长度 : 512 (设置为512或1024)
    */
    function disImg1(){
    	var equType = document.getElementById("equType").value;
    	var commPort = document.getElementById("commPort").value;
    	//document.forms[0].finger.spDeviceType="2";
		//document.forms[0].finger.spComPort="1";
		document.forms[0].finger.spDeviceType=equType;
		document.forms[0].finger.spComPort=commPort;
		document.forms[0].finger.spBaudRate="6";
		document.forms[0].finger.CharLen=512;
		document.forms[0].finger.ZAZGetImgCode();
		document.forms[0].fingImg1.value=document.forms[0].finger.FingerCode;
    }
    function disImg2(){
    	var equType = document.getElementById("equType").value;
    	var commPort = document.getElementById("commPort").value;
    	document.forms[0].finger.spDeviceType=equType;
		document.forms[0].finger.spComPort=commPort;
		document.forms[0].finger.spBaudRate="6";
		document.forms[0].finger.CharLen=512;
		document.forms[0].finger.ZAZGetImgCode();
		document.forms[0].fingImg2.value=document.forms[0].finger.FingerCode;
    }
    
    function Match()
	{
		var spSrc="";
		var spDst="";
		var spResult=0;
		spSrc=document.forms[0].fingImg1.value
		spDst=document.forms[0].fingImg2.value;
		spResult=document.forms[0].finger.ZAZCharMatch(spSrc,spDst);
		
		$("#fingerMatch").val(spResult);
	}
</SCRIPT>
<html:form action="/fingDim.do">
<html:hidden property="method" value="addTblFingDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<object codebase="ZAZFingerActivexT.cab#version=1,0,1,1" classid="clsid:35515A76-3049-4D2A-8457-FD83173037E9" name="finger" width="0"  height="0" id="finger" accesskey="a" tabindex="0"  title="finger">
  </object>
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">指纹门禁信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblFingDimForm.usrNo}">
          <tr>
            <td class="t0">证件号码：</td>
            <td class="t1">
            	<c:out value="${TblFingDimForm.usrNo}"/>
            	<html:hidden property="usrNo" />
            	<html:hidden property="opType" value="0"/>
            </td>
          </tr>
          </c:if>
          <c:if test="${empty TblFingDimForm.usrNo}">
          <tr>
            <td class="t0">证件号码：</td>
            <td class="t1">
            	<html:text property="usrNo" maxlength="20"/><span style="color: red;"><strong>*</strong></span>
            	<html:hidden property="opType" value="1"/>
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">姓名：</td>
            <td class="t1"><html:text property="fingUsr" maxlength="80"></html:text><span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">房号：</td>
            <td class="t1">
            <select name="roomCode">
            	<option value="">请选择房号</option>
            	<c:forEach var="room" items="${RoomList}">
            	<option value='<c:out value="${room.roomCode }"/>'
            		<c:if test="${room.roomCode==TblFingDimForm.roomCode }"> selected="selected"</c:if> >
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
          <tr>
          	<td class="t0">设备类型:</td>
          	<td class="t1">
          	<select name="equType" id="equType">
          		<option value="0">0-有驱动USB设备</option>
          		<option value="1">1-串口设备</option>
          		<option value="2">2-无驱UDISK设备</option>
          	</select>
          	</td>
          </tr>
          <tr>
          	<td class="t0">串口号:</td>
          	<td class="t1">
          	<select name="commPort" id="commPort">
          		<option value="1">1</option>
          		<option value="2">2</option>
          		<option value="3">3</option>
          		<option value="4">4</option>
          		<option value="5">5</option>
          		<option value="6">6</option>
          		<option value="7">7</option>
          		<option value="8">8</option>
          		<option value="9">9</option>
          		<option value="10">10</option>
          		<option value="11">11</option>
          		<option value="12">12</option>
          		<option value="13">13</option>
          		<option value="14">14</option>
          		<option value="15">15</option>
          		<option value="16">16</option>
          	</select>
          	</td>
          </tr>
          <tr>
            <td class="t0">指纹1：</td>
            <td class="t1">
              <html:textarea property="fingImg1" rows="5" cols="50" readonly="true"></html:textarea>
            <input id="fingerCode1" type="button" value="取指纹1" class="btn" onclick="disImg1();"/>
            <span style="color: red;"><strong>*</strong></span>
            </td>
          </tr>
          <tr>
            <td class="t0">指纹2：</td>
            <td class="t1"><html:textarea property="fingImg2" rows="5" cols="50" readonly="true"></html:textarea>
            <input id="fingerCode2" type="button" value="取指纹2" class="btn" onclick="disImg2();"/>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">验证指纹是否匹配：</td>
            <td class="t1">
            <input  readonly="readonly"  maxlength="20" id="fingerMatch"/> &nbsp&nbsp(100以下为不通过) &nbsp&nbsp&nbsp
             <input  type="button" value="验证两码特征" class="btn"  id="btnMatch" onclick="Match();"/>
           </td>
          </tr>
          <tr>
            <td class="t0">状态：</td>
            <td class="t1">
            <html:select property="fingState">
					<html:option value="">请选择指纹状态</html:option>
					<html:option value="01">在用</html:option>
					<html:option value="02">停用</html:option>
				</html:select><span style="color: red;"><strong>*</strong></span>
           </td>
          </tr>
          <tr>
            <td class="t0">门禁权限：</td>
            <td class="t1">
            	<c:forEach var="equ" items="${EquList}">
<%--            		<input type="checkbox" name="fingop" value="<c:out value='${equ.equCode }'/>"--%>
            		<input type="checkbox" name="fingop"  value="<c:out value='${equ.equCode }'/>"
            			<%
            				List fingop = (List)request.getAttribute("FingOpList");
          					TblEquDimBo op = (TblEquDimBo)pageContext.getAttribute("equ");
          					for(int i=0;i<fingop.size();i++){
          						TblFingOpBo bo1 = (TblFingOpBo)fingop.get(i);
          						if(bo1.getEquCode().equals(op.getEquCode())){
          						%>
									checked="checked"
								<%
									break;
          						}
          					}
            			 %>
            		/>
            		 <c:out value="${equ.equCode}"/> - <span class="outdoor" onclick="obtinOutdoorInfo('<c:out value="${equ.equAdd}"/>','<c:out value="${equ.areaID}"/>','<c:out value="${equ.commID}"/>','<c:out value="${equ.unitID}"/>','<c:out value="${equ.buldID}"/>', this)">
            		  <c:out value="${equ.equName}"/>
            		 </span> 
            		<br/>
            	</c:forEach>
            	<c:if test="${empty EquList}">
            		<span>没有查找到门禁数据</span>
            	</c:if>
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
<!-- 显示房号 -->
<SCRIPT language="javascript" type="text/javascript">	
	$(document).ready(function(){
		$(".outdoor").each(function(){
			$(this).trigger("click") ;
		});
	}) ;

	function obtinOutdoorInfo(roomCodeP, areaCodeP,commCodeP, unitCodeP,  buildingCodeP, object){
		$.getJSON("<%=path%>/roomDim.do?method=getJsonOutdoorInfo",{roomCode:roomCodeP, areaCode:areaCodeP, commCode:commCodeP, unitCode:unitCodeP, buildingCode:buildingCodeP }, function(json){
		  if(json != null) {
			 $(object).html(json.roominfo) ;
		  }
		});
	}
</SCRIPT>
