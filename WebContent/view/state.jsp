<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/state.css">
<title></title>
</head>
<body>
	<div id="stDiv1">
		<%
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						String cookName = cookie.getName();
						String cookValue = cookie.getValue();
						if (cookName.equals("lastUser") && cookValue != null) {
							request.getSession().setAttribute("logined", cookValue);
							break;
						}
					}
				}
				String loginedUser = (String) request.getSession().getAttribute("logined");
				if (loginedUser != null) {
		%>
		<ul id="stUl1">
			<li id="stLi1"><a id="stA1" href="Logout.do">退出</a></li>
			<li id="stLi1"><a id="stA1" href="#"><%=loginedUser%></a></li>
		</ul>
		<%
				} else {
		%>
		<ul id="stUl1">
			<li id="stLi1"><a id="stA1" href="register.jsp">注册</a></li>
			<li id="stLi1"><a id="stA1" href="login.jsp">登录</a></li>
		</ul>
		<%
				}
		%>
	</div>
</body>
</html>