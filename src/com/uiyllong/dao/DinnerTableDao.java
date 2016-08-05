package com.uiyllong.dao;

import java.util.List;

import com.uiyllong.entity.DinnerTable;

public interface DinnerTableDao {

	//得到所有的餐桌信息
	List<DinnerTable> getAll();

}
