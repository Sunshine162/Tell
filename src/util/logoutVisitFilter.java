package util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(
		urlPatterns = { "/view/login.jsp", "/view/register.jsp"}, 
		initParams = {@WebInitParam(name = "HOME_VIEW", value = "/Tell/view/selected.jsp") }
)

public class logoutVisitFilter implements Filter {
	private String HOME_VIEW;

	public void init(FilterConfig config) throws ServletException {
		this.HOME_VIEW = config.getInitParameter("HOME_VIEW");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getSession().getAttribute("logined") == null) {
			chain.doFilter(request, response);
		} else {
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect(HOME_VIEW);
		}
	}

	public void destroy() {
	}

}
