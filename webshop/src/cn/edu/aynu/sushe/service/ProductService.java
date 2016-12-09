package cn.edu.aynu.sushe.service;

import java.sql.SQLException;
import java.util.List;

import cn.edu.aynu.sushe.bean.Product;
import cn.edu.aynu.sushe.dao.ProductDao;

public class ProductService {
	private  ProductDao pdao = new ProductDao();
	
	//查询所有商品
	public List<Product> findAll(){
		try {
			return pdao.findAll();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//查看某一商品
	public Product findById(String id){
		try {
			return pdao.findProductById(id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
