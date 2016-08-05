package com.uiyllong.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.uiyllong.dao.DinnerTableDao;
import com.uiyllong.entity.DinnerTable;
import com.uiyllong.utils.JdbcUtils;

public class DinnerTableDaoImpl implements DinnerTableDao {

	@Override
	public List<DinnerTable> getAll() {
		String sql = "select * from OS_diningTable";
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
