-- 创建数据库 ordersystem
create database ordersystem character set utf8;

-- 创建餐桌表
create table OS_diningTable(
	id int primary key auto_increment,		-- 餐桌号
	tableName varchar(20),					-- 餐桌名
	tableStatus int default 0,				-- 餐桌状态:0->空闲：1->预定
	orderDate datetime						-- 订餐时间
);

-- 创建菜系表
create table OS_foodType(
	id int primary key auto_increment,		-- 菜系号
	typeName varchar(20)					-- 菜系名
);

-- 创建菜品表
create table OS_foodInfo(
	id int primary key auto_increment,		-- 菜品号
	foodName varchar(20),					-- 菜名
	foodType_id int,						-- 所属菜系
	price double,							-- 原价
	mprice double,							-- 会员价
	intro varchar(200),						-- 简介
	img varchar(100)						-- 菜图片（存图片地址）
);

-- 创建订单表
create table OS_orders(
	id varchar(10) primary key,				-- 订单编号
	table_id int,							-- 餐桌号
	orderDate datetime,						-- 下单日期
	totalPrice double,						-- 总金额
	orderStatus int default 0				-- 订单状态：0->未结账：1->已结账
);

-- 创建订单明细表
create table OS_orderDetail(
	id int primary key auto_increment,		-- 主键
	order_id varchar(10),					-- 外键：引入的是订单表的主键
	food_id int,							-- 外键：引用的是菜信息表的主键
	foodCount int							-- 菜数量
);

-- 添加菜品与菜类别的关系约束
ALTER TABLE OS_foodInfo ADD CONSTRAINT fk_foodInfo_foodType_id FOREIGN KEY(foodType_id) REFERENCES OS_foodType(id);
-- 订单表： 与餐桌表的关系
ALTER TABLE OS_orders ADD CONSTRAINT order_table_id FOREIGN KEY(table_id) REFERENCES OS_diningTable(id);
-- 订单明细： 与订单表的关系
ALTER TABLE OS_orderDetail ADD CONSTRAINT orderDetail_order_id FOREIGN KEY(order_id) REFERENCES OS_orders(id);
-- 订单明细： 与菜信息的关系
ALTER TABLE OS_orderDetail ADD CONSTRAINT orderDetail_food_id FOREIGN KEY(food_id) REFERENCES OS_foodInfo(id);
