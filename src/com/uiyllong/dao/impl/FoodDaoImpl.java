package com.uiyllong.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.uiyllong.dao.FoodDao;
import com.uiyllong.entity.Food;
import com.uiyllong.entity.FoodType;
import com.uiyllong.entity.PageBean;
import com.uiyllong.utils.JdbcUtils;

public class FoodDaoImpl implements FoodDao {

	// 查询所有菜品信息
	@Override
	public List<Food> list() {
		String sql = "select * from OS_foodType";

		try {
			// 查出所有菜系
			List<Food> fList = new ArrayList<Food>();
			List<FoodType> foodTypes = JdbcUtils.getQueryRunner().query(sql,
					new BeanListHandler<FoodType>(FoodType.class));
			// 根据菜系 id 查出所有菜品
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

	// 添加菜品
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

	// 根据菜系 id 查出所有菜品
	public List<Food> findFoodByFoodTypeId(int id) {
		String sql = "select * from OS_foodInfo where foodType_id = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<Food>(Food.class), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 删除菜品信息
	@Override
	public void delete(int id) {
		String sql = "delete from OS_foodInfo where id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// 根据菜品 id 查询菜品信息
	@Override
	public Food findFoodById(int id) {
		String sql = "select * from OS_foodInfo where id = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<Food>(Food.class), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//修改菜品信息
	@Override
	public void updateFood(Food food) {
		String sql = "update OS_foodInfo set foodName = ?, foodType_id = ?, price = ?, mprice = ?, intro = ?, img = ? where id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql, food.getFoodName(), food.getFoodType().getId(), food.getPrice(),
					food.getMprice(), food.getIntro(), food.getImg(), food.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//分页查询数据 每次六条
	@Override
	public void pageList(PageBean<Food> pg) {
		//查出总条数
		int foodType_id = pg.getCondition().getFoodType_id();
		String foodName = pg.getCondition().getFoodName();
		
		StringBuffer sql = new StringBuffer();
		sql.append("select");
		sql.append("		f.id,");
		sql.append("		f.foodName,");
		sql.append("		f.price,");
		sql.append("		f.mprice,");
		sql.append("		f.intro,");
		sql.append("		f.img,");
		sql.append("		t.id,");
		sql.append("		t.typeName ");
		sql.append("from ");
		sql.append("		OS_foodInfo f, ");
		sql.append("		OS_foodType t ");
		sql.append("where 1=1");
		sql.append("		and f.foodType_id=t.id ");
		List<Object> list = new ArrayList<Object>();
		if (foodType_id > 0) {
			sql.append("and f.foodType_id = ? ");
			list.add(foodType_id);
		}
		if (foodName != null && !"".equals(foodName.trim())) {
			sql.append("and foodName like '%?%' ");
			list.add(foodName);
		}
		sql.append("limit ?,?");
		pg.setTotalCount(findFoodByFoodTypeId(foodType_id).size());
		int totalPage = pg.getTotalCount() % pg.getPageCount() == 0 ? pg.getTotalCount() / pg.getPageCount() : pg.getTotalCount() / pg.getPageCount() + 1;
		pg.setTotalPage(totalPage);
		if (pg.getCurrentPage() < 1) {
			pg.setCurrentPage(1);
		} else if (pg.getCurrentPage() > pg.getTotalPage()) {
			pg.setCurrentPage(pg.getTotalPage());
		}
		int index = (pg.getCurrentPage() - 1) * pg.getPageCount();
		int count = pg.getPageCount();
		list.add(index);
		list.add(count);
		try {
			List<Food> pageData = JdbcUtils.getQueryRunner().query(sql.toString(), new BeanListHandler<Food>(Food.class), list.toArray());
			pg.setPageData(pageData);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
