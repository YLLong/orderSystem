package com.uiyllong.service;

import com.uiyllong.entity.Order;
import com.uiyllong.entity.OrderDetail;

public interface OrderService {

	//下订单
	void ordering(Order order);

	//保持订单条目
	void saveOrderDetail(OrderDetail orderDetail);

}
