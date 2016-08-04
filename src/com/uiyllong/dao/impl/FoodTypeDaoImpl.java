package com.uiyllong.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.uiyllong.dao.FoodTypeDao;
import com.uiyllong.entity.FoodType;
import com.uiyllong.utils.JdbcUtils;

public class FoodTypeDaoImpl implements FoodTypeDao {

	/**
	 * 查询所有菜系
	 */
	@Override
	public List<FoodType> list() {
		String sql = "select * from OS_foodType";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodType>(FoodType.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加菜系
	 */
	@Override
	public void save(FoodType foodType) {
		String sql = "insert into OS_foodType(typeName) values(?)";
		try {
			JdbcUtils.getQueryRunner().update(sql, foodType.getTypeName());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据菜系id 得到菜系信息
	 */
	@Override
	public FoodType findById(int id) {
		String sql = "select * from OS_foodType where id = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<FoodType>(FoodType.class), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int id) {
		String sql = "delete from OS_foodType where id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(FoodType foodType) {
		String sql = "update OS_foodType set typeName = ? where id =?";
		try {
			JdbcUtils.getQueryRunner().update(sql, foodType.getTypeName(), foodType.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
