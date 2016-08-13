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
	
	/**
	 * 添加菜品
	 * @param request
	 * @param response
	 * @return
	 */
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
	
	/**
	 * 删除菜品信息
	 * @param request
	 * @param response
	 * @return
	 */
	public Object delete(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String id = request.getParameter("id");
		foodService.delete(Integer.parseInt(id));
		url = "/food?method=list";
		return url;
	}
	
	/**
	 * 根据id查询菜品信息，根据菜系id查找菜系信息 封装food
	 * @param request
	 * @param response
	 * @return
	 */
	public Object findFoodById(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		String id = request.getParameter("id");
		String foodType_id = request.getParameter("foodType_id");
		List<FoodType> foodTypes = foodTypeService.list();
		request.setAttribute("foodTypes", foodTypes);
		FoodType foodType = foodTypeService.findById(Integer.parseInt(foodType_id));
		Food food = foodService.findFoodById(Integer.parseInt(id));
		food.setFoodType(foodType);
		request.setAttribute("food", food);
		url = request.getRequestDispatcher("/pos/updateFood.jsp");
		return url;
	}
	
	/**
	 * 更新菜品信息
	 * @param request
	 * @param response
	 * @return
	 */
	public Object updateFood(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		//接受所有数据
		String id = request.getParameter("id");
		String foodName = request.getParameter("foodName");
		String foodType_id = request.getParameter("foodType_id");
		String price = request.getParameter("price");
		String mprice = request.getParameter("mprice");
		String intro = request.getParameter("intro");
		String img = "暂无修改照片";
		//根据 foodType_id 得到 foodType
		FoodType foodType = foodTypeService.findById(Integer.parseInt(foodType_id));
		//封装数据
		Food food = new Food();
		food.setId(Integer.parseInt(id));
		food.setFoodName(foodName);
		food.setFoodType(foodType);
		food.setPrice(Double.parseDouble(price));
		food.setMprice(Double.parseDouble(mprice));
		food.setIntro(intro);
		food.setImg(img);
		foodService.updateFood(food);
		url = "/food?method=list";
		return url;
	}
	
}
