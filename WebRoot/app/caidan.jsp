<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script type="text/javascript" src="${pageContext.request.contextPath }/app/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/app/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/app/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/app/style/css/index_1.css" />
	<link href="${pageContext.request.contextPath }/app/style/css/index.css" rel="stylesheet" type="text/css" />
</head>
<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- 显示菜品的div -->
			<div id="top">
				<ul>
					<!-- 循环列出餐品 -->
					<c:forEach items="${foods }" var="food">
						<li>
							<dl>
								<dt>
									<a href="${pageContext.request.contextPath }/home?method=findFoodInfoById&id=${food.id }">
										<img width="214px" height="145px" src="${pageContext.request.contextPath }/app/style/images/baizhuoxia.jpg" />
									</a>
								</dt>
								<dd class="f1">
									<a href="${pageContext.request.contextPath }/home?method=findFoodInfoById&id=${food.id }">${food.foodName }</a>
								</dd>
								<dd class="f2">
									<a href="${pageContext.request.contextPath }/home?method=findFoodInfoById&id=${food.id }">&yen;${food.price }</a>
								</dd>
							</dl>
						</li>
					</c:forEach>
					
				</ul>
			</div>
			
			<!-- 底部分页导航条div -->
			<div id="foot">
				
					
					
						<span
							style="float:left; line-height:53PX; margin-left:-50px; font-weight:bold; ">
							<span style="font-weight:bold">&lt;&lt;</span>
						</span>
					
				
				<div id="btn">
				
					当前&nbsp;${pg.currentPage }&nbsp;|&nbsp;${pg.totalPage }&nbsp;页
					<ul>
						<li><a href="javascript:gotoPage(1)">首页</a></li>
						<li><a href="javascript:gotoPage(${pg.currentPage - 1 })">上一页</a></li>
						<li><a href="javascript:gotoPage(${pg.currentPage + 1 })">下一页</a></li>
						<li><a href="javascript:gotoPage(${pg.totalPage })">末页</a></li>
				
					</ul>
					<!-- <ul>
						参看 百度, 谷歌是 左 5 右 4
						
							<li><a
								href="#">1</a></li>
						
							<li><a
								href="#">2</a></li>
						
					</ul> -->
				</div>
				
					
						<span style="float:right; line-height:53px; margin-right:10px;  ">
							<a
							href="#"
							style=" text-decoration:none;color:#000000; font-weight:bold">&gt;&gt;</a>
						</span>
					
					
				
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
	
	<script type="text/javascript">
		function gotoPage(page) {
			var frm = document.forms[0];
			frm.action = "${pageContext.request.contextPath}/home?method=homeList&currentPage=" + page;
			alert(frm.action);
			frm.submit();
		}
	</script>
	
</body>
</html>
