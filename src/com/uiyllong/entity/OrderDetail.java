package com.uiyllong.entity;

/**
 * @author Zero_Dragon
 *
 */
public class OrderDetail {

	private int id;
	private String order_id; // 订单号
	private Food food;
	private int foodCount;

	public double subTotal() {
		return this.foodCount * food.getPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getFoodCount() {
		return foodCount;
	}

	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}

	@Override
	public String toString() {
		return "OrderDetail [id=" + id + ", order_id=" + order_id + ", food=" + food + ", foodCount=" + foodCount + "]";
	}

}
