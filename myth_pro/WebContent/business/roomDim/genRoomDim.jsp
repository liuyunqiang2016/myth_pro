<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String commCode = (String)request.getAttribute("commCode");
String areaCode = (String)request.getAttribute("areaCode");
String buildingCode = (String)request.getAttribute("buildingCode");
String unitCode = (String)request.getAttribute("unitCode");
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript" src="/js/jquery-1.4.2.min.js"></SCRIPT>
<SCRIPT language="javascript">
		function genRoom(){
			var obj = $("#roomAmount").attr("value");
			if(obj==null|| obj==""){
				alert("请输入房号数量");
				return false;
			}
			var str="";
			for(var i=0;i<obj;i++){
				str=str + "<tr><td>房号<input type='text' id='roomCode"+i
					+"' name='roomCode"+i+"' value='"+(i+1)+"' size='10'/></td>"
					+"<td>户型<select name='htCode"+i+"' id='htCode"+i+"' >"
					+"<c:forEach var='ht' items='${htlist}' varStatus='e'>"
					+"<option value='"+"<c:out value='${ht.htCode}'/>"+"'>"
					+"<c:out value='${ht.htName}'/></option></c:forEach>"
					+"</select></td></tr>";
			}
			var str1="<tr><td align='center'>&nbsp;&nbsp;"+
				"<input type='button' value='确认' onclick='checkFrm("+obj+")'</td></tr>";
			$("#genName").html("房号生成：");
			$("#genDiv").html("<table>"+str+ str1+ "</table>");
		}
		
    	function checkFrm(obj)
    	{
    		if(obj==null || obj==""){
    			alert("请输入房号数量");
				return false;
    		}
    		var roomCode="";
    		var htCode="";
    		for(var i=0;i<obj;i++){
    			var obj1 = $("#roomCode"+i).attr("value");
    			var obj2 = $("#htCode"+i).attr("value");
    			if(obj1==null || obj1==""){
    				alert("房号不为空");
					return false;
    			}
    			var reg = /^[a-zA-Z0-9]*[a-zA-Z0-9]$/;
    			if(reg.test(obj1)==false){
    				alert("房号只能由数字或字母组成");
    				return false;
    			}
    			if(obj2==null || obj2==""){
    				alert("户型不为空");
					return false;
    			}
    			roomCode = roomCode + obj1+",";
    			htCode = htCode + obj2+",";
    		}
    		$.get("<%=path%>/roomDim.do?method=checkRoomGen",
    			{commCode:<%=commCode%> ,areaCode:<%=areaCode%>,buildingCode:<%=buildingCode%>,
    			unitCode:<%=unitCode%>,roomCode:roomCode},
    			function(data){
    				var obj = eval("("+data+")");
    				if(obj.msg!="success"){
    					alert(obj.msg);
    				}else{
    					var form = document.forms[0];
    					form.action="<%=path%>/roomDim.do?method=addBatchRoomDim&htCode="+htCode+"&commCode=" 
    						+ <%=commCode%> +"&areaCode=" + <%=areaCode%>+"&unitCode=" + <%=unitCode%>
    						+"&buildingCode=" + <%=buildingCode%> +"&roomCode="+ roomCode ;
    					form.submit();
    				}
    		});
    	}
</SCRIPT>
<html:form action="/roomDim.do">
<html:hidden property="method" value="addTblRoomDim" />
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
            <th colspan="2" class="th0">房号生成信息详情</th>
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
            <td class="t0">房号数量：</td>
            <td class="t1"><input type="text" name="roomAmount" id="roomAmount"/>
			<input type="button" value="生成" onclick="genRoom();"  class="btn"/></td>
          </tr>
          <tr>
            <td class="t0">&nbsp;<span id="genName"></span></td>
            <td class="t1">&nbsp;<div id="genDiv"></div></td>
          </tr>
        </tbody>
      </table>
    </div>
    
  </div>
 </div>
 <div class="clear"></div>
</html:form>
<jsp:include page="/share/foot.jsp" />
