package model.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DataBase {
	public DataSource getDateSource() {
        DataSource dateSource = null;  
        try {  
            Context initContext = new InitialContext();  
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dateSource=(DataSource) envContext.lookup("jdbc/Tell");   
            //Connection conn = dateSource.getConnection();  
        } catch (Exception ex) {  
        	System.out.print("数据库连接失败:" + ex.getMessage());  
            ex.printStackTrace();  
        }
        return dateSource;
	}
}
