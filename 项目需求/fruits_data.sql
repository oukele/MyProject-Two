use fruits;

#添加管理员
insert into admin(admin_user,admin_password,admin_name,admin_tel) values
("oukele","oukele","欧可乐","12345678901"),
("ou","ou","欧乐乐","09876543211"),
("admin","admin","管理员","56789054321");

select * from admin;

#添加顾客
insert into customer(customer_user,customer_password,customer_name,customer_tel,customer_card) values
("haha","haha","顾客1","12345678901","123456789012345678"),
("oukele","oukele","欧可乐","09876543211","45222619990828181X"),
("guke","123","顾客3","5678905678","123456789012345678");

select * from customer;

#添加顾客地址
insert into customer_address(customer_address_take,customer_address_tel,customer_address_info,customer_id) values
("老ha","12345543211","广西来宾市兴宾区五山乡六贝村委欧屯",1),
("老haha","56789043211","广西来宾市兴宾区五山乡六贝村委欧屯27号",1),
("欧可乐","12345543211","广东省珠海市南方IT学院",2),
("欧小乐","12345543211","广东省珠海市南方IT学院501宿舍",2),
("欧大乐","54321678908","广东省珠海市南方IT学院XXX宿舍",2),
("老guke","12345543212","这是一个模拟地址,老顾客专用",3),
("小guke","12121345677","这也是一个模拟地址,小顾客专用",3);

select * from customer_address;

#添加供应商
insert into supplier(supplier_name,supplier_address,supplier_tel,supplier_remarks) values
("生态红苹果有限公司","广东省珠海市白蕉镇XXX路XXX号","12345678901","这是卖苹果的的纯生态公司"),
("进口香蕉有限公司","广东省广州市番禺区XXX号","89012555555","进口的香蕉，顾客购买量比较多"),
("生态橘子有限公司","广东省珠海市白蕉镇YYY路YYY号","12345678901","这是卖橘子的的纯生态公司"),
("西瓜有限公司","广东省中山市XXX路XXX号","12345678901","纯生态的西瓜，挺甜的");

select * from supplier;

#商品单位
insert into commodity_company(commodity_company_name) values("斤"),("箱"),("个");

select * from commodity_company;

#商品标签
insert into commodity_tag(commodity_tag_name) values("苹果"),("香蕉"),("橘子"),("西瓜");

select * from commodity_tag;

#进货
#数量、负责人、单位表外键、商品标签、供应商外键
insert into purchase(purchase_count,purchase_name,commodity_company_id,commodity_tag_id,supplier_id) values
(10,"进货人-苹果",2,1,1),
(60,"进货人-香蕉",1,2,2),
(20,"进货人-橘子",2,3,3),
(30,"进货人-西瓜",3,4,4);

#库存
#存入数量,备注，进货表外键，商品标签外键,商品单位
insert into stock(stock_count,stock_remarks,purchase_id,commodity_tag_id,commodity_company_id)values
(0,"苹果不容易坏",1,1,2),
(20,"香蕉啦",2,2,1),
(0,"橘子啊",3,3,2),
(15,"西瓜咯",4,4,3);

select * from stock;

#商品表
#商品描述、商品图片、价格、销售数量、库存表外键、单位表外键
insert into commodity(commodity_name,commodity_photo,commodity_price,commodity_count,stock_id,commodity_company_id) values
("红富士苹果 超甜 超好吃 超脆","images/u=2454425123,1615136153&fm=27&gp=0.jpg",40.5,10,1,2),
("香喷喷的巴西大香蕉","images/u=863011583,584886010&fm=27&gp=0.jpg",6,40,2,1),
("超甜的橘子","images/u=4293497380,2997629945&fm=27&gp=0.jpg",30,20,3,2),
(" 无籽西瓜 超甜 超红 ","images/u=2657775863,599903496&fm=27&gp=0.jpg",8,15,4,3);

select * from commodity;

#添加快递分类
insert into express_type(express_type_name) values("顺风快递"),("圆通快递"),("韵达快递"),("中通快递");

select * from express_type;

#模拟快递发送
insert into express (express_number,express_type_id) values
("顺风-A112233",1),
("圆通-B112233",2),
("韵达-C112233",3),
("中通-D112233",4);

select * from express;

#取出商品的所有信息
select * from commodity c 
	left join commodity_company cp on c.commodity_company_id=cp.commodity_company_id;

#显示库存表中全部有关信息
select * from stock s 
	left join commodity_tag ct on s.commodity_tag_id = ct.commodity_tag_id;
#根据库存唯一编号查出对应的数据
select * from stock s 
	left join commodity_tag t on s.commodity_tag_id = t.commodity_tag_id 
	left join commodity_company cp on s.commodity_company_id = cp.commodity_company_id 
	where stock_id = 1;

#根据 供应商状态 查询 数据
select * from supplier where supplier_status = 1;
#根据 供应商地址 搜索 供应商
select * from supplier where supplier_address like concat('%','','%');
#查询 进货表的 信息
select * from purchase p 
	left join supplier s on p.supplier_id = s.supplier_id 
	left join commodity_company c on p.commodity_company_id = c.commodity_company_id
	left join commodity_tag ct on p.commodity_tag_id = ct.commodity_tag_id; 

#进货表 根据 负责人 或者 供应商名称 、商品标签 进行 模糊搜索
select * from purchase p 
	left join supplier s on p.supplier_id = s.supplier_id 
	left join commodity_company c on p.commodity_company_id = c.commodity_company_id
	left join commodity_tag ct on p.commodity_tag_id = ct.commodity_tag_id 
	where p.purchase_name like concat('%','A','%') 
		or  s.supplier_name like concat('%','生态','%')
		or  ct.commodity_tag_name like concat('%','苹果','%');	
# 进货表 根据 进货状态 进行 搜索
select * from purchase p 
	left join supplier s on p.supplier_id = s.supplier_id 
	left join commodity_company c on p.commodity_company_id = c.commodity_company_id
	left join commodity_tag ct on p.commodity_tag_id = ct.commodity_tag_id where purchase_status = 0; 

#编辑 进货信息
#update purchase
#	set purchase_count =10 ,
#       purchase_name ='苹果Apple' ,
#       commodity_company_id =2 ,
#       supplier_id = 1 
#   where purchase_id = 1;

# 查看库存表 信息
select * from stock s 
	left join commodity_tag ct on ct.commodity_tag_id = s.commodity_tag_id
	left join commodity_company cp on cp.commodity_company_id = s.commodity_company_id ;
	
# 模糊搜索 库存表信息
select * from stock s 
	left join commodity_tag ct on ct.commodity_tag_id = s.commodity_tag_id
	left join commodity_company cp on cp.commodity_company_id = s.commodity_company_id 
	where ct.commodity_tag_name like concat('%','','%') or cp.commodity_company_name like concat('%','','%');
	
#模糊查询 用户信息
select * from customer 
	where customer_user like concat('%','hah','%') 
	or customer_name like concat('%','','%')
	or customer_tel like concat('%','','%')
	or customer_card like concat('%','','%');
	
	


