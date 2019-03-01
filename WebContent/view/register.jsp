<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/register.css">
<script type="text/javascript" src="js/register.js"></script>
<title>注册</title>
</head>
<body>
<body id="rgBody">
	<div id="rgDiv1">
		<div id="rgDiv2">
			<jsp:include page="navigation.html" />
			<jsp:include page="state.jsp" />
		</div>
		<div id="rgDiv3">
			<div id="d1">
				<h2>创建用户名</h2>
				<p>想要好友更快的记住您？请创建一个动听的用户名。</p>
				<input type='text' id="in1" name='username' class="rginput"
					oninput="inputAgain(event,1)"
					onpropertychange="inputAgain(event,1)" size='25' maxlength='20'
					placeholder="用户名">
				<p id="error1" class="error">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<a href="javascript:void(0);" onclick="isUsernameOk()">下一步</a>
			</div>
			<div id="d2">
				<h2>设置密码</h2>
				<p>为了保护您的隐私，请设置一个复杂且易记的密码。</p>
				<input type='password' id="in2" class="rginput" name='password'
					oninput="inputAgain(event,2)"
					onpropertychange="inputAgain(event,2)" size='25' maxlength='30'
					placeholder="密码">
				<p id="error2" class="error">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<a href="javascript:void(0);" onclick="Tab(1)">上一步</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="isPasswordOk()">下一步</a>
			</div>
			<div id="d3">
				<h2>确认密码</h2>
				<p>为了防止意外，请您再次确认您的密码。</p>
				<input type='password' id="in3" class="rginput" name='re_password'
					oninput="inputAgain(event,3)"
					onpropertychange="inputAgain(event,3)" size='25' maxlength='30'
					placeholder="确认密码">
				<p id="error3" class="error">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<a href="javascript:void(0);" onclick="Tab(2)">上一步</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="isConfirmPwOk()">下一步</a>
			</div>
			<div id="d4">
				<h2>验证邮箱</h2>
				<p>请填写邮箱，当您忘记密码时可以通过此邮箱找回。</p>
				<input type='text' id="in4" class="rginput" name='email'
					oninput="inputAgain(event,4)"
					onpropertychange="inputAgain(event,4)" size='25' maxlength='100'
					placeholder="邮箱">
				<p id="error4" class="error">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				<a href="javascript:void(0);" onclick="Tab(3)">上一步</a>&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void(0);" onclick="isEamilOk()">提交</a>
				<!-- 	<a href="javascript:void(0);" onclick="tiaozhuan('Register.do')">提交</a> -->
			</div>
		</div>
		<div id="rgDiv4">
			<h4>Made by <a href="#">Sunshine</a>&nbsp;&nbsp;and&nbsp;&nbsp;<a href="#">Orange</a></h4>
			<span>2017 - 11 - 10</span>
		</div>
	</div>
</body>
</body>
</html>