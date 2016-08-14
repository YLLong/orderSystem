package com.uiyllong.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uiyllong.entity.Condition;
import com.uiyllong.entity.DinnerTable;
import com.uiyllong.entity.Food;
import com.uiyllong.entity.FoodType;
import com.uiyllong.entity.PageBean;

public class HomeServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 前台进入首页显示没有再用餐桌
	 * @param request
	 * @param response
	 * @return
	 */
	public Object home(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		List<DinnerTable> tables = dinnerTableService.getAll();
		request.setAttribute("tables", tables);
		url = request.getRequestDispatcher("/app/index.jsp");
		return url;
	}
	
	/**
	 * 进入主页分页展示菜单信息（菜品 菜系）
	 * @param request
	 * @param response
	 * @return
	 */
	public Object homeList(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		HttpSession session = request.getSession();
		//餐桌号
		@SuppressWarnings("unused")
		String tableId = request.getParameter("tableId");
		
		String currentPage = request.getParameter("currentPage");
		String foodType_id = request.getParameter("foodType_id");
		String foodName = request.getParameter("foodName");
		PageBean<Food> pg = new PageBean<Food>();		
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		pg.setCurrentPage(Integer.parseInt(currentPage));
		Condition condition = new Condition();
		String type_id = null;
		if (foodType_id == null ||"".equals(foodType_id.trim())) {
			if (session.getAttribute("foodType_id") == null) {
				foodType_id = "1";
				session.setAttribute("foodType_id", foodType_id);				
			}
			type_id = (String) session.getAttribute("foodType_id");
			
		} else {
			session.setAttribute("foodType_id", foodType_id);
			type_id = (String) session.getAttribute("foodType_id");
		}
		condition.setFoodType_id(Integer.parseInt(type_id));
		condition.setFoodName(foodName);
		List<FoodType> foodTypes = foodTypeService.list();
		pg.setCondition(condition);
		foodService.pageList(pg);
		request.setAttribute("foodTypes", foodTypes);
		request.setAttribute("foods", pg.getPageData());
		request.setAttribute("pg", pg);
		url = request.getRequestDispatcher("/app/caidan.jsp");
		return url;
	}

	/**
	 * 菜品详细
	 * @param request
	 * @param response
	 * @return
	 */
	public Object findFoodInfoById(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String id = request.getParameter("id");
		List<FoodType> foodTypes = foodTypeService.list();
		request.setAttribute("foodTypes", foodTypes);
		Food food = foodService.findFoodById(Integer.parseInt(id));
		request.setAttribute("food", food);
		url = request.getRequestDispatcher("/app/caixiangxi.jsp");
		return url;
	}
	
}
