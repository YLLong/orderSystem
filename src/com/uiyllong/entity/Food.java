package com.uiyllong.entity;

public class Food {

	private int id;
	private String foodName;
	// private int foodType_id;
	private double price;
	private double mprice;
	private String intro;
	private String img;

	private FoodType foodType;

	public FoodType getFoodType() {
		return foodType;
	}

	public void setFoodType(FoodType foodType) {
		this.foodType = foodType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	// public int getFoodType_id() {
	// return foodType_id;
	// }
	//
	// public void setFoodType_id(int foodType_id) {
	// this.foodType_id = foodType_id;
	// }

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMprice() {
		return mprice;
	}

	public void setMprice(double mprice) {
		this.mprice = mprice;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", foodName=" + foodName + ", price=" + price + ", mprice=" + mprice + ", intro="
				+ intro + ", img=" + img + ", foodType=" + foodType + "]";
	}

} 
