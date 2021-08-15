CREATE TABLE `writer` (
            `id` int(11) NOT NULL AUTO_INCREMENT,
            `email` varchar(45) NOT NULL,
            `password` varchar(20) NOT NULL,
            `nickname` varchar(45) NOT NULL,
            `sex` varchar(6) NOT NULL COMMENT '성별',
            `join_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일',
            `birthday` varchar(10) DEFAULT NULL COMMENT '생년월일.',
            PRIMARY KEY (`id`),
            UNIQUE KEY `email_UNIQUE` (`email`),
            UNIQUE KEY `nickname_UNIQUE` (`nickname`)
);

CREATE TABLE `memo` (
            `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '아이디',
            `writer_id` int(11) NOT NULL COMMENT '메모한 사람',
            `memo` text NOT NULL COMMENT '메모 내용',
            `category` varchar(20) DEFAULT NULL COMMENT '카테고리',
            `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일',
            `updated_at` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정일',
            PRIMARY KEY (`id`),
            CONSTRAINT `memo_writer_id_fk` FOREIGN KEY (`writer_id`) REFERENCES `writer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `category` (
            `id` int(11) NOT NULL AUTO_INCREMENT,
            `writer_id` int(11) NOT NULL,
            `category` varchar(20) DEFAULT NULL,
            `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
            PRIMARY KEY (`id`),
            CONSTRAINT `writer_id_fk` FOREIGN KEY (`writer_id`) REFERENCES `writer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

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

alter table category change writer_id member_id int(11) unsigned not null;
alter table memo change writer_id member_id int(11) unsigned;

-- truncate category;
-- H2 DB 에서 truncate 명령어 작동 안함.

alter table category add constraint category_member_id_fk foreign key (member_id) references member(id);
alter table memo add constraint memo_member_id_fk foreign key (member_id) references member(id);

alter table member add update_at datetime default '9999-12-31 23:59:59' not null after join_at;
alter table member modify join_at datetime default now() not null;
alter table member modify exit_at datetime default '9999-12-31 23:59:59' not null;