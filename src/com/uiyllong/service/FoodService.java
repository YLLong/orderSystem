package com.uiyllong.service;

import java.util.List;

import com.uiyllong.entity.Food;

public interface FoodService {

	//查询所有菜品
	List<Food> list();

	//添加菜品
	void saveFood(Food food);

}
