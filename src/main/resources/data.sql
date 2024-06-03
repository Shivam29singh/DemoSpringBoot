insert into user_details(id,birth_date,name)
values(10001,current_date(),'Ranga');

insert into user_details(id,birth_date,name)
values(10002,current_date(),'Ravi');

insert into user_details(id,birth_date,name)
values(10003,current_date(),'Shivam');

--insert into post(id,description,,user_id)
--values(20001,'i want to learn AWS',10001);
--
--insert into post(id,description,user_id)
--values(20002,'i want to learn Python',10002);
--
--insert into post(id,description,user_id)
--values(20003,'i want to learn Java',10003);
--
--insert into post(id,description,user_id)
--values(20004,'i want to learn Html',10004);

insert into post(id,user_id,description)
values(20001,10001,'i want to learn AWS');

insert into post(id,description,user_id)
values(20002,'i want to learn Python',10002);

insert into post(id,description,user_id)
values(20003,'i want to learn Java',10003);

insert into post(id,description,user_id)
values(20004,'i want to learn Html',10003);