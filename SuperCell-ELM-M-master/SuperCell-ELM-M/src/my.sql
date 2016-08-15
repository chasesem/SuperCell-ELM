select * from Merchant

insert into merchant(id,address,idcardpicpath,licensepicpath,numberoforders,password,phonenumber,rating,shopname,shoppicpath)
values(1,'test_addr','id_path','lic_path',3,'12345','12345','5','shop_name','shop_path')

select * from dishes
select * from OrderItem

select * from CustomerOrder

update ORDERITEM set DISHESID=116 where orderid=110
update CUSTOMERORDER set merchantId=150 where id=112
update CUSTOMERORDER set state=0 where id=110

delete from MERCHANT where id=149

select * from complaint

