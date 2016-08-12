<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
 	<!-- 包含公共的JSP代码片段 -->
	
<title>无线点餐平台</title>



<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="${pageContext.request.contextPath }/pos/style/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pos/style/js/page_common.js"></script>
<link href="${pageContext.request.contextPath }/pos/style/css/common_style_blue.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pos/style/css/index_1.css" />

</head>
<body>
<!-- 页面标题 -->
<div id="TitleArea">
	<div id="TitleArea_Head"></div>
	<div id="TitleArea_Title">
		<div id="TitleArea_Title_Content">
			<img border="0" width="13" height="13" src="${pageContext.request.contextPath }/pos/style/images/title_arrow.gif"/> 餐桌列表
		</div>
    </div>
	<div id="TitleArea_End"></div>
</div>


<!-- 过滤条件 -->
<div id="QueryArea">
	<form action="/wirelessplatform/board.html" method="get">
		<input type="hidden" name="method" value="search">
		<input type="text" name="keyword" title="请输入餐桌名称">
		<input type="submit" value="搜索">
	</form>
</div>


<!-- 主内容区域（数据列表或表单显示） -->
<div id="MainArea">
    <table class="MainArea_Content" cellspacing="0" cellpadding="0">
        <!-- 表头-->
        <thead>
            <tr align="center" valign="middle" id="TableTitle">
				<td>编号</td>
				<td>桌名</td>
				<td>状态</td>
				<td>预定时间</td>
				<td>操作</td>
			</tr>
		</thead>	
		<!--显示数据列表 -->
        <tbody id="TableData">
			<c:choose>
				<c:when test="${not empty tables }">
					<c:forEach items="${tables }" var="table">
							<tr class="TableDetail1">
								<td align="center">${table.id }&nbsp;</td>
								<td align="center">${table.tableName }&nbsp;</td>
								<td align="center">${table.tableStatus == 1 ? "预定":"空闲" }</td>
								<td align="center">
									<fmt:formatDate value="${table.orderDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<a href="${pageContext.request.contextPath }/dinnertable?method=updateDinnerTable&id=${table.id }" class="FunctionButton isorder">${table.tableStatus == 1 ? "退订":"预定" }</a>
									<a href="${pageContext.request.contextPath }/dinnertable?method=deleteDinnerTable&id=${table.id }" onClick="return delConfirm();" class="FunctionButton">删除</a>
								</td>
							</tr>
						</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td align="center" colspan="4">没有餐桌信息！请添加后查询。。。。</td>
					</tr>
				</c:otherwise>
			</c:choose>
        </tbody>
    </table>
	
   <!-- 其他功能超链接 -->
	<div id="TableTail" align="center">
		<div class="FunctionButton"><a href="${pageContext.request.contextPath }/pos/saveBoard.jsp">添加</a></div>
    </div> 
</div>

<script type="text/javascript">
	$(function(){
		$(".isorder").click(function(){
			var val = $(this).text();
			var obj = $(this);
			if(val=="预定") {
				var url = $(this).attr("href") + "&tableStatus=1";
				$.post(url, function(data){
					$(obj).parent().prev().prev().text("预定");
					$(obj).parent().prev().text(data);
					$(obj).text("退订");
				});
			} else {
				var url = $(this).attr("href") + "&tableStatus=2";
				$.post(url, function(data) {
					$(obj).parent().prev().prev().text("空闲");
					$(obj).parent().prev().text(data);
					$(obj).text("预定");
				});
			}
			return false;
		});
	})
</script>

</body>
</html>
