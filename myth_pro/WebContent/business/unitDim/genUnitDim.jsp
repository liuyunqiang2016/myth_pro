<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String commCode = (String)request.getAttribute("commCode");
	String buildingCode = (String)request.getAttribute("buildingCode");
	String areaCode = (String)request.getAttribute("areaCode");
	%>
<jsp:include page="/share/top.jsp" />
<SCRIPT language="javascript" src="/js/jquery-1.4.2.min.js"></SCRIPT>
<SCRIPT language="javascript">
		function genUnit(){
			var obj = $("#UnitAmount").attr("value");
			if(obj==null|| obj==""){
				alert("请输入单元数量");
				return false;
			}
			var str="";
			for(var i=0;i<obj;i++){
				str=str + "<tr><td>单元编号<input type='text' id='unitCode"+i
					+"' name='unitCode"+i+"' value='"+(i+1)+"' size='10'/>&nbsp;&nbsp;"+
					"单元名称<input type='text' id='unitName"+i
					+"' name='unitName"+i+"' value='"+(i+1)+"单元' size='10'/></td></tr>";
			}
			var str1="<tr><td colspan='2' align='center'>&nbsp;&nbsp;"+
				"<input type='button' value='确认' onclick='checkFrm("+obj+")'</td></tr>";
			$("#genName").html("单元生成：");
			$("#genDiv").html("<table>"+str+ str1+ "</table>");
		}
		
    	function checkFrm(obj)
    	{
    		if(obj==null || obj==""){
    			alert("请输入单元数量");
				return false;
    		}
    		var unitCode="";
    		var unitName="";
    		for(var i=0;i<obj;i++){
    			var obj1 = $("#unitCode"+i).attr("value");
    			var obj2 = $("#unitName"+i).attr("value");
    			if(obj1==null || obj1==""){
    				alert("单元编号不为空");
					return false;
    			}
    			var reg = /^[a-zA-Z0-9]*[a-zA-Z0-9]$/;
    			if(reg.test(obj1)==false){
    				alert("单元编号只能由数字或字母组成");
    				return false;
    			}
    			if(obj2==null || obj2==""){
    				alert("单元名称不为空");
					return false;
    			}
    			unitCode = unitCode + obj1+",";
    			unitName = unitName + obj2+",";
    		}
    		$.get("<%=path%>/unitDim.do?method=checkUnitGen",
    			{commCode:<%=commCode%> ,areaCode:<%=areaCode%>,buildingCode:<%=buildingCode%>,
    			unitCode:unitCode},
    			function(data){
    				var obj = eval("("+data+")");
    				if(obj.msg!="success"){
    					alert(obj.msg);
    				}else{
    					var form = document.forms[0];
    					form.action="<%=path%>/unitDim.do?method=addBatchUnitDim&commCode=" + <%=commCode%> 
    						+"&unitCode="+unitCode+"&unitName="+unitName
    						+"&buildingCode=" + <%=buildingCode%> + "&areaCode=" + <%=areaCode%>;
    					form.submit();
    				}
    		});
    	}
</SCRIPT>
<html:form action="/unitDim.do">
<html:hidden property="method" value="addTblUnitDim" />
<html:hidden property="unitCode" />
<html:hidden property="commCode" />
<html:hidden property="buildingCode" />
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
            <th colspan="2" class="th0">单元信息详情</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td class="t0">所属小区：</td>
            <td class="t1"><c:out value="${TblUnitDimForm.commName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">所属区域：</td>
            <td class="t1"><c:out value="${TblUnitDimForm.areaName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">所属楼宇：</td>
            <td class="t1"><c:out value="${TblUnitDimForm.buildingName}"></c:out></td>
          </tr>
          <tr>
            <td class="t0">单元数量：</td>
            <td class="t1"><input type="text" name="UnitAmount" id="UnitAmount"/>
			<input type="button" value="生成" onclick="genUnit();"  class="btn"/></td>
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
