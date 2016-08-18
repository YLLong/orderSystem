package com.uiyllong.dao;

import java.util.List;

import com.uiyllong.entity.DinnerTable;

public interface DinnerTableDao {

	//得到所有的餐桌信息
	List<DinnerTable> getAll();

	//添加餐桌信息
	void add(String tableName);
	
	//删除餐桌信息
	void delete(int id);

	//更新餐桌信息
	void update(DinnerTable dinnerTable);

	//根据id 得到餐桌信息
	DinnerTable getTableById(int id);

}
