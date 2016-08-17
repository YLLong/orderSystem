package com.uiyllong.dao;

import com.uiyllong.entity.Order;
import com.uiyllong.entity.OrderDetail;

public interface OrderDao {

	//下订单
	void ordering(Order order);

	//保存订单条目
	void saveOrderDetail(OrderDetail orderDetail);

}
