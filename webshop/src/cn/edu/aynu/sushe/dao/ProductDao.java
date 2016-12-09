package cn.edu.aynu.sushe.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.edu.aynu.sushe.bean.Product;
import cn.edu.aynu.sushe.utils.JDBCUtils;

public class ProductDao {
	QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
	
	//查询所有商品
	public List<Product> findAll() throws SQLException{
		String sql = "select * from product";
		List<Product> list = qr.query(sql, new BeanListHandler<Product>(Product.class));
		return list;
	}
	
	//根据id查找商品
	public Product findProductById(String id) throws SQLException{
		String sql = "select * from product where id=?";
		return qr.query(sql, new BeanHandler<Product>(Product.class),id);
	}
}
