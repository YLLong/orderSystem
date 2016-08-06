package com.uiyllong.service;

import java.util.List;

import com.uiyllong.entity.DinnerTable;

public interface DinnerTableService {

	//得到所有餐桌信息
	List<DinnerTable> getAll();

	//添加餐桌信息
	void add(String tableName);
	
	//删除餐桌信息
	void delete(int id);

	//更新餐桌状态
	void update(DinnerTable dinnerTable);

}
