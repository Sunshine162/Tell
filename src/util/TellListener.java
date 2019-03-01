package util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.sun.org.apache.bcel.internal.generic.NEW;

import model.services.ArticleServices;
import model.services.UserServices;


@WebListener
public class TellListener implements ServletContextListener {


    public TellListener() {
        // TODO Auto-generated constructor stub
    }


    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	DataSource dataSource = null;  
        try {  
            Context initContext = new InitialContext();  
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource=(DataSource) envContext.lookup("jdbc/Tell");   
            //Connection conn = dateSource.getConnection();  
            
            ServletContext context = sce.getServletContext();
            context.setAttribute("userServices",new UserServices(dataSource));
            context.setAttribute("articleServices", new ArticleServices(dataSource));
        } catch (Exception ex) {  
        	System.out.print("数据库连接失败:" + ex.getMessage());  
            ex.printStackTrace();  
        }
         
    }
	
}
