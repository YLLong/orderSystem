package com.uiyllong.utils;


import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库连接工具类
 * @author Zero_Dragon
 *
 */
public class JdbcUtils {

	private static DataSource dataSource;
	static {
		dataSource = new ComboPooledDataSource();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public static QueryRunner getQueryRunner() {
		return new QueryRunner(dataSource);
	}
	
}
