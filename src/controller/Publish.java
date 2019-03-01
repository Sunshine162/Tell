package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.ArticleBean;
import model.services.ArticleServices;

@WebServlet("/view/Publish.do")
public class Publish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Publish() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ArticleServices articleServices = (ArticleServices) getServletConfig().getServletContext().getAttribute("articleServices");
		
		//获取参数
		String author = (String) request.getSession().getAttribute("logined");
		String se_visibility  = request.getParameter("visibility");
		int visibility = articleServices.visibilityToInt(se_visibility);
		String[] ch_topic = request.getParameterValues("topic");
		String topic = articleServices.topicToStr(ch_topic);
		String title = request.getParameter("title");
		String summary = request.getParameter("summary");
		String content = request.getParameter("content");
		
		//创建一个 文章 实例并存入数据库
		ArticleBean article = articleServices.createArticleBean(author, title, summary, content, topic, visibility);
		articleServices.addArticleInfo(article);
		articleServices.addArticleContent(article);
		
		response.sendRedirect("mypage.jsp");
		

			
	}

}
