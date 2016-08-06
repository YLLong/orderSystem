package com.uiyllong.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uiyllong.entity.DinnerTable;

public class DinnerTableServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 列出所有餐桌信息
	 * @param request
	 * @param response
	 * @return
	 */
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		List<DinnerTable> dinnerTables = dinnerTableService.getAll();
		request.setAttribute("tables", dinnerTables);
		url = request.getRequestDispatcher("/pos/boardList.jsp");
		return url;
	}
	
	/**
	 * 添加餐桌信息
	 * @param request
	 * @param response
	 * @return
	 */
	public Object addDinnerTable(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String tableName = request.getParameter("tableName");
		dinnerTableService.add(tableName);
		url = "/dinnertable?method=list";
		return url;
	}
	
	/**
	 * 删除餐桌信息
	 * @param request
	 * @param response
	 * @return
	 */
	public Object deleteDinnerTable(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String id = request.getParameter("id");
		dinnerTableService.delete(Integer.parseInt(id));
		url = "/dinnertable?method=list";
		return url;
	}
	
	/**
	 * 预定与退订的变化处理
	 * @param request
	 * @param response
	 * @return
	 */
	public Object updateDinnerTable(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String id = request.getParameter("id");
		String tableStatus = request.getParameter("tableStatus");
		//构建对象
		DinnerTable dinnerTable = new DinnerTable();
		dinnerTable.setId(Integer.parseInt(id));
		System.out.println(tableStatus);
		dinnerTable.setTableStatus(Integer.parseInt(tableStatus));
		Date orderDate = (Integer.parseInt(tableStatus) == 0) ? null:new Date();
		dinnerTable.setOrderDate(orderDate);
		dinnerTableService.update(dinnerTable);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(orderDate);
		try {
			PrintWriter writer = response.getWriter();
			writer.write(date);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("id:" +id + "; tableStatus:" + tableStatus);
		return url;
	}
	
}
