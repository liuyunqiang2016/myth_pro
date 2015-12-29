<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
	String commCode = (String)request.getAttribute("commCode");
	String path = request.getContextPath();
%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript" src="../js/jquery-1.4.2.min.js"></SCRIPT>
<SCRIPT language="javascript">
function guid() {
    function S4() {
       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    return (S4()+S4()+S4());
}
		function genArea(){
			var obj = $("#areaAmount").attr("value");
			if(obj==null|| obj==""){
				alert("请输入区域数量");
				return false;
			}
			var str="";
			for(var i=0;i<obj;i++){
				str=str + "<tr><td>区域编号<input type='text' id='areaCode"+i
					+"' name='areaCode"+i+"' value='"+(guid())+"' size='10'/>&nbsp;&nbsp;"+
					"区域名称<input type='text' id='areaName"+i
					+"' name='areaName"+i+"' value='"+('A'+i)+"区' size='10'/></td></tr>";
			}
			var str1="<tr><td colspan='2' align='center'>&nbsp;&nbsp;"+
				"<input type='button' value='确认' onclick='checkFrm("+obj+")'</td></tr>";
			$("#genName").html("区域生成：");
			$("#genDiv").html("<table>"+str+ str1+ "</table>");
		}
		
    	function checkFrm(obj)
    	{
    		if(obj==null || obj==""){
    			alert("请输入区域数量");
				return false;
    		}
    		var areaCode="";
    		var areaName="";
    		for(var i=0;i<obj;i++){
    			var obj1 = $("#areaCode"+i).attr("value");
    			var obj2 = $("#areaName"+i).attr("value");
    			if(obj1==null || obj1==""){
    				alert("区域编号不为空");
					return false;
    			}
    			var reg = /^[a-zA-Z0-9]*[a-zA-Z0-9]$/;
    			if(reg.test(obj1)==false){
    				alert("区域编号只能由数字或字母组成");
    				return false;
    			}
    			if(obj2==null || obj2==""){
    				alert("区域名称不为空");
					return false;
    			}
    			areaCode = areaCode + obj1+",";
    			areaName = areaName + obj2+",";
    		}
    		$.get("<%=path%>/areaDim.do?method=checkAreaGen",{commCode:<%=commCode%> ,areaCode:areaCode},
    			function(data){
    				var obj = eval("("+data+")");
    				if(obj.msg!="success"){
    					alert(obj.msg);
    				}else{
    					var form = document.forms[0];
    					form.action="<%=path%>/areaDim.do?method=addBatchAreaDim&commCode=" + <%=commCode%> 
    						+"&areaCode="+areaCode+"&areaName="+areaName;
    					form.submit();
    				}
    		});
    	}
</SCRIPT>
<html:form action="/areaDim.do">
<html:hidden property="method" value="addTblAreaDim" />
<html:hidden property="areaCode" />
<%
	String strPraId ="";
	String strChlId = "";
	strPraId = (String)request.getParameter("menuPare");
	strChlId = (String)request.getParameter("menuChild");
	String commName = (String)request.getParameter("commName");
 %>
<html:hidden property="menuPare" value="<%=strPraId %>"/>
<html:hidden property="menuChild" value="<%=strChlId %>"/>
<div id="rightcontent">
  <div class="inner">
    <div class="tablecontent2">
      <table class="table" cellpadding="0" bordercolor="#dbdbdb" cellspacing="0" border='1'>
        <thead>
          <tr>
            <th colspan="2" class="th0">小区信息详情
            <input type="hidden" name="commName" id="commName" value="<%=request.getAttribute("commName") %>"/>
            </th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">小区名称：</td>
            <td class="t1"><%=request.getAttribute("commName") %></td>
          </tr>
          <tr>
            <td class="t0">区域数量：</td>
            <td class="t1"><input type="text" name="areaAmount" id="areaAmount"/>
			<input type="button" value="生成" onclick="genArea();"  class="btn"/></td>
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