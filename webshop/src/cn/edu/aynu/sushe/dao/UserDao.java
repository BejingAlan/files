package cn.edu.aynu.sushe.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.edu.aynu.sushe.bean.User;
import cn.edu.aynu.sushe.utils.JDBCUtils;

public class UserDao {
	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	/**
	 * 添加用户信息到表user中
	 * @param user
	 * @throws SQLException 
	 */
	public void add(User user) throws SQLException{
		String sql = "insert into user values(?,?,?,?,?)";
		qr.update(sql, user.getId(),user.getUsername(),user.getPassword(),user.getName(),user.getTelephone());	
	}
	
	/**
	 * 通过用户名查找用户
	 * @param username
	 * @return
	 * @throws SQLException 
	 */
	public User findByUsername(String username) throws SQLException{
		String sql = "select * from user where username = ?";
		User user = qr.query(sql, new BeanHandler<User>(User.class),username);
		return user;
	}
	
	
}
