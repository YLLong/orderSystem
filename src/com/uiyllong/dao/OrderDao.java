package com.uiyllong.dao;

import java.util.List;

import com.uiyllong.entity.Order;
import com.uiyllong.entity.OrderDetail;

public interface OrderDao {

	//下订单
	void ordering(Order order);

	//保存订单条目
	void saveOrderDetail(OrderDetail orderDetail);

	//后台显示所有订单
	List<Order> orderList();

	//根据 orderid 显示所有详情
	List<OrderDetail> orderDetailListByOrderId(String id);

}
