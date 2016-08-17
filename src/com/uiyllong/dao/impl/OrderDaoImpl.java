package com.uiyllong.dao.impl;

import java.sql.SQLException;

import com.uiyllong.dao.OrderDao;
import com.uiyllong.entity.Order;
import com.uiyllong.entity.OrderDetail;
import com.uiyllong.utils.JdbcUtils;

public class OrderDaoImpl implements OrderDao {

	/**
	 * 订单插入数据库
	 */
	@Override
	public void ordering(Order order) {
		String sql = "insert into OS_orders(id, table_id, orderDate, totalPrice) values(?, ?, ?, ?)";
		try {
			JdbcUtils.getQueryRunner().update(sql, order.getId(), order.getTable_id(), order.getOrderDate(), order.getTotalPrice());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将订单条目保存
	 */
	@Override
	public void saveOrderDetail(OrderDetail orderDetail) {
		String sql = "insert into OS_orderDetail(order_id, food_id, foodCount) values(?, ?, ?)";
		try {
			JdbcUtils.getQueryRunner().update(sql, orderDetail.getOrder_id(), orderDetail.getFood().getId(), orderDetail.getFoodCount());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
