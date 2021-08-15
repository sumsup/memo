alter table memo drop foreign key memo_writer_id_fk;
alter table category drop foreign key writer_id_fk;

drop table writer;

create table member (
	id int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
    email varchar(30) NOT NULL,
    password varchar(20) NOT NULL,
    nickname varchar(20) NOT NULL,
    phone_number varchar(15) NOT NULL DEFAULT '0',
    status varchar(20) NOT NULL DEFAULT 'ACTIVE',
    join_at datetime default now(),
    exit_at datetime default '9999-12-31 23:59:59',
    primary key(id)
);

alter table category change member_id member_id int(11) unsigned not null;
alter table memo change writer_id member_id int(11) unsigned;

truncate category;

insert into member (email, password, nickname, phone_number, status, join_at)
values ('starirof@naver.com', 'dpqk13', '구름빵', '01095962356', 'active' , now());

alter table category add constraint category_member_id_fk foreign key (member_id) references member(id);
alter table memo add constraint memo_member_id_fk foreign key (member_id) references member(id);

alter table member add update_at datetime default '9999-12-31 23:59:59' not null after join_at;
alter table member modify join_at datetime default now() not null;
alter table member modify exit_at datetime default '9999-12-31 23:59:59' not null;