package com.zetta.memo.page.writer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class WriterServiceTest {
    @Autowired
    private WriterMapper writerMapper;

    @Test
    public void getWritersTest() {
        log.info("search writers : " + writerMapper.getWriters().toString());
    }
}
