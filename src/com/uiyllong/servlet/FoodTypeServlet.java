package com.uiyllong.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uiyllong.entity.FoodType;

public class FoodTypeServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 修改菜系
	 * @param request
	 * @param response
	 * @return
	 */
	public Object updateFoodType(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String id = request.getParameter("id");
		String typeName = request.getParameter("typeName");
		FoodType foodType = new FoodType();
		foodType.setId(Integer.parseInt(id));
		foodType.setTypeName(typeName);
		foodTypeService.update(foodType);
		url = "/foodtype?method=list";
		return url;
	}
	
	/**
	 * 删除菜系
	 * @param request
	 * @param response
	 * @return
	 */
	public Object deleteFoodType(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String id = request.getParameter("id");
		foodTypeService.delete(Integer.parseInt(id));
		url = "/foodtype?method=list";
		return url;
	}
	
	/**
	 * 根据菜系 id 找到菜系信息
	 * @return
	 */
	public Object findFoodType(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String id = request.getParameter("id");
		FoodType foodType = foodTypeService.findById(Integer.parseInt(id));
		request.setAttribute("foodType", foodType);
		url = request.getRequestDispatcher("/pos/type/foodType_update.jsp");
		return url;
	}
	
	/**
	 * 添加菜系
	 * @return
	 */
	public Object addFoodType(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String typeName = request.getParameter("typeName");
		FoodType foodType = new FoodType();
		foodType.setTypeName(typeName);
		foodTypeService.save(foodType);
		url = request.getRequestDispatcher("/foodtype?method=list");
		return url;
	}
	
	/**
	 * 返回所有菜系结果
	 * @return
	 */
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		request.setAttribute("foodTypes", foodTypeService.list());
		url = request.getRequestDispatcher("/pos/type/foodType_list.jsp");
		return url;
	}

}
