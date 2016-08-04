package com.uiyllong.dao;

import java.util.List;

import com.uiyllong.entity.FoodType;

public interface FoodTypeDao {

	//列出所有菜系
	List<FoodType> list();

	//添加菜系
	void save(FoodType foodType);

	//修改菜系
	FoodType findById(int id);

	//删除菜系
	void delete(int id);

	//修改菜系
	void update(FoodType foodType);

}
