package com.uiyllong.service.impl;

import java.util.List;

import com.uiyllong.dao.DinnerTableDao;
import com.uiyllong.entity.DinnerTable;
import com.uiyllong.service.DinnerTableService;
import com.uiyllong.utils.BeanFactory;

public class DinnerTableServiceImpl implements DinnerTableService {

	protected DinnerTableDao dinnerTableDao = BeanFactory.getInstance("dinnerTableDao", DinnerTableDao.class);
	
	@Override
	public List<DinnerTable> getAll() {
		return dinnerTableDao.getAll();
	}

	@Override
	public void add(String tableName) {
		dinnerTableDao.add(tableName);
	}

	@Override
	public void delete(int id) {
		dinnerTableDao.delete(id);
	}

	@Override
	public void update(DinnerTable dinnerTable) {
		dinnerTableDao.update(dinnerTable);
	}

	@Override
	public DinnerTable getTableById(int id) {
		return dinnerTableDao.getTableById(id);
	}

}
