drop database fruits;
create database fruits;
use fruits;

#商品标签表
create table commodity_tag
(
	commodity_tag_id int primary key auto_increment comment"商品标签表编号",
	commodity_tag_name varchar(30) not null comment "商品标签名"
)default charset=utf8 comment "商品标签表";

#商品单位表
create table commodity_company
(
	commodity_company_id int primary key auto_increment comment"商品单位表编号",
	commodity_company_name varchar(10) not null comment "单位名称"
)default charset=utf8 comment "商品单位表";

#供应商表
create table supplier
(
	supplier_id int primary key auto_increment comment"供应商编号",
	supplier_name varchar(100) comment "供应商名称",
	supplier_address varchar(200) comment"供应商地址",
	supplier_tel varchar(11) comment"联系电话",
	supplier_status bit default 1 comment "状态",
	supplier_remarks varchar(200) comment"备注"
)default charset=utf8 comment "供应商表";

#进货表
create table purchase
(
	purchase_id int primary key auto_increment comment"进货编号",
	purchase_count double  comment "进货的数量",
	purchase_status bit default 1 comment"状态",
	purchase_name varchar(40) comment"负责人",
	commodity_company_id int references commodity_company(commodity_company_id),#商品单位表外键
	commodity_tag_id int references commodity_tag(commodity_tag_id),#商品标签
	supplier_id int references supplier(supplier_id)#供应商表外键
)default charset=utf8 comment "进货表";

#库存表
create table stock
(
	stock_id int primary key auto_increment comment"库存编号",
	stock_count double comment "库存数量",
	stock_remarks varchar(50) comment"备注",
	stock_datetime datetime default now(),
	purchase_id int references purchase(purchase_id),#进货表外键
	commodity_tag_id int references commodity_tag(commodity_tag_id),#商品标签外键
	commodity_company_id int references commodity_company(commodity_company_id)#商品单位外键
)default charset=utf8 comment "库存表";


#商品表
create table commodity
(
	commodity_id int primary key auto_increment comment"商品表编号",
	commodity_name varchar(100) not null comment "商品描述",
	commodity_photo varchar(256) comment "商品图片",
	commodity_price decimal(18,5)  comment"商品销售价格",
	commodity_count double  comment"销售数量",
	commodity_status bit default 1 comment"商品状态，默认为上架",
	stock_id int references stock(stock_id),#库存表外键
	commodity_company_id int references commodity_company(commodity_company_id)#商品单位表外键
)default charset=utf8 comment "商品单位表";


#顾客表
create table customer
(
	customer_id int primary key auto_increment comment"顾客表编号",
	customer_user varchar(18) not null comment "顾客账号",
	customer_password varchar(16) not null comment "密码",
	customer_name varchar(10) not null comment "姓名",
	customer_photo varchar(256)  comment"照片",
	customer_tel varchar(11) not null comment"手机号码",
	customer_card varchar(18) not null comment"身份证",
	customer_state bit default 1 comment"用户状态"
)default charset=utf8 comment "顾客表";

#顾客地址表
create table customer_address
(
	customer_address_id int primary key auto_increment comment"顾客地址表编号",
	customer_address_take varchar(10) not null comment "取货人",
	customer_address_tel varchar(11) not null comment "电话",
	customer_address_info varchar(256) not null comment "地址信息",
	customer_id int references customer(customer_id) #顾客表外键
)default charset=utf8 comment "顾客地址表";


#购物车表
create table shopping_cart
(
	shopping_cart_id int primary key auto_increment comment"购物车表编号",
	shopping_cart_count int default 1 comment "数量",
	shopping_cart_status bit default 0 comment "状态",
	commodity_id int references commodity(commodity_id),#商品表外键
	customer_id int references customer(customer_id) #顾客表外键
)default charset=utf8 comment "购物车表";

#快递分类表
create table express_type
(
	express_type_id int primary key auto_increment comment"快递分类表编号",
	express_type_name varchar(20) not null comment "名称"
)default charset=utf8 comment "快递分类表";

#快递表
create table express
(
	express_id int primary key auto_increment comment"快递表编号",
	express_number varchar(100) not null comment "运送单号",
	express_datetime datetime default now() comment "发货时间",
	express_type_id int references express_type(express_type_id)#快递表外键
)default charset=utf8 comment "快递表";


#订单表
create table `order`
(
	order_id int primary key auto_increment comment"订单表编号",
	order_datetime datetime default now() comment "生成订单时间",
	order_price decimal(18,5) default 0 comment "总价",
	order_status bit default 1,#订单状态
	shopping_cart_id int references shopping_cart(shopping_cart_id), #购物车表外键
	express_id int references express(express_id) #快递表外键
)default charset=utf8 comment "订单表";


#销售表
create table sale
(
	sale_id int primary key auto_increment comment"销售表编号",
	sale_datetime datetime default now() comment "销售日期",
	order_id int references `order`(order_id)#订单表外键
)default charset=utf8 comment "销售表";

#退货表
create table cancel
(
	cancel_id int primary key auto_increment comment"退货表编号",
	cancel_datetime datetime default now() comment "日期",
	cancel_remarks varchar(256) comment"退货备注",
	cancel_status bit default 0 comment"状态",
	oder_id int references `order`(oder_id),#订单表外键
	customer_id int references customer(customer_id)#顾客表外键
)default charset=utf8 comment "退货表";

#管理员表
create table admin
(
	admin_id int primary key auto_increment comment"管理员表编号",
	admin_user varchar(10) not null comment "账号",
	admin_password varchar(16) comment"密码",
	admin_name varchar(10) comment"姓名",
	admin_tel varchar(11) not null comment"电话",
	admin_photo varchar(200) comment"照片"
)default charset=utf8 comment "退货表";





