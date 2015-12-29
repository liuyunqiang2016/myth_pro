<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String commCode = (String)request.getAttribute("commCode");
  String areaCode = (String)request.getAttribute("areaCode");
 %>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript" src="../js/jquery-1.4.2.min.js"></SCRIPT>
<SCRIPT language="javascript">
		function genBuilding(){
			var obj = $("#buildingAmount").attr("value");
			if(obj==null|| obj==""){
				alert("请输入楼宇数量");
				return false;
			}
			var str="";
			for(var i=0;i<obj;i++){
				str=str + "<tr><td>楼宇编号<input type='text' id='buildingCode"+i
					+"' name='buildingCode"+i+"' value='"+(i+1)+"' size='10'/>&nbsp;&nbsp;"+
					"楼宇名称<input type='text' id='buildingName"+i
					+"' name='buildingName"+i+"' value='楼"+('0'+i+1)+"' size='10'/></td></tr>";
			}
			var str1="<tr><td colspan='2' align='center'>&nbsp;&nbsp;"+
				"<input type='button' value='确认' onclick='checkFrm("+obj+")'</td></tr>";
			$("#genName").html("楼宇生成：");
			$("#genDiv").html("<table>"+str+ str1+ "</table>");
		}
		
    	function checkFrm(obj)
    	{
    		if(obj==null || obj==""){
    			alert("请输入楼宇数量");
				return false;
    		}
    		var buildingCode="";
    		var buildingName="";
    		for(var i=0;i<obj;i++){
    			var obj1 = $("#buildingCode"+i).attr("value");
    			var obj2 = $("#buildingName"+i).attr("value");
    			if(obj1==null || obj1==""){
    				alert("楼宇编号不为空");
					return false;
    			}
    			var reg = /^[a-zA-Z0-9]*[a-zA-Z0-9]$/;
    			if(reg.test(obj1)==false){
    				alert("楼宇编号只能由数字或字母组成");
    				return false;
    			}
    			if(obj2==null || obj2==""){
    				alert("楼宇名称不为空");
					return false;
    			}
    			buildingCode = buildingCode + obj1+",";
    			buildingName = buildingName + obj2+",";
    		}
    		$.get("<%=path%>/buildingDim.do?method=checkBuildingGen",
    			{commCode:<%=commCode%> ,areaCode:<%=areaCode%>,buildingCode:buildingCode},
    			function(data){
    				var obj = eval("("+data+")");
    				if(obj.msg!="success"){
    					alert(obj.msg);
    				}else{
    					var form = document.forms[0];
    					form.action="<%=path%>/buildingDim.do?method=addBatchBuildingDim&commCode=" + <%=commCode%> 
    						+"&buildingCode="+buildingCode+"&buildingName="
    						+buildingName+"&areaCode="+<%=areaCode%>;
    					form.submit();
    				}
    		});
    	}
</SCRIPT>
<html:form action="/areaDim.do">
<html:hidden property="method" value="addTblAreaDim" />
<html:hidden property="areaCode" />
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
            <th colspan="2" class="th0">区域信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">小区名称：</td>
            <td class="t1"><%=request.getAttribute("commName") %></td>
          </tr>
          <tr>
            <td class="t0">区域名称：</td>
            <td class="t1"><%=request.getAttribute("areaName") %></td>
          </tr>
          <tr>
            <td class="t0">楼宇数量：</td>
            <td class="t1"><input type="text" name="buildingAmount" id="buildingAmount"/>
			<input type="button" value="生成" onclick="genBuilding();"  class="btn"/></td>
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
</div>
</html:form>
<jsp:include page="/share/foot.jsp" />
