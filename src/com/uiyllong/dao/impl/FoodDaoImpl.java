package com.uiyllong.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.uiyllong.dao.FoodDao;
import com.uiyllong.entity.Food;
import com.uiyllong.entity.FoodType;
import com.uiyllong.utils.JdbcUtils;

public class FoodDaoImpl implements FoodDao {

	//查询所有菜品信息
	@Override
	public List<Food> list() {
		String sql = "select * from OS_foodType";
		
		try {
			//查出所有菜系
			List<Food> fList = new ArrayList<Food>();
			List<FoodType> foodTypes = JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class));
			System.out.println(foodTypes);
			//根据菜系 id 查出所有菜品
			for (FoodType foodType : foodTypes) {
				List<Food> foods = findFoodByFoodTypeId(foodType.getId());
				if (foods.size() > 0) {
					for (Food food : foods) {
						food.setFoodType(foodType);
					}
					fList.addAll(foods);
				}
			}
			return fList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//添加菜品
	@Override
	public void saveFood(Food food) {
		String sql = "insert into OS_foodInfo(foodName, foodType_id, price, mprice, intro, img)"
				+ " values(?, ?, ?, ?, ?, ?)";
		try {
			JdbcUtils.getQueryRunner().update(sql, food.getFoodName(), food.getFoodType().getId(), food.getPrice(),
					food.getMprice(), food.getIntro(), food.getImg());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//根据菜系 id 查出所有菜品
	public List<Food> findFoodByFoodTypeId(int id) {
		String sql = "select * from OS_foodInfo where foodType_id = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Food>(Food.class), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
