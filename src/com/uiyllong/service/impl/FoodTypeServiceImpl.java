package com.uiyllong.service.impl;

import java.util.List;

import com.uiyllong.dao.FoodTypeDao;
import com.uiyllong.entity.FoodType;
import com.uiyllong.service.FoodTypeService;
import com.uiyllong.utils.BeanFactory;

public class FoodTypeServiceImpl implements FoodTypeService {

	private FoodTypeDao foodTypeDao = BeanFactory.getInstance("foodTypeDao", FoodTypeDao.class);
	
	@Override
	public List<FoodType> list() {
		return foodTypeDao.list();
	}

	@Override
	public void save(FoodType foodType) {
		foodTypeDao.save(foodType);
	}

	@Override
	public FoodType findById(int id) {
		return foodTypeDao.findById(id);
	}

	@Override
	public void delete(int id) {
		foodTypeDao.delete(id);
	}

	@Override
	public void update(FoodType foodType) {
		foodTypeDao.update(foodType);
	}

}
