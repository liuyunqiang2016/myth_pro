<%@ page language="java" pageEncoding="UTF-8"%>
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
			//刷新验证码  
			function refresh()  
			{  
				document.getElementById("authImg").src="authImg?now="+new Date();//使用时间作为参数避免浏览器从缓存取图片  
			}
			function reset()
			{
				document.getElementById("authImg").src="authImg?now="+new Date();
				document.getElementByName("logName").value="";
				document.getElementByName("logPwd").value="";
				document.getElementByName("authCode").value="";
			}
			function submit()
			{
				alert("aaaaaa");
				//document.forms[0].submit();
			}
		</script>
		<style type="">
			#footer{ padding:10px 0px; text-align:center; line-height:20px;background:url(<%=path%>/pic/body_bg.jpg) repeat-x 0px -60px;}
			#footer p{ color:White;}
		</style>
	</head>

	<body>
		<div id="logincontent">
		<form method="post" action="<%=path%>/user.do?method=login">
			<ul>
				<li>
					<label>
						帐号:
					</label>
					<input type="text" class="text" id="logName" name="logName" value="<%=logName %>" maxlength="20">
				</li>
				<li>
					<label>
						密码:
					</label>
					<input type="password" class="text" id="logPwd" name="logPwd" value="<%=logPwd %>" maxlength="20">
				</li>
				<li>
					<label>
						验证码:
					</label>
					<input type="text" class="text2" name="authCode" maxlength="4">
					<img src="authImg" id="authImg" onclick="refresh()"
						align="absmiddle" width="80" height="20">
				</li>
				<li>
					<a href="<%=path%>/findPassword.jsp">
						<span id="fingPassword" style="float:right;color:White;margin-right:66px;margin-top:-5px;cursor:pointer;">忘记密码？</span>
					</a>
				</li>
				<li class="btnli">
					<input type="submit" value="" class="btn1" onclick="submit()">
					<input type="button" class="btn2" onclick="reset()">
				</li>
			</ul>
		  </form>
		</div>
		<jsp:include page="/share/foot.jsp"/>
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
