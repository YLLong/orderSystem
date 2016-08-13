package com.uiyllong.service.impl;

import java.util.List;

import com.uiyllong.dao.FoodDao;
import com.uiyllong.entity.Food;
import com.uiyllong.service.FoodService;
import com.uiyllong.utils.BeanFactory;

public class FoodServiceImpl implements FoodService {

	private FoodDao foodDao = BeanFactory.getInstance("foodDao", FoodDao.class);

	@Override
	public List<Food> list() {
		return foodDao.list();
	}

	@Override
	public void saveFood(Food food) {
		foodDao.saveFood(food);
	}

	@Override
	public void delete(int id) {
		foodDao.delete(id);
	}

	@Override
	public Food findFoodById(int id) {
		return foodDao.findFoodById(id);
	}

	@Override
	public void updateFood(Food food) {
		foodDao.updateFood(food);
	}
	
}
