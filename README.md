# orderSystem
一个订餐系统

工具：myeclipse2016 数据库：mysql 数据库连接方式：DBUtils

# 订餐系统功能：
  1. 餐桌模块
  2. 菜类别模块(菜系)
  3. 菜信息    (菜品)
  4. 订单

详细分析：
	1. 后台录入的餐桌， 要在前台首页显示； 且只显示未预定
	2. 后台录入的菜类别， 在前台主页显示
	3. 后台录入的菜信息，在前台主页显示
	4. 前台生成订单后，在后台显示订单详细 
# 数据库设计
	见 ordersystem.sql 文件
	
# 编码
	1、修改前端处理事件的代码
	2、编码思路：从上到下，从servlet->service->dao
	3、模块化的思路
# 问题
	1、在进行 servlet 代码编写中，出现了多种方法在同一servlet中调用，由于方法执行后会转发或者重定向，所有会出现很多重复的
	代码。所有写了一个 BaseServlet，使用反射机制简化代码。
	2、在各层类中需要定义一个下层的类，并使用。由于只需要使用单列就ok,所有写了一个工厂类来实现单列。
		protected FoodTypeService foodTypeService = 
						BeanFactory.getInstance("foodTypeService", FoodTypeService.class);
		配置文件：instance.properties
		工厂类：com.uiyllong.utils.BeanFactory
	3、在实现餐桌状态改变的时候出现了在第二个餐桌进行状态改变时，出现空指针异常。
		我估计是异步请求的时候出了一点问题？