package com.zetta.memo.page.member;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceTest {
    @Autowired
    private MemberMapper memberMapper;

    @Test
    public void getWritersTest() {
        log.info("search writers : " + memberMapper.getWriters().toString());
    }
}
