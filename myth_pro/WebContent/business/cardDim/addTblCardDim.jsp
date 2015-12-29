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
    	if(checkIsNull('0','cardNo')==false){
    		alert("房号不为空");
    		return false;
    	}
    	if(checkIsNull('0','roomCode')==false){
    		alert("房号不为空");
    		return false;
    	}
    	if(checkIsNull('0','usrName')==false){
    		alert("业主名称不为空");
    		return false;
    	}
    	if(checkIsNull('0','userNo')==false){
    		alert("身份证号码不为空");
    		return false;
    	}
    	var ownerNo = document.getElementById("userNo").value;
    	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   		if(reg.test(ownerNo) === false){  
       		alert("身份证输入不合法");  
       		return  false;  
   		}
    	if(checkIsNull('0','state')==false){
    		alert("卡状态不为空");
    		return false;
    	}
    	if(!confirm("是否已执行写卡权限操作")){
    		return false;
    	}
    	document.forms[0].submit();
    }
    function readNo(){
    	var USBCardRW = document.getElementById("USBCardRW");
        //判断发卡器是否已经连接到电脑,函数原型: int Connected();  
        var isConected=USBCardRW.Connected();
        if(isConected!=1){
          alert("发卡器未连接");
        }
        var scardno=USBCardRW.ReadCardNo();
        document.getElementById('EdtCardNo').value=scardno; 
    }
    function writeCard(){
    	var data = "";
    	var boxes = document.getElementsByName("fingop");
    	if(boxes.length>16){
    		alert("最多只能设置16个设备权限");
    		return false;
    	}
    	for(var i = 0; i < boxes.length; i++){
    		if(boxes[i].checked){
    			var obj = document.getElementById(boxes[i].value).value;
    			var reg = /^\d+$/;
    			var re = obj.match( reg );
    			if(re==null){
    				alert("门禁模块编号只能为数字");
    				return false;
    			}
    			if(parseInt(obj,10)>255){
    				alert("门禁模块编号最大为255");
    				return false;
    			}
    			var data1 = parseInt(obj,10).toString(16);
    			if(data1.length==1)
    				data1="0"+data1;
    				data = data + data1;
    			//data = data+parseInt(obj,10).toString(16);
    		}
    	}
    	if(data==""){
    		alert("请选择门禁权限");
    		return false;
    	}
    	if(data.lengt!=32){
    		for(var j=data.length;j<32;j++){
    			//data = data+"0";
    			data="0"+data;
    		}
    	}
    	//alert(data+"==============="+data.length);
    	
    	var USBCardRW = document.getElementById("USBCardRW");
    	//alert(USBCardRW.ReadDoorCard(14)+"==readcard");
    	//alert(USBCardRW.DoorAuth);
    	//USBCardRW.InitCard(14);
    	//USBCardRW.ResumeCard(14);
    	var iRes=USBCardRW.WriteDoorCard(14,data,65535,6);
    	if(iRes!=0){
    		alert("请检测门禁读卡器，写门禁卡失败");
    	}
    }
</SCRIPT>
<html:form action="/cardDim.do">
<html:hidden property="method" value="addTblCardDim" />
<%
	String strPraId =(String)request.getParameter("menuPare");
	String strChlId =(String)request.getParameter("menuChild");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<OBJECT id=USBCardRW classid="CLSID:C720F392-B44D-457C-A633-15ED97D0FEA3" width="0"  height="0"></OBJECT>  
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">门禁卡信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblCardDimForm.cardNo}">
          <tr>
            <td class="t0">门禁卡编号：</td>
            <td class="t1">
            	<c:out value="${TblCardDimForm.cardNo}"/>
            	<html:hidden property="cardNo" />
            	<html:hidden property="opType" value="1"/>
            </td>
          </tr>
          </c:if>
          <c:if test="${empty TblCardDimForm.cardNo}">
          <tr>
            <td class="t0">门禁卡编号：</td>
            <td class="t1">
            	<input id="EdtCardNo" name="cardNo" maxlength="20"/>
            	<input id="readCardNo" type="button" value="读取卡号" class="btn" onclick="readNo();"/>
            	<html:hidden property="opType" value="0"/>
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">房号：</td>
            <td class="t1">
            <select name="roomCode">
            	<option value="">请选择房号</option>
            	<c:forEach var="room" items="${RoomList}">
            	<option value='<c:out value="${room.roomCode }"/>'
            		<c:if test="${room.roomCode==TblCardDimForm.roomCode }"> selected="selected"</c:if> >
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
            <td class="t0">业主名称：</td>
            <td class="t1"><html:text property="usrName" maxlength="50"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">身份证号码：</td>
            <td class="t1">
            <input type="text" name="userNo" id="userNo" maxlength="18"
            value="<c:out value='${TblCardDimForm.userNo }'/>"/>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">状态：</td>
            <td class="t1">
				<html:select property="state">
					<html:option value="">请选择卡状态</html:option>
					<html:option value="01">在用</html:option>
					<html:option value="02">停用</html:option>
				</html:select><span style="color: red;"><strong>*</strong></span>
			</td>
          </tr>
          <tr>
            <td class="t0">开始时间：</td>
            <td class="t1"><html:text property="beginDate"
            	styleClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyyMMdd'});"></html:text>(格式如：20140118)</td>
          </tr>
          <tr>
            <td class="t0">结束时间：</td>
            <td class="t1"><html:text property="endDate"
            	styleClass="Wdate" onfocus="WdatePicker({dateFmt:'yyyyMMdd'});"></html:text>(格式如：20140118)</td>
          </tr>
          <tr>
            <td class="t0">门禁权限：</td>
            <td class="t1">
            	<c:forEach var="equ" items="${EquList}">
            		<input type="checkbox" name="fingop" value="<c:out value='${equ.equCode }'/>"
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
            		<input type="hidden" id="<c:out value='${equ.equCode }'/>"
            			name="<c:out value='${equ.equCode }'/>" 
            			value="<c:out value='${equ.equAccid }'/>"/>
            		<c:out value="${equ.equCode}"/> - <span class="outdoor" onclick="obtinOutdoorInfo('<c:out value="${equ.equAdd}"/>','<c:out value="${equ.areaID}"/>','<c:out value="${equ.commID}"/>','<c:out value="${equ.unitID}"/>','<c:out value="${equ.buldID}"/>', this)">
            		  <c:out value="${equ.equName}"/>
            		 </span> 
<!--            		<c:out value="${equ.equCode}"/>-<c:out value="${equ.equName}"/>-->
            		<br/>
            	</c:forEach>
            	<c:if test="${empty EquList}">
            		<span>没有查找到门禁数据</span>
            	</c:if>
            </td>
          </tr>
          <tr>
            <td class="t0"></td>
            <td class="t1">
				<span class="btnspan btnspanc"><input type="button" value="写卡权限" class="btn" onclick="writeCard();"></span>
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
