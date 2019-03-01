<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/login.css">
<script type="text/javascript" src="js/login.js"></script>
<title>用户登录</title>
</head>
<body>
<body id="lgBody">
	<%
	String loginError = (String)request.getAttribute("loginError");
	if(loginError==null)
		loginError = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	%>
	<div id="lgDiv1">
		<div id="lgDiv2">
			<jsp:include page="navigation.html" />
			<jsp:include page="state.jsp" />
		</div>
		<div id="lgDiv3">
			<div id="lgDiv4">
 				<form action="Login.do" method="post" name="loginInfo"> 
<!-- 				<form name="loginInfo"> -->
					<input id="lginput1" type="text" name="username" placeholder="用户名" oninput="inputAgain(event)"
					onpropertychange="inputAgain(event)"><br/> 
					<input id="lginput2" type="password" name="password" placeholder="密   码" oninput="inputAgain(event)"
					onpropertychange="inputAgain(event)"> <br/> 
					<input id="lginput3" type="checkbox" name="autoLogin" value="yes">自动登录 
					<input id="lginput4" type="checkbox" name="rememberPw" value="yes">记住密码<br/>
					<span id="error"><%= loginError %></span>
 					<input id="lginput5" type="submit" value="登     录" onclick="return isLoginInfoOk()"> <br/>  
<!--  					<button id="lginput5" onclick="doSubmit('Login.do')">登录</button><br/>  -->
<!-- 					<a href="javascript:void(0);" onclick="doSubmit('Login.do')">登录</a> -->
					<a href="#">忘记密码?</a>&nbsp;&nbsp;or&nbsp;&nbsp;<a href="register.jsp">未注册?</a>
				</form>
			</div>
		</div>
	<div id="lgDiv5">
		<h4>Made by <a href="#">Sunshine</a>&nbsp;&nbsp;and&nbsp;&nbsp;<a href="#">Orange</a></h4>
		<span>2017 - 11 - 10</span>
	</div>
	</div>
</body>
</body>
</html>