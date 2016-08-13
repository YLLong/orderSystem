package com.uiyllong.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uiyllong.service.DinnerTableService;
import com.uiyllong.service.FoodService;
import com.uiyllong.service.FoodTypeService;
import com.uiyllong.utils.BeanFactory;
import com.uiyllong.utils.WebUtils;

public class BaseServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected FoodTypeService foodTypeService = BeanFactory.getInstance("foodTypeService", FoodTypeService.class);
	protected DinnerTableService dinnerTableService = BeanFactory.getInstance("dinnerTableService", DinnerTableService.class);
	protected FoodService foodService = BeanFactory.getInstance("foodService", FoodService.class);
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		
		String method = req.getParameter("method");
		if (method == null) {
			method = "home";
		}
		Object reslut = null;
		try {
			Method m = this.getClass().getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			reslut = m.invoke(this, req, resp);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
		WebUtils.conduct(req, resp, reslut);
	}
	
}
