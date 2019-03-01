<%@page import="model.services.ArticleServices"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/readArticle.css">
<title>Tell</title>
</head>
<body id="raBody">
	<div id="raDiv1">
	<div id="raDiv2">
		<jsp:include page="navigation.html"/>
		<jsp:include page="state.jsp"/>
	</div>
	<div id="raDiv3">
		<br> <br>
		<%
			Timestamp date_time = Timestamp.valueOf(request.getParameter("date_time"));
			String author = request.getParameter("author");
			ArticleServices articleServices = (ArticleServices)getServletContext().getAttribute("articleServices");
			String content = articleServices.getArticleContent(author, date_time);
		%>
		<div class="arCt">
<%-- 		<h3><%= request.getParameter("author")%></h3> --%>
<%-- 		<h3><%= request.getParameter("date_time")%></h3> --%>
		<h3><%= request.getParameter("title")%></h3>
<%-- 		<h3><%= request.getParameter("summary")%></h3> --%>
<%-- 		<h3><%= request.getParameter("praise")%></h3> --%>
<%-- 		<h3><%= request.getParameter("collect")%></h3> --%>
<%-- 		<h3><%= request.getParameter("topic")%></h3> --%>
<%-- 		<h3><%= request.getParameter("visibility")%></h3> --%>
		
		<hr>
		<pre><%= content %></pre>
		<hr>
		<%= author %>&nbsp;&nbsp;&nbsp;&nbsp;
		<%= date_time %>
		</div>
		<br><br><br><br>
	</div>
	<div id="raDiv4">
		<h4>Made by <a href="#">Sunshine</a>&nbsp;&nbsp;and&nbsp;&nbsp;<a href="#">Orange</a></h4>
		<span>2017 - 11 - 10</span>
	</div>
	</div>
</body>
</html>