<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
	String logName = (String)request.getAttribute("logName");
	if(logName==null || logName.equals("")){
		logName="";
	}
	String emailAddress = (String)request.getAttribute("emailAddress");
	if(emailAddress==null || emailAddress.equals("")){
		emailAddress="";
	}
	String telNum = (String)request.getAttribute("telNum");
	if(telNum==null || telNum.equals("")){
		telNum="";
	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>物业管理系统_密思科技</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="css/login.css" type="text/css" rel="stylesheet">
		<script type="text/javascript" src="./js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript">
		function checkFrm()
		{
			if($("#logName").val() == ""){
	    		alert("请输入帐号信息");
	    		return false;
	    	}
			
			if($("#emailAddress").val() == ""){
	    		alert("请输入邮箱信息");
	    		return false;
	    	}
			if($("#telNum").val() == ""){
	    		alert("请输入电话信息");
	    		return false;
	    	}
			document.forms[0].submit();
		}
		</script>
		<style type="">
			#footer{ padding:10px 0px; text-align:center; line-height:20px;background:url(<%=path%>/pic/body_bg.jpg) repeat-x 0px -60px;}
			#footer p{ color:White;}
		</style>
	</head>

	<body>
		<div id="logincontent">
		<form method="post" action="<%=path%>/user.do?method=findPassword">
			<ul>
				<li>
					<label>
						帐号:
					</label>
					<input type="text" class="text" id="logName" name="logName" value="<%=logName%>" maxlength="20">
				</li>
				<li>
					<label>
						邮箱:
					</label>
					<input type="text" class="text" id="emailAddress" name="emailAddress" value="<%=emailAddress%>" maxlength="20">
				</li>
				<li>
					<label>
						电话:
					</label>
					<input type="text" class="text" id="telNum" name="telNum" value="<%=telNum%>" maxlength="20">
				</li>
				<li class="btnli">
					<input type="button" value="验证信息" style="margin:0px 3px 0px 44px; height:28px; cursor:pointer;width:80px;" onclick="checkFrm()">
				</li>
			</ul>
		  </form>
		</div>
		<jsp:include page="/share/foot.jsp" />
		<script type="text/javascript">
			<%
				String msg = (String)request.getAttribute("message");
				if(msg!=null&&!msg.equals("")){
			%>
			alert('<%=msg%>');
			<%
				}
			%>
		</script>
	</body>
</html>

