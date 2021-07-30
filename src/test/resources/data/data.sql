/* 작성자 등록 */
insert into writer (email, password, nickname, sex, join_at) values ('test@email.com', 'pass123', '점진적재미', '남', now());

/* 메모 등록 */
INSERT INTO `memo` (`writer_id`, `memo`, `category`, `created_at`, `updated_at`)
VALUES (1, '테스트로 메모 인서트 해봅니다.', null, now(), now());