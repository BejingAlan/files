package cn.edu.aynu.sushe.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.aynu.sushe.bean.Order;
import cn.edu.aynu.sushe.bean.OrderItem;
import cn.edu.aynu.sushe.bean.Product;
import cn.edu.aynu.sushe.bean.User;
import cn.edu.aynu.sushe.service.OrderService;
import cn.edu.aynu.sushe.utils.CommonsUtils;

public class OrderServlet extends HttpServlet {
	private OrderService orderServcie = new OrderService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int method = Integer.parseInt(request.getParameter("method"));
		switch(method){
			case 1:this.addOrder(request, response);
					break;
			case 2:this.findMyOrder(request, response);
					break;
			case 3:this.findOrderItem(request, response);
					break;
		}
	}

	//添加订单和订单项
	public void addOrder(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		/**
		 * 1、判断用户是否登陆，如果未登录就重定向到login.jsp页面
		 * 2、使用工具类将订单表单中的信息封装成Order对象，补全剩余字段，
		 * 3、从session中获取购物车，遍历购物车，创建订单项，把他们加入List集合
		 * 4、添加订单和订单项
		 */
		User user = (User) request.getSession().getAttribute("user");
		if(user == null){
			response.sendRedirect("/day34/login.jsp");
			return;
		}
		
		Order order = CommonsUtils.toBean(request.getParameterMap(), Order.class);
		//补全剩余字段
		order.setId(CommonsUtils.uuid());
		order.setOrdertime(new Date());
		order.setPaysate(0);
		order.setUser_id(user.getId());
		
		Map<Product,Integer> cart = (Map<Product, Integer>) request.getSession().getAttribute("cart");
		List<OrderItem> list  = new ArrayList<OrderItem>();
		for(Product p:cart.keySet()){
			OrderItem orderItem = new OrderItem();
			orderItem.setId(CommonsUtils.uuid());
			orderItem.setOrder_id(order.getId());
			orderItem.setProduct_id(p.getId());
			orderItem.setBuynum(cart.get(p));
			orderItem.setSubtotal(p.getPrice()*orderItem.getBuynum());
			//订单项加入list集合
			list.add(orderItem);
		}
		order.setList(list);
		//添加订单和订单项
		String result = orderServcie.addOrder(order);
		request.setAttribute("orderResult", result);
		request.getRequestDispatcher("/result.jsp").forward(request, response);
	}
	
	//查看我的订单
	public void findMyOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 1、用户已经登录，从session中获取用户的信息，从用户信息中得到user_id
		 * 2、调用方法的得到用户的订单的集合
		 * 3、通过请求转发将订单集合显示在myOrder.jsp页面上
		 */
		User user = (User) request.getSession().getAttribute("user");
		String user_id = user.getId();
		List<Order> list = orderServcie.findMyOrder(user_id);
		request.setAttribute("myOrderList", list);
		request.getRequestDispatcher("/myOrder.jsp").forward(request, response);
	}	
	
	
	//查看我的订单的详情
	public void findOrderItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			/**
			 * 1、从请求中获取订单的id
			 * 2、调用方法得到订单详情的集合
			 * 3、通过请求转发将订单详情在orderItem.jsp页面上显示
			 */
		String order_id = request.getParameter("order_id");
		List<OrderItem> list = orderServcie.findOrderItem(order_id);
		request.setAttribute("orderItemList", list);
		request.getRequestDispatcher("/orderItem.jsp").forward(request, response);
	}
	
	
	
}
