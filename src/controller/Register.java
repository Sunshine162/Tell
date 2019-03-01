package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.services.UserServices;

@WebServlet("/view/Register.do")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Register() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); 
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String re_pw = request.getParameter("re_password");
		String email = request.getParameter("email");
		PrintWriter out = response.getWriter();
		
		//如果 检验成功并且成功添加进数据库 就跳转到首页
		UserServices userServices = (UserServices) getServletConfig().getServletContext().getAttribute("userServices");
		if(userServices.addUser(username, password, email)) {
			request.getSession().setAttribute("logined", username);
			response.sendRedirect("selected.jsp");
		} else {
			response.sendRedirect("register.jsp");
		}
		
	}

}
