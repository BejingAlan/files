package cn.edu.aynu.sushe.service;

import java.sql.SQLException;
import java.util.List;

import cn.edu.aynu.sushe.bean.Order;
import cn.edu.aynu.sushe.bean.OrderItem;
import cn.edu.aynu.sushe.dao.OrderDao;
import cn.edu.aynu.sushe.dao.OrderItemDao;
import cn.edu.aynu.sushe.utils.JDBCUtils;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	private OrderItemDao orderItemDao = new OrderItemDao();
	
	//添加订单和订单项
	public String addOrder(Order order){
		try {
			//开启事务
			JDBCUtils.startTransaction();
			//添加订单
			orderDao.add(order);
			//获取订单项的集合
			List<OrderItem> list = order.getList();
			//遍历订单项目，依次将它们插入orderitem表中
			for(OrderItem orderItem:list){
				//添加订单项
				orderItemDao.addItem(orderItem);
			}
			//提交事务
			JDBCUtils.commit();
			return "success";
		} catch (Exception e) {
			//回滚事务
			JDBCUtils.rollback();
			e.printStackTrace();
			return "failer";
		}
	}
	
	//查看我的订单
	public List<Order> findMyOrder(String user_id){
		try {
			return orderDao.findOrderByUser_id(user_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	//查看某个订单的详情
	public List<OrderItem> findOrderItem(String order_id){
		try {
			return orderItemDao.findOrderItem(order_id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
}
