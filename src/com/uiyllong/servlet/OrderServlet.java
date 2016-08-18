package com.uiyllong.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uiyllong.entity.Order;
import com.uiyllong.entity.OrderDetail;

public class OrderServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 显示所有订单
	 * @param request
	 * @param response
	 * @return
	 */
	public Object orderList(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		List<Order> orders = orderService.orderList();
		request.setAttribute("orderList", orders);
		url = request.getRequestDispatcher("/pos/orderList.jsp");
		return url;
	}
	
	/**
	 * 显示订单条目详细
	 * @param request
	 * @param response
	 * @return
	 */
	public Object orderDetail(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String id = request.getParameter("orderId");
		List<OrderDetail> orderDetails = orderService.orderDetailListByOrderId(id);
		request.setAttribute("orderDetails", orderDetails);
		url = request.getRequestDispatcher("/pos/orderDetail.jsp");
		return url;
	}
	
}
