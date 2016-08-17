package com.uiyllong.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order {

	private String id; // 自动生成
	private int table_id;
	private Date orderDate;
	private double totalPrice;
	private int orderStatus; // 默认为0 未结款 1 已结账

	// 订单条目
	public Map<String, OrderDetail> orderMap = new HashMap<String, OrderDetail>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTable_id() {
		return table_id;
	}

	public void setTable_id(int table_id) {
		this.table_id = table_id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

}
