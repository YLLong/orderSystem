package com.uiyllong.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uiyllong.entity.Food;
import com.uiyllong.entity.FoodType;

public class FoodServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 展示所有菜品信息
	 * @param request
	 * @param response
	 * @return
	 */
	public Object list(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		List<Food> foods = foodService.list();
		request.setAttribute("foods", foods);
		url = request.getRequestDispatcher("/pos/foodList.jsp");
		return url;
	}
	
	/**
	 * 添加菜品的跳转处理（回显菜系）
	 * @param request
	 * @param response
	 * @return
	 */
	public Object findFoodType(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		List<FoodType> foodTypes = foodTypeService.list();
		request.setAttribute("foodTypes", foodTypes);
		url = request.getRequestDispatcher("/pos/saveFood.jsp");
		return url;
	}
	
	public Object addFood(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String foodType_id = request.getParameter("foodType_id");
		String foodName = request.getParameter("foodName");
		String price = request.getParameter("price");
		String mprice = request.getParameter("mprice");
		String intro = request.getParameter("intro");
		String img = "暂无照片";
		//封装 food 对象
		FoodType foodType = foodTypeService.findById(Integer.parseInt(foodType_id));
		Food food = new Food();
		food.setFoodName(foodName);
		food.setFoodType(foodType);
		food.setPrice(Double.parseDouble(price));
		food.setMprice(Double.parseDouble(mprice));		
		food.setIntro(intro);
		food.setImg(img);
		
		foodService.saveFood(food);
		url = "/food?method=list";
		return url;
	}
	
}
