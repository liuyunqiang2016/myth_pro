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
<script type="text/javascript" src="<%=path%>/js/jquery-1.7.1.min.js"></script>
<SCRIPT language="javascript">
	function checkFrm()
    {
    	if(checkIsNull('0','ownerName')==false){
    		alert("业主名称不为空");
    		return false;
    	}
    	if(checkIsNull('0','ownerNo')==false){
    		alert("身份证号码不为空");
    		return false;
    	}
    	var ownerNo = document.getElementById("ownerNo").value;
    	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
   		if(reg.test(ownerNo) === false){  
       		alert("身份证输入不合法");  
       		return  false;  
   		}
    	document.forms[0].submit();
    }
	function changeComm(){
		var areaselect = $("select[name='areaCode']");
		var unitselect = $("select[name='unitCode']");
		var buildingselect = $("select[name='buildingCode']");
		var roomselect = $("select[name='roomId']");
		areaselect.empty();
		unitselect.empty();
		buildingselect.empty();
		roomselect.empty();
		var tempOption = "<option value=''>--请选择--</option>";
		areaselect.append(tempOption);
		unitselect.append(tempOption);
		buildingselect.append(tempOption);
		roomselect.append(tempOption);
		
		var commCodeStr = $("select[name='commCode']").val();
		var url = '<%=path%>/areaDim.do?method=findAreaDimList';
        var params = {
       		commCode:commCodeStr
        }
        $.post(
                url,        //服务器要接受的url
                params,     //传递的参数
                function cbf(data){ //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据
                	var select = $("select[name='areaCode']");
					for (var i = 0; i < data.length; i++) {
						var tempOption = "<option value=\"" + data[i].areaCode + "\">" + data[i].areaName + "</option>";
						select.append(tempOption);
					}
		},
		'json' //数据传递的类型  json
		);
	}
	function changeArea(){
		var unitselect = $("select[name='unitCode']");
		var buildingselect = $("select[name='buildingCode']");
		var roomselect = $("select[name='roomId']");
		unitselect.empty();
		buildingselect.empty();
		roomselect.empty();
		var tempOption = "<option value=''>--请选择--</option>";
		unitselect.append(tempOption);
		buildingselect.append(tempOption);
		roomselect.append(tempOption);
		
		var commCodeStr = $("select[name='commCode']").val();
		var areaCodeStr = $("select[name='areaCode']").val();
		var url = '<%=path%>/unitDim.do?method=findUnitDimList';
        var params = {
       		commCode:commCodeStr,
       		areaCode:areaCodeStr
        }
        $.post(
                url,        //服务器要接受的url
                params,     //传递的参数
                function cbf(data){ //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据
                	var select = $("select[name='unitCode']");
					for (var i = 0; i < data.length; i++) {
						var tempOption = "<option value=\"" + data[i].unitCode + "\">" + data[i].unitName + "</option>";
						select.append(tempOption);
					}
		},
		'json' //数据传递的类型  json
		);
	}
	function changeUnit(){
		var buildingselect = $("select[name='buildingCode']");
		var roomselect = $("select[name='roomId']");
		buildingselect.empty();
		roomselect.empty();
		var tempOption = "<option value=''>--请选择--</option>";
		buildingselect.append(tempOption);
		roomselect.append(tempOption);
		
		var commCodeStr = $("select[name='unitCode']").val();
		var commCodeStr = $("select[name='commCode']").val();
		var areaCodeStr = $("select[name='areaCode']").val();
		var url = '<%=path%>/buildingDim.do?method=findBuildingDimList';
        var params = {
        		commCode:commCodeStr,
           		areaCode:areaCodeStr
        }
        $.post(
                url,        //服务器要接受的url
                params,     //传递的参数
                function cbf(data){ //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据
                	var select = $("select[name='buildingCode']");
					for (var i = 0; i < data.length; i++) {
						var tempOption = "<option value=\"" + data[i].buildingCode + "\">" + data[i].buildingName + "</option>";
						select.append(tempOption);
					}
		},
		'json' //数据传递的类型  json
		);
	}
	function changeBuilding(){
		var roomselect = $("select[name='roomId']");
		roomselect.empty();
		var tempOption = "<option value=''>--请选择--</option>";
		roomselect.append(tempOption);
		
		var buildingCode = $("select[name='buildingCode']").val();
		var unitCode = $("select[name='unitCode']").val();
		var commCodeStr = $("select[name='commCode']").val();
		var areaCodeStr = $("select[name='areaCode']").val();
		var url = '<%=path%>/roomDim.do?method=findRoomDimList';
        var params = {
        		commCode:commCodeStr,
           		areaCode:areaCodeStr,
           		buildingCode:buildingCode,
           		unitCode:unitCode
        }
        $.post(
                url,        //服务器要接受的url
                params,     //传递的参数
                function cbf(data){ //服务器返回后执行的函数 参数 data保存的就是服务器发送到客户端的数据
                	var select = $("select[name='roomId']");
					for (var i = 0; i < data.length; i++) {
						var tempOption = "<option value=\"" + data[i].roomCode + "\">" + data[i].roomCode + "</option>";
						select.append(tempOption);
					}
		},
		'json' //数据传递的类型  json
		);
	}
