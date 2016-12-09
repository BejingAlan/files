package cn.edu.aynu.sushe.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.aynu.sushe.bean.Product;
import cn.edu.aynu.sushe.service.ProductService;

public class ProductServlet extends HttpServlet {
	private ProductService ps = new ProductService();

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int method = Integer.parseInt(request.getParameter("method"));
		switch (method) {
		case 1:
			this.findAll(request, response);
			break;
		case 2:
			this.addCart(request, response);
			break;
		case 3:
			this.changeCart(request, response);
			break;
		case 4:
			this.findById(request, response);
			break;
		}
	}

	/**
	 * 根据id查找到该 商品的具体信息
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void findById(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("productid");
		Product product = ps.findById(id);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/jsp/productdetails.jsp").forward(
				request, response);

	}

	// 处理查询所有商品的请求
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> list = ps.findAll();
		request.setAttribute("listProduct", list);
		// 通过请求转发将商品信息显示在list.jsp页面上
		request.getRequestDispatcher("/jsp/commoditylist.jsp").forward(request,
				response);
	}

	public void addCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取商品id
		String id = request.getParameter("id");
		// 得到商品
		Product p = ps.findById(id);

		// 购物车是保存在session中
		HttpSession session = request.getSession();
		// 从session中拿到购物车
		Map<Product, Integer> cart = (Map<Product, Integer>) session
				.getAttribute("cart");
		// 如果cart不存在,就创建购物车
		if (cart == null) {
			cart = new HashMap<Product, Integer>();
		}

		// 购物，就是向cart集合中加入商品和数量,如果有相同的商品，返回值为原有的数量
		// Integer count = cart.put(p, 1);
		// if(count != null){
		// cart.put(p, count+1);
		// }

		// 遍历Map中的所有key也就是商品对象，如果发现有商品的id和即将加入购物车的id相同，就在已有商品原来的数量基础上加1
		Iterator<Product> it = cart.keySet().iterator();
		boolean f = true;
		while (it.hasNext()) {
			Product pp = (Product) it.next();
			if (pp.getId().equals(p.getId())) {
				cart.put(pp, cart.get(pp) + 1);
				f = false;
			}
		}
		// 如果没有发现购物车原来有相同的商品，就直接加入
		if (f) {
			cart.put(p, 1);
		}

		// 将cart放入session
		session.setAttribute("cart", cart);
		// 重定向
		response.sendRedirect("/webshop/jsp/mycart.jsp");
	}

	public void changeCart(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		int count = Integer.parseInt(request.getParameter("count").trim());
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>) session
				.getAttribute("cart");

		Iterator<Product> it = cart.keySet().iterator();
		while (it.hasNext()) {
			Product pp = (Product) it.next();
			if (pp.getId().equals(id)) {
				if (count == 0) {// 删除当前商品
					// cart.remove(pp);
					it.remove();
				} else {// 修改当前商品数量
					cart.put(pp, count);
				}
			}
		}

		response.sendRedirect("/webshop/jsp/mycart.jsp");
	}

}
