/* 작성자 등록 */
insert into writer (email, password, nickname, sex, join_at) values ('test@email.com', 'pass123', '점진적재미', '남', now());

/* 메모 등록 */
insert into memo (writer_id, memo) values (1, '메모했습니다.');
