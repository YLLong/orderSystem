package com.uiyllong.service;

import java.util.List;

import com.uiyllong.entity.Order;
import com.uiyllong.entity.OrderDetail;

public interface OrderService {

	//下订单
	void ordering(Order order);

	//保持订单条目
	void saveOrderDetail(OrderDetail orderDetail);

	//后台查询所有订单
	List<Order> orderList();

	//根据订单id显示该订单详情
	List<OrderDetail> orderDetailListByOrderId(String id);

}
