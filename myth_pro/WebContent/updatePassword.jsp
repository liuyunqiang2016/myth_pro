<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
	String logName = (String)request.getAttribute("logName");
	if(logName==null || logName.equals("")){
		logName="";
	}
	String logPwd = (String)request.getAttribute("logPwd");
	if(logPwd==null || logPwd.equals("")){
		logPwd="";
	}
 %>
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
			if($("#newPwd").val() == ''|| $("#newPwd").val() == null)
    		{
    			alert('新密码不能为空');
    			return false;
    		}
    		if($("#confirmPwd").val() == '' || $("#confirmPwd").val() == null)
    		{
    			alert('确认密码不能为空');
    			return false;
    		}
    		if($("#confirmPwd").val() != $("#newPwd").val())
    		{
    			alert('两次输入的密码不一样');
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
		<form method="post" action="<%=path%>/user.do?method=updatePw">
			<input type="hidden" class="text" id="logName" name="logName" value="<%=logName%>" maxlength="20">
			<ul>
				<li>
					<label>
						输入密码:
					</label>
					<input type="text" class="text" id="newPwd" name="newPwd" value="" maxlength="20">
				</li>
				<li>
					<label>
						重复输入密码:
					</label>
					<input type="text" class="text" id="confirmPwd" name="confirmPwd" value="" maxlength="20">
				</li>
				<li class="btnli">
					<input type="button" value="确认修改" style="margin:0px 3px 0px 44px; height:28px; cursor:pointer;width:80px;" onclick="checkFrm()">
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