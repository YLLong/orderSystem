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

}
