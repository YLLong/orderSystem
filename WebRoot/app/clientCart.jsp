<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/style/css/index.css" />
	<script type="text/javascript">
		/** // 删除菜品项
		function removeSorder(node) {
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=removeSorder&gid="+gid;
		}
		
		// 修改菜品项数量
		function alterSorder(node) {
			var snumber = node.value;
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=alterSorder&gid="+gid+"&snumber="+snumber;
		}
		*/
		// 下单
		function genernateOrder() {
			window.location.href = "${pageContext.request.contextPath }/app/clientOrderList.jsp";
		}
	</script>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 餐车div -->
			<div id="count">
				<table align="center" width="100%">
					<tr height="40">
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<td align="center" width="20%">小计</td>
				 		<td align="center" width="20%">操作</td>
				 	</tr>
				 	
					<c:forEach items="${orders }" var="order">
						<tr height="60">
						 		<td align="center" width="20%">${order.getValue().food.foodName }</td>
						 		<td align="center" width="20%">￥${order.getValue().food.price }</td>
						 		<td align="center" width="20%">
						 			<input type="text" value="${order.getValue().foodCount }" size="3" lang="3" onblur="alterSorder(this)"/>
						 		</td>
						 		<td align="center" width="20%">￥${order.getValue().subTotal() }</td>
						 		<td align="center" width="20%">
						 			<input type="button" value="删除" class="btn_next" lang="3" onclick="removeSorder(this)" />
						 		</td>
					 	</tr>
					</c:forEach>
					
					<tr>
						<td colspan="6" align="right">总计:
							<span style="font-size:36px;">&yen;&nbsp;${total }</span>
							<label
								id="counter" style="font-size:36px"></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
							
								
								
									<input type="button" value="下单" class="btn_next" onclick="genernateOrder()" />
								
							
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- 右边菜系列表，菜品搜索框  -->
		<div id="dish_class">
			<div id="dish_top">
				<ul>
				<li class="dish_num"></li>
					<li>
						<a href="${pageContext.request.contextPath }/home?method=homeList">
							<img src="${pageContext.request.contextPath }/app/style/images/call2.gif" />
						</a>
					</li>
				</ul>
			</div>

			<div id="dish_2">
				<ul>
					
						<c:forEach items="${foodTypes }" var="foodType">
						<li>
							<a href="${pageContext.request.contextPath }/home?method=homeList&foodType_id=${foodType.id }">${foodType.typeName }</a>
						</li>
					</c:forEach>
					
				</ul>
			</div>
			<div id="dish_3">
				<!-- 搜索菜品表单  -->
				<form action="${pageContext.request.contextPath }/home?method=homeList" method="post">
					<table width="166px">
						<tr>
							<td>
								<input type="text" id="dish_name" name="foodName" class="select_value" /> 
								<!-- <input type="hidden" value="selectFood" name="method"> -->
							</td>
						</tr>
						<tr>
							<td><input type="submit" id="sub" value="" /></td>
						</tr>
						<tr>
							<td>
								<a href="#">
									<img src="${pageContext.request.contextPath }/app/style/images/look.gif" />
								</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
