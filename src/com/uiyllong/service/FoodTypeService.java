package com.uiyllong.service;

import java.util.List;

import com.uiyllong.entity.FoodType;

public interface FoodTypeService {

	//列出全部菜系
	List<FoodType> list();

	//添加菜系
	void save(FoodType foodType);

	//根据菜系 id 查找菜系信息
	FoodType findById(int id);

	//删除菜系
	void delete(int id);

	//修改菜系
	void update(FoodType foodType);

}
