package com.uiyllong.service;

import java.util.List;

import com.uiyllong.entity.Food;

public interface FoodService {

	//查询所有菜品
	List<Food> list();

	//添加菜品
	void saveFood(Food food);

	//删除菜品信息
	void delete(int id);

	//封装food用于回显
	Food findFoodById(int id);

	//修改菜品信息
	void updateFood(Food food);

}
