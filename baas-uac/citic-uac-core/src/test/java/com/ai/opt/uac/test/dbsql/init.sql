delete from sys_user;
insert into sys_user(id,company_id,office_id,login_name,password,name,email,mobile,login_flag,del_flag)
values('1','company001','officeid001','user001','cdfedbff4ace28a5abc1637e4d78c15c838ee97ad29217cef89c2e7f','name001','user001@citic.com','13100000000','1','0');

insert into sys_user(id,company_id,office_id,login_name,password,name,email,mobile,login_flag,del_flag)
values('2','company002','officeid002','user002','cdfedbff4ace28a5abc1637e4d78c15c838ee97ad29217cef89c2e7f','name002','user002@citic.com','13200000000','1','0');

insert into sys_user(id,company_id,office_id,login_name,password,name,email,mobile,login_flag,del_flag)
values('3','company003','officeid003','user003','cdfedbff4ace28a5abc1637e4d78c15c838ee97ad29217cef89c2e7f','name003','user003@citic.com','13300000000','1','0');

commit;