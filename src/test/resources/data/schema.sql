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
