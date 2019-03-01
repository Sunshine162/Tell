package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import model.services.UserServices;

@WebServlet("/view/Login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); 
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String isAutoLogin = request.getParameter("autoLogin");
		
		UserServices userServices = (UserServices) getServletConfig().getServletContext().getAttribute("userServices");
		if(userServices.isLoginSuccess(username,password)) {
			if(isAutoLogin!=null&&isAutoLogin.equals("yes")) {
				Cookie cookie = new Cookie("lastUser",username);
				cookie.setMaxAge(7*24*60*60);
				response.addCookie(cookie);
			}
			request.getSession().setAttribute("logined", username);
			response.sendRedirect("selected.jsp");
		} else {
			request.setAttribute("loginError", "用户名或密码输入错误！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
