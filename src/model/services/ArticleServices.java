package model.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import model.bean.ArticleBean;
import model.dao.DataBase;

public class ArticleServices {
	
	//private DataSource dataSource;
	private Connection conn = null;
	
	
	// 初始化，取得 datasource
	public ArticleServices(DataSource dataSource) {
		//dataSource = new DataBase().getDateSource();
		try {
			this.conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 将可视化结果转化为字符串
	public int visibilityToInt(String sel_visibility) {
		int visibility = 1;
		String[] visibilityStrs = { "myself", "careme", "alluer", "allvisitor" };
		for (int i = 0; i < visibilityStrs.length; i++) {
			if (sel_visibility.equals(visibilityStrs[i])) {
				visibility = i;
				break;
			}
		}

		return visibility;
	}

	// 将话题结果转化为字符串
	public String topicToStr(String[] ch_topic) {
		String str = "";
		String[] topic = { "movie", "music", "sport", "science", "philosophy", "emotion", "work", "life", "study",
				"tourism" };
		for (int i = 0; i < topic.length; i++) {
			int j = 0;
			for (j = 0; j < ch_topic.length; j++) {
				if (ch_topic[j].equals(topic[i]))
					break;
			}
			if (j == ch_topic.length)
				str = str + "0";
			else
				str = str + "1";
		}
		return str;
	}

	// 生成一个ArticleBean对象
	public ArticleBean createArticleBean(String author, String title, String summary, String content, String topic,
			int visibility) {

		ArticleBean article = new ArticleBean();

		Date date = new Date();
		String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);// 将时间格式转换成符合Timestamp要求的格式.
		Timestamp date_time = Timestamp.valueOf(nowTime);// 把时间转换

		article.setAuthor(author);
		article.setDate_time(date_time);
		article.setTitle(title);
		article.setSummary(summary);
		article.setContent(content);
		article.setPraise(0);
		article.setCollect(0);
		article.setTopic(topic);
		article.setVisibility(visibility);

		return article;

	}

	// 向数据库中添加一篇文章的基本信息
	public boolean addArticleInfo(ArticleBean article) {
		//Connection conn;
		String sql = "insert into tb_articleinfo values(?,?,?,?,?,?,?,?)";

		try {
			//conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, article.getAuthor());
			ps.setTimestamp(2, article.getDate_time());
			ps.setString(3, article.getTitle());
			ps.setString(4, article.getSummary());
			ps.setInt(5, article.getPraise());
			ps.setInt(6, article.getCollect());
			ps.setString(7, article.getTopic());
			ps.setInt(8, article.getVisibility());
			ps.addBatch();
			ps.executeBatch();
			conn.commit();
			ps.close();
			//conn.close();
			return true;

		} catch (SQLException e) {
			System.out.println("文章" + article.getTitle() + "插入数据库出错！");
			e.printStackTrace();
			return false;
		}
	}
	
	
	

	// 向数据库中添加一篇文章的内容
	public boolean addArticleContent(ArticleBean article) {
		//Connection conn;
		String sql = "insert into tb_articlecontent values(?,?,?)";

		try {
			//conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, article.getAuthor());
			ps.setTimestamp(2, article.getDate_time());
			ps.setString(3, article.getContent());
			ps.addBatch();
			ps.executeBatch();
			conn.commit();
			ps.close();
			//conn.close();
			return true;

		} catch (SQLException e) {
			System.out.println("文章" + article.getTitle() + "插入数据库出错！");
			e.printStackTrace();
			return false;
		}
	}

	
	
	// 获取所有文章的基本信息
	public List<ArticleBean> getAllArticleInfo() {
		ArrayList<ArticleBean> articles = new ArrayList<ArticleBean>();
		String sql = "select * from tb_articleinfo order by date_time DESC";

		//Connection conn = null;
		Statement sta = null;
		try {
			//conn = dataSource.getConnection();
			sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				ArticleBean article = new ArticleBean();
				article.setAuthor(rs.getString(1));
				article.setDate_time(rs.getTimestamp(2));
				article.setTitle(rs.getString(3));
				article.setSummary(rs.getString(4));
				article.setPraise(rs.getInt(5));
				article.setCollect(rs.getInt(6));
				article.setTopic(rs.getString(7));
				article.setVisibility(rs.getInt(8));

				articles.add(article);
			}
			sta.close();
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据库出错！");
		}
		return articles;
	}
	
	
	//根据可见度级别查找文章基本信息，结果以赞和收藏排序
	//（已登录用户查看精选页面，查看 2 时需要关注）
	//（访客查看精选页面，查看 3 时需要登录）
	public List<ArticleBean> getAnyArticlesInfo(int[] anyVby) {
		ArrayList<ArticleBean> articles = new ArrayList<ArticleBean>();
		String sql = null;
		Arrays.sort(anyVby);
		int[] vbys1 = {2,3,4};
		int[] vbys2 = {3,4};
		if(Arrays.equals(anyVby, vbys1)) {
			sql = "select * from tb_articleinfo where visibility in(2,3,4) order by (praise+collect) DESC";
		} else if(Arrays.equals(anyVby, vbys2)) {
			sql = "select * from tb_articleinfo where visibility in(3,4) order by (praise+collect) DESC";
		} else {
			sql = "select * from tb_articleinfo where visibility in(4) order by (praise+collect) DESC";
		}
		//Connection conn = null;
		Statement sta = null;
		try {
			//conn = dataSource.getConnection();
			sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				ArticleBean article = new ArticleBean();
				article.setAuthor(rs.getString(1));
				article.setDate_time(rs.getTimestamp(2));
				article.setTitle(rs.getString(3));
				article.setSummary(rs.getString(4));
				article.setPraise(rs.getInt(5));
				article.setCollect(rs.getInt(6));
				article.setTopic(rs.getString(7));
				article.setVisibility(rs.getInt(8));
				articles.add(article);
			}
			sta.close();
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据库出错！");
		}
		return articles;
	}
	
	
	
	
	
	/*************************************************************************************
	*																					 *
	*	//获取所有可见度级别为 2，3 和 4 的某个专题的文章，结果以时间排序（用户查看专题页面，查看 2 需要关注）                   *
	*	//获取所有可见度级别为 3，4 的某个专题的文章，结果以时间排序（游客查看专题页面，查看 3 需要登录）			 *
	*																					 *
	*																					 *
	*	//获取某个作者可见度级别为 2，3，4 的所有文章，结果按时间排序（用户访问某个作者的文章，查看 2 需要关注）		 *
	*	//获取某个作者可见度级别为 3，4 的所有文章，结果按时间排序（游客访问某个作者的文章，查看 3 需要登录）		 *
	*																					 *			
	*	//获取所有关注的人的可见度级别为2，3，4的文章，结果以时间排序 （好友动态页面）						 *
	*																		  			 *	
	*************************************************************************************/
	
	
	
	
	//根据某个作者的姓名获取该作者的相关文章 ，结果以时间排序
	//（用户访问某个作者的文章，查看 2 需要关注）
	//（游客访问某个作者的文章，查看 3 需要登录）
	//（已登录用户自己可以看到自己的所有的文章）
	public List<ArticleBean> getAnyArticlesInfo(int[] anyVby, String author) {
		ArrayList<ArticleBean> articles = new ArrayList<ArticleBean>();

		String sql = null;
		Arrays.sort(anyVby);
		int[] vbys1 = {1,2,3,4};
		int[] vbys2 = {2,3,4};
		int[] vbys3 = {3,4};
		if(Arrays.equals(anyVby, vbys1)) {
			sql = "select * from tb_articleinfo where author='" + author + "' order by date_time DESC";
		} else if(Arrays.equals(anyVby, vbys2)) {
			sql = "select * from tb_articleinfo where visibility in(2,3,4) and author='" + author + "' order by date_time DESC";
		} else if(Arrays.equals(anyVby, vbys3)) {
			sql = "select * from tb_articleinfo where visibility in(3,4) and author='" + author + "' order by date_time DESC";
		} else {
			sql = "select * from tb_articleinfo where visibility=4 and author='" + author + "' order by date_time DESC";
		}
		
		//Connection conn = null;
		Statement sta = null;
		try {
			//conn = dataSource.getConnection();
			sta = conn.createStatement();
			ResultSet rs = sta.executeQuery(sql);

			while (rs.next()) {
				ArticleBean article = new ArticleBean();
				article.setAuthor(rs.getString(1));
				article.setDate_time(rs.getTimestamp(2));
				article.setTitle(rs.getString(3));
				article.setSummary(rs.getString(4));
				article.setPraise(rs.getInt(5));
				article.setCollect(rs.getInt(6));
				article.setTopic(rs.getString(7));
				article.setVisibility(rs.getInt(8));

				articles.add(article);
			}
			sta.close();
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据库出错！");
		}
		return articles;
	}
	
	
	
	
	
	

	// 查询数据库，根据作者和创作时间得到得到一篇文章的内容，返回一个ArticleBean类型的文章实例
	public ArticleBean getAnArticle(ArticleBean article) {
		String sql = "select content from tb_articlecontent where author=? and date_time=?";
		//Connection conn = null;
		PreparedStatement ps = null;
		try {
			//conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, article.getAuthor());
			ps.setTimestamp(2, article.getDate_time());
			ResultSet rs = ps.executeQuery(sql);

			while (rs.next()) {
				article.setContent(rs.getString(1));
			}
			ps.close();
			//conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("查询数据库出错！");
		}
		return article;
	}
	
	// 查询数据库，根据作者和创作时间返回这篇文章的内容
		public String getArticleContent(String author,Timestamp date_time) {
			String sql = "select content from tb_articlecontent where author=? and date_time=?";
			//Connection conn = null;
			PreparedStatement ps = null;
			String content = null;
			try {
				//conn = dataSource.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, author);
				ps.setTimestamp(2, date_time);
				//ResultSet rs = ps.executeQuery(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					content = rs.getString(1);
				}
				ps.close();
				//conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("查询数据库出错！");
			}
			return content;
		}

		
		

}
