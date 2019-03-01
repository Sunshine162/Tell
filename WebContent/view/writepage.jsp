<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/writepage.css">
<title>编辑页面</title>
</head>
<body id="wtBody">
	<div id="wtDiv1">
	<div id="wtDiv2">
		<jsp:include page="navigation.html"/>
		<jsp:include page="state.jsp"/>
	</div>
	<div id="wtDiv3">
		<form action="Publish.do" method="POST">
			<br><br>可见度级别：
			<input type="radio" name="visibility" value="myself" >自己可见
			<input type="radio" name="visibility" value="careme" checked="true">关心我的人
			<input type="radio" name="visibility" value="alluer">所有用户
			<input type="radio" name="visibility" value="allvisitor">所有访客
			
			<br><br>话题：
			<input type="checkbox" name="topic" value="movie">电影
			<input type="checkbox" name="topic" value="music">音乐
			<input type="checkbox" name="topic" value="sport">运动
			<input type="checkbox" name="topic" value="science">科学
			<input type="checkbox" name="topic" value="philosophy">哲学
			<input type="checkbox" name="topic" value="emotion" checked="checked" >感情
			<input type="checkbox" name="topic" value="work">工作
			<input type="checkbox" name="topic" value="life">生活
			<input type="checkbox" name="topic" value="study">学习
			<input type="checkbox" name="topic" value="tourism">旅游
			
			<br><br>标题<br>
			<textarea id="wtTa1" name="title" rows="3" cols="120"></textarea><br><br>
			摘要<br>
			<textarea id="wtTa3" name="summary" rows="8" cols="120"></textarea><br><br>
			正文<br>
			<textarea id="wtTa3" name="content" rows=35" cols="120"></textarea><br>
			<input id="wtin1" type="submit" value="发布">
			<input id="wtin2" type="reset" value="清空">
		</form>
	</div>
	<div id="wtDiv4">
		<h4>Made by <a href="#">Sunshine</a>&nbsp;&nbsp;and&nbsp;&nbsp;<a href="#">Orange</a></h4>
		<span>2017 - 11 - 10</span>
	</div>
	</div>
</body>
</html>