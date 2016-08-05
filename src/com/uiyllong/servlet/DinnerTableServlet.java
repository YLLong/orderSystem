package com.uiyllong.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uiyllong.entity.DinnerTable;

public class DinnerTableServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		List<DinnerTable> dinnerTables = dinnerTableService.getAll();
		request.setAttribute("tables", dinnerTables);
		url = request.getRequestDispatcher("/pos/boardList.jsp");
		return url;
	}
	
}
