package com.uiyllong.dao.impl;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.inject.New;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.uiyllong.dao.DinnerTableDao;
import com.uiyllong.entity.DinnerTable;
import com.uiyllong.utils.JdbcUtils;

public class DinnerTableDaoImpl implements DinnerTableDao {

	/**
	 * 列出餐桌信息
	 */
	@Override
	public List<DinnerTable> getAll() {
		String sql = "select * from OS_diningTable";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 添加餐桌信息
	 */
	@Override
	public void add(String tableName) {
		String sql = "insert into OS_diningTable(tableName) values(?)";
		try {
			JdbcUtils.getQueryRunner().update(sql,tableName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除餐桌信息
	 */
	@Override
	public void delete(int id) {
		String sql = "delete from OS_diningTable where id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 更新餐桌（顶退操作）
	 */
	@Override
	public void update(DinnerTable dinnerTable) {
		String sql = "update OS_diningTable set tableStatus = ?, orderDate = ? where id = ?";
		try {
			JdbcUtils.getQueryRunner().update(sql, dinnerTable.getTableStatus(), dinnerTable.getOrderDate(), dinnerTable.getId());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据 id 得到餐桌信息
	 */
	@Override
	public DinnerTable getTableById(int id) {
		String sql = "select * from OS_diningTable where id = ?";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
