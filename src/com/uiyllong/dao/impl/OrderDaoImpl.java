package com.uiyllong.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.uiyllong.dao.DinnerTableDao;
import com.uiyllong.dao.FoodDao;
import com.uiyllong.dao.OrderDao;
import com.uiyllong.entity.DinnerTable;
import com.uiyllong.entity.Order;
import com.uiyllong.entity.OrderDetail;
import com.uiyllong.utils.BeanFactory;
import com.uiyllong.utils.JdbcUtils;

public class OrderDaoImpl implements OrderDao {

	private DinnerTableDao dinnerTableDao = BeanFactory.getInstance("dinnerTableDao", DinnerTableDao.class);
	private FoodDao foodDao = BeanFactory.getInstance("foodDao", FoodDao.class);
	
	/**
	 * 订单插入数据库
	 */
	@Override
	public void ordering(Order order) {
		String sql = "insert into OS_orders(id, table_id, orderDate, totalPrice) values(?, ?, ?, ?)";
		try {
			JdbcUtils.getQueryRunner().update(sql, order.getId(), order.getTable().getId(), order.getOrderDate(), order.getTotalPrice());
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

	/**
	 * 显示所有订单
	 */
	@Override
	public List<Order> orderList() {
		String sql = "select o.id, o.table_id, o.orderDate, o.totalPrice, o.orderStatus from OS_orders o, OS_diningTable t where o.table_id = t.id";
		try {
			List<Order> orders = JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Order>(Order.class));
			for (Order order : orders) {
				DinnerTable table = dinnerTableDao.getTableById(order.getTable_id());
				order.setTable(table);
			}
			System.out.println(orders);
			return orders;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据订单 id 显示该订单的详细
	 */
	@Override
	public List<OrderDetail> orderDetailListByOrderId(String id) {
		String sql = "select od.id, od.order_id, od.food_id, od.foodCount from OS_orderDetail od where order_id = ?";
		try {
			List<OrderDetail> orderDetails = JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<OrderDetail>(OrderDetail.class), id);
			for (OrderDetail orderDetail : orderDetails) {
				orderDetail.setFood(foodDao.findFoodById(orderDetail.getFood_id()));
			}
			return orderDetails;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
