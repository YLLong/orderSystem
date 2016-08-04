package com.uiyllong.utils;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 网页处理类：是转发还是重定向
 * @author Zero_Dragon
 *
 */
public class WebUtils {

	public static void conduct(HttpServletRequest request, HttpServletResponse response, Object url) throws ServletException, IOException {
		if (url instanceof RequestDispatcher) {
			((RequestDispatcher)url).forward(request, response);
		} else if (url instanceof String) {
			response.sendRedirect(request.getContextPath() + url);
		}
	}
	
}
