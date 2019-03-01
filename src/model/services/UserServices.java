package model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import model.bean.UserBean;
import model.dao.DataBase;

public class UserServices {
	
	//private DataSource dataSource;
	private Connection conn = null;
	
	//初始化，取得 datasource
	public UserServices(DataSource dataSource) {
		//dataSource = new DataBase().getDateSource();
		
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//检查登录是否成功过
	public boolean isLoginSuccess(String username, String password) {
		
		HashMap<String, String> users_pws = getUsers_Pws();
		if (users_pws.containsKey(username) && users_pws.get(username).equals(password)) {
			return true;
		}
		return false;

		// Iterator iter = users_pws.entrySet().iterator();
		// while (iter.hasNext()) {
		// Map.Entry entry = (Map.Entry) iter.next();
		//
		// String str1 = (String) entry.getKey();
		// String str2 = (String) entry.getValue();
		// out.println(str1+"------"+str2+"<br>");
		// }

	}
	
	//查询数据库，得到 用户名-密码 键值对
	public HashMap getUsers_Pws() {
		HashMap users_pws = new HashMap<String,String>();
		String sql = "select * from tb_users";

		//Connection conn = null;
		Statement sta = null;
		try {
			//conn = dataSource.getConnection();
			sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			
			while (rs.next()) {
				String username = rs.getString(1);
				String password = rs.getString(2);
				users_pws.put(username, password);
			}
			sta.close();
			////conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据库出错！");
		}	
		return users_pws;
		
	}
	
	
	//根据用户名查询是否有某个用户
		public boolean queryIsExistUser(String username) {
			String sql = "select count(1) from tb_users where username="+"'"+username+"'";	
			
			//Connection conn = null;
			Statement sta = null;
			int count = 0;
			try {
				//conn = dataSource.getConnection();
				sta = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = sta.executeQuery(sql);
				if (rs.next()) {
					count = rs.getInt(1);
				}
				System.out.println("总人数："+count);
				sta.close();
				////conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("查询数据库出错！");
			}
			if(count>0)
				return true;
			else
				return false;
			
		}
	
	
	//获取所有用户信息
	public List<UserBean> getAllUsersInfo() {
		ArrayList<UserBean> users = new ArrayList<UserBean>();
		String sql = "select * from tb_users";

		//Connection conn = null;
		Statement sta = null;
		try {
		//	conn = dataSource.getConnection();
			sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			
			while (rs.next()) {
				UserBean user = new UserBean();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
				user.setEmail(rs.getString(3));
				users.add(user);
			}
			sta.close();
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据库出错！");
		}
		return users;
	}
	
	
	//向数据库中添加一个用户
	public boolean addUser(UserBean user) {
		//Connection conn;
		String sql = "insert into tb_users values(?,?,?)";

		try { 
			//conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.addBatch();
			ps.executeBatch();
			conn.commit();
			ps.close();
			//conn.close();
			return true;
			
		} catch (SQLException e) {
			System.out.println("将用户" + user.getUsername() + "插入数据库出错！");
			e.printStackTrace();
			return false;
		}
	}
	
	
	//向数据库中添加一个用户
	public boolean addUser(String username,String password,String email) {
		//Connection conn;
		String sql = "insert into tb_users values(?,?,?)";

		try { 
			//conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, email);
			ps.addBatch();
			ps.executeBatch();
			conn.commit();
			ps.close();
			//conn.close();
			return true;
			
		} catch (SQLException e) {
			System.out.println("将用户" + username + "插入数据库出错！");
			e.printStackTrace();
			return false;
		}
	}


}
