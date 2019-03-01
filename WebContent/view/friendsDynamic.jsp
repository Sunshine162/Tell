<%@page import="model.services.ArticleServices"%>
<%@page import="model.bean.ArticleBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/friendsDynamic.css">
<script type="text/javascript" src="js/readArticle.js"></script>
<title>Tell</title>
</head>
<body id="fdBody">
	<div id="fdDiv1">
	<div id="fdDiv2">
		<jsp:include page="navigation.html"/>
		<jsp:include page="state.jsp"/>
	</div>
	<div id="fdDiv3">
		<br> <br> 
		<%
		ArticleServices articleServices = (ArticleServices)getServletContext().getAttribute("articleServices");
		List<ArticleBean> articles = null;
		if(request.getSession().getAttribute("logined")!=null){
			int[] vbys = {2,3,4};
			articles = articleServices.getAnyArticlesInfo(vbys);
		}else{
			int[] vbys = {3,4};
			articles = articleServices.getAnyArticlesInfo(vbys);
		}
		
		int end = articles.size();
		HttpSession mysession = request.getSession();
 		for(int i=0; i<end; i++){  
 			ArticleBean article = articles.get(i);
 			%>					
			<div class="arInfo">
			<hr>
			<form action="readArticle.jsp" method="post">
				<input id="<%= i%>_in1" type="hidden" name="author" value="<%= article.getAuthor()%>">
				<input id="<%= i%>_in2" type="hidden" name="date_time" value="<%= article.getDate_time()%>">
				<input id="<%= i%>_in3" type="hidden" name="title" value="<%= article.getTitle()%>">
				<input id="<%= i%>_in4" type="hidden" name="summary" value="<%= article.getSummary()%>">
				<input id="<%= i%>_in5" type="hidden" name="praise" value="<%= article.getPraise()%>">
				<input id="<%= i%>_in6" type="hidden" name="collect" value="<%= article.getCollect()%>">
				<input id="<%= i%>_in7" type="hidden" name="topic" value="<%= article.getTopic()%>">
				<input id="<%= i%>_in8" type="hidden" name="visibility" value="<%= article.getVisibility()%>">
				<h3><a href="javascript:void(0);" onclick="readIt('<%= i%>')"><%= article.getTitle()%></a></h3>
<%-- 			<h3><input type="submit" value="<%= article.getTitle()%>" class="sub"></h3> --%>
			</form>
			
			

<%-- 		<h3><a href="readArticle.jsp?author=<%=article.getAuthor()%>&date_time=<%= article.getDate_time()%>" target="_blank"><%= article.getTitle() %></a></h3> --%>
			<pre><%= article.getSummary() %></pre>
			<br>
			<span id="author"><%= article.getAuthor() %></span>&nbsp;&nbsp;&nbsp;
			<span id="da_ti"><%= article.getDate_time() %></span>&nbsp;&nbsp;&nbsp;
			赞：<%= article.getPraise() %>
			</div>	
			<%	} %>
	</div>
	<div id="fdDiv4">
		<h4>Made by <a href="#">Sunshine</a>&nbsp;&nbsp;and&nbsp;&nbsp;<a href="#">Orange</a></h4>
		<span>2017 - 11 - 10</span>
	</div>
	</div>
</body>
</html>