package com.uiyllong.service.impl;

import java.util.List;

import com.uiyllong.dao.OrderDao;
import com.uiyllong.entity.Order;
import com.uiyllong.entity.OrderDetail;
import com.uiyllong.service.OrderService;
import com.uiyllong.utils.BeanFactory;

public class OrderServiceImpl implements OrderService {

	private OrderDao orderDao = BeanFactory.getInstance("orderDao", OrderDao.class);
	
	@Override
	public void ordering(Order order) {
		orderDao.ordering(order);
	}

	@Override
	public void saveOrderDetail(OrderDetail orderDetail) {
		orderDao.saveOrderDetail(orderDetail);
	}

	@Override
	public List<Order> orderList() {
		return orderDao.orderList();
	}

	@Override
	public List<OrderDetail> orderDetailListByOrderId(String id) {
		return orderDao.orderDetailListByOrderId(id);
	}

}
