package model.services;

import java.io.IOException;

import javax.naming.Context;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.security.krb5.Config;


@WebServlet("/view/IsUserExist.do")
public class IsUserExist extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public IsUserExist() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		response.setCharacterEncoding("UTF-8");
		
		UserServices userServices = (UserServices) getServletConfig().getServletContext().getAttribute("userServices");
		if(userServices.queryIsExistUser(username)) {
			response.getWriter().write("true");
		}else {
			response.getWriter().write("false");
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		response.setCharacterEncoding("UTF-8");
	}

}
