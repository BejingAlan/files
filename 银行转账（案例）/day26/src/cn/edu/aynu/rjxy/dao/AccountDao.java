package cn.edu.aynu.rjxy.dao;

import java.sql.Connection;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.edu.aynu.rjxy.bean.Account;
import cn.edu.aynu.rjxy.utils.JDBCUtils;

public class AccountDao {
	//根据账户名查询账户信息
	public Account find(String name) throws Exception{
		QueryRunner run = new QueryRunner();
		Connection conn = JDBCUtils.getConnection();
		System.out.println("find()得到连接："+conn);
		String sql = "select * from account where name=?";
		Account account  = (Account)run.query(conn, sql, new BeanHandler(Account.class), new Object[]{name});	
		return account;
		
	}
	
	//转账（可能转入也可能转出）
	public void update(Account account) throws Exception {
		QueryRunner run = new QueryRunner();
		Connection conn = JDBCUtils.getConnection();
		System.out.println("update()得到连接："+conn);
		String sql = "update account set money=? where name=?";
		run.update(conn, sql, new Object[]{account.getMoney(),account.getName()});
	}
}
