/* 작성자 등록 */
insert into member (email, password, nickname, phone_number, status, join_at)
values ('test@email.com', 'pass123', '점진적재미', '01012341222', 'active', now());

/* 메모 등록 */
INSERT INTO `memo` (`member_id`, `memo`, `category`, `created_at`, `updated_at`)
VALUES (1, '테스트로 메모 인서트 해봅니다.', null, now(), now());