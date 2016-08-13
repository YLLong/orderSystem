package com.uiyllong.dao;

import java.util.List;

import com.uiyllong.entity.Food;
import com.uiyllong.entity.PageBean;

public interface FoodDao {

	//查询所有菜品
	List<Food> list();

	//添加菜品
	void saveFood(Food food);

	//删除菜品信息
	void delete(int id);

	//查询菜品
	Food findFoodById(int id);

	//修改菜品信息
	void updateFood(Food food);

	//分页查询
	void pageList(PageBean<Food> pg);

}
