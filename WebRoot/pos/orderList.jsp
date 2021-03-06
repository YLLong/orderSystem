﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/pos/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pos/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/pos/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pos/style/css/index_1.css" />
	<script type="text/javascript">
		setInterval(function(){
			window.location.href = "/wirelessplatform/client.html?method=list";
		},1000 * 50);
	</script>
</head>
<body>
	<!-- 页面标题 -->
	<div id="TitleArea">
		<div id="TitleArea_Head"></div>
		<div id="TitleArea_Title">
			<div id="TitleArea_Title_Content">
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath }/pos/style/images/title_arrow.gif" /> 餐厅订单列表
			</div>
		</div>
		<div id="TitleArea_End"></div>
	</div>

	<!-- 主内容区域（数据列表或表单显示） -->
	<div id="MainArea">
		<table class="MainArea_Content" align="center" cellspacing="0" cellpadding="0">
			<!-- 表头-->
			<thead>
				<tr align="center" valign="middle" id="TableTitle">
					<td>订单编号</td>
					<td>餐桌名</td>
					<td>下单日期</td>
					<td>总金额</td>
					<td>状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<!--显示数据列表 -->
			<tbody id="TableData" align="center">
			
				<c:forEach items="${orderList }" var="order">
					<tr height="60">
				 		<td>${order.id }</td>
				 		<td>${order.table.tableName }</td>
				 		<td><fmt:formatDate value="${order.orderDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				 		<td>${order.totalPrice }</td>
				 		<td>${order.orderStatus == 1 ? "已结账" : "未结账" }</td>
				 		<td>
							<a href="${pageContext.request.contextPath }/order?method=orderDetail&orderId=${order.id }" class="FunctionButton">详细</a> 
				 			<a href="#" class="FunctionButton">结账</a>
				 		</td>
			 		</tr>
				</c:forEach>
			
			</tbody>
		</table>
		<!-- 其他功能超链接 -->
		<div id="TableTail" align="center">
		</div>
	</div>
</body>
</html>
