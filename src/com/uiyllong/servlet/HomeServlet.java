package com.uiyllong.servlet;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uiyllong.entity.Condition;
import com.uiyllong.entity.DinnerTable;
import com.uiyllong.entity.Food;
import com.uiyllong.entity.FoodType;
import com.uiyllong.entity.Order;
import com.uiyllong.entity.OrderDetail;
import com.uiyllong.entity.PageBean;

public class HomeServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 下单
	 * @param request
	 * @param response
	 * @return
	 */
	public Object order(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		HttpSession session = request.getSession();
		//封装 order
		Order order1 = new Order();
		order1.setId(getOrderId(4));
		String tableId = (String) session.getAttribute("tableId");
		DinnerTable table = dinnerTableService.getTableById(Integer.parseInt(tableId));
		order1.setTable(table);
		order1.setOrderDate(new Date());
		double total = (double) session.getAttribute("total");
		order1.setTotalPrice(total);
		orderService.ordering(order1);
		//封装 订单条目
		@SuppressWarnings("unchecked")
		Map<String, OrderDetail> orderMap =  (Map<String, OrderDetail>) session.getAttribute("orders");
		for (OrderDetail orderDetail : orderMap.values()) {
			orderDetail.setOrder_id(order.getId());
			orderService.saveOrderDetail(orderDetail);
		}
	 	Map<String, OrderDetail> ordercloneMap = new HashMap<String, OrderDetail>();
	 	for(Iterator<Entry<String, OrderDetail>> iter = order.orderMap.entrySet().iterator();iter.hasNext();) {
            Map.Entry<String, OrderDetail> element = iter.next();
            String strKey = (String) element.getKey();
            OrderDetail strObj = (OrderDetail) element.getValue();
            ordercloneMap.put(strKey, strObj);
	 	}
		session.setAttribute("order", ordercloneMap);
		session.removeAttribute("orders");
		order.orderMap.clear();
		url = request.getRequestDispatcher("/app/clientOrderList.jsp");
		return url;
	}
	
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
		String tableId;
		if (session.getAttribute("tableId") == null) {
			tableId = request.getParameter("tableId");
			session.setAttribute("tableId", tableId);			
		} else {
			tableId = (String) session.getAttribute("tableId");
			session.setAttribute("tableId", tableId);
		}
		
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
	
	/**
	 * 加入餐车
	 * @param request
	 * @param response
	 * @return
	 */
	Order order = new Order();
	public Object addCart(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		OrderDetail orderDetail;
		HttpSession session = request.getSession();
		List<FoodType> foodTypes = foodTypeService.list();
		session.setAttribute("foodTypes", foodTypes);
		String food_id = request.getParameter("food_id");
		Food food = foodService.findFoodById(Integer.parseInt(food_id));
		if (order.orderMap.size() > 0) {
			Collection<String> keys = order.orderMap.keySet();
			int count = 0;
			for (String key : keys) {
				++count;
				if (food_id.equals(key)) {
					int i = order.orderMap.get(key).getFoodCount();
					order.orderMap.get(key).setFoodCount(i + 1);
					break;
				} else {
					if (count == keys.size()) {
						orderDetail = new OrderDetail();
						orderDetail.setFood(food);
						orderDetail.setFoodCount(1);
						order.orderMap.put(food_id, orderDetail);
					} else {
						continue;					
					}
				}
			}
		} else {
			orderDetail = new OrderDetail();
			orderDetail.setFood(food);
			orderDetail.setFoodCount(1);
			order.orderMap.put(food_id, orderDetail);
		}
		session.setAttribute("orders", order.orderMap);
		session.setAttribute("total", totalAll(order.orderMap));
		url = request.getRequestDispatcher("/app/clientCart.jsp");
		return url;
	}
	
	/**
	 * 返回总价格
	 * @param orderMap
	 * @return
	 */
	public double totalAll(Map<String, OrderDetail> orderMap) {
		double total = 0;
		Collection<OrderDetail> values = orderMap.values();
		for (Iterator<OrderDetail> it = values.iterator(); it.hasNext();) {
			OrderDetail orderDetail = (OrderDetail) it.next();
			total += orderDetail.subTotal();
		}
		BigDecimal b = new BigDecimal(total); 
		total = b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();
		return total;
	}
	
	/**
	 * 自动生成订单号
	 * @return
	 */
	public String getOrderId(int len) {
		int[] param = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        Random rand = new Random();
        for (int i = param.length; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = param[index];
            param[index] = param[i - 1];
            param[i - 1] = tmp;
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            result = result * 10 + param[i];
        }
        String str= String.valueOf(System.currentTimeMillis());
        str = str.substring(str.length()-4,str.length());
        String orderId = str + String.valueOf(result);
        return orderId;
	}
	
}