</SCRIPT>
<html:form action="/ownerDim.do">
<html:hidden property="method" value="addTblOwnerDim" />
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">业主信息详情</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${!empty TblOwnerDimForm.ownerCode}">
          <tr>
            <td class="t0">业主编号：</td>
            <td class="t1">
            	<c:out value="${TblOwnerDimForm.ownerCode}"/>
            	<html:hidden property="ownerCode" />
            </td>
          </tr>
          </c:if>
          <tr>
            <td class="t0">业主名称：</td>
            <td class="t1"><html:text property="ownerName" maxlength="80"></html:text>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">业主性别：</td>
            <td class="t1">
            <html:select property="ownerSex">
            	<html:option value="0">男</html:option>
            	<html:option value="1">女</html:option>
            </html:select>
            </td>
          </tr>
          <tr>
            <td class="t0">身份证号码：</td>
            <td class="t1">
            <input type="text" name="ownerNo" id="ownerNo" value="<c:out value='${TblOwnerDimForm.ownerNo }'/>" maxlength="18"/>
            <span style="color: red;"><strong>*</strong></span></td>
          </tr>
          <tr>
            <td class="t0">出生日期：</td>
            <td class="t1">
            <input type="text" name="ownerDate" id="ownerDate"
            value="<c:out value='${TblOwnerDimForm.ownerDate }'/>"
            onfocus="WdatePicker({dateFmt:'yyyyMMdd'});"/> 
            (格式如：20140118)
            </td>
          </tr>
          <tr>
            <td class="t0">家庭电话：</td>
            <td class="t1"><html:text property="ownerTel" maxlength="20"></html:text></td>
          </tr>
          <tr>
            <td class="t0">移动电话：</td>
            <td class="t1">
            <html:text property="ownerMob" maxlength="20"></html:text>
           	 (是否开通手机权限  <html:select property="isSipAcount">
            	<html:option value="0">是</html:option>
            	<html:option value="1">否</html:option>
            </html:select>)
            </td>
          </tr>
          <tr>
            <td class="t0">邮编：</td>
            <td class="t1"><html:text property="ownerPc" maxlength="20"></html:text></td>
          </tr>
          <tr>
            <td class="t0">地址：</td>
            <td class="t1"><html:text property="ownerAdds" maxlength="50"></html:text></td>
          </tr>
          <tr>
            <td class="t0">房间信息：</td>
            <td class="t1">
            	小区:<html:select property="commCode" onchange="changeComm();">
            		<html:option value="">--请选择--</html:option>
            		<html:options 
					   collection="commDimList"
					   property="commCode"
					   labelProperty="commName"/>
           		 </html:select>
           		 区域:<html:select property="areaCode" onchange="changeArea();">
            	<html:option value="">--请选择--</html:option>
           		 </html:select>
           		 单元:<html:select property="unitCode" onchange="changeUnit();">
            	<html:option value="">--请选择--</html:option>
           		 </html:select>
           		 楼宇:<html:select property="buildingCode" onchange="changeBuilding();">
            	<html:option value="">--请选择--</html:option>
           		 </html:select>
           		  房号:<html:select property="roomId">
            	<html:option value="">--请选择--</html:option>
           		 </html:select>
            </td>
          </tr>
          <tr>
            <td class="t0">家属联系方式：</td>
            <td class="t1"><html:text property="ownerRelationNumber" maxlength="120"></html:text></td>
          </tr>
          <tr>
            <td class="t0">门禁卡ID：</td>
            <td class="t1"><html:text property="cardId" maxlength="50"></html:text></td>
          </tr>
          <tr>
            <td class="t0">指纹ID：</td>
            <td class="t1"><html:text property="fingId" maxlength="50"></html:text></td>
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
