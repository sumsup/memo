package com.zetta.memo.page.memo;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// TODO : 리얼 환경에서 테스트 스키마 h2-console 띄우기.

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class MemoServiceTest {
    @Autowired
    private MemoMapper memoMapper;

    private static final Logger log = LoggerFactory.getLogger(MemoServiceTest.class);

    @Test
    public void searchMemoTest() {

        List<MemoDTO> memoList = memoMapper.selectMemo();

        assertNotNull(memoList);

        log.info("memoList : " + memoList.toString());
    }

    @Test
    @Transactional
    public void deleteMemoTest() {
        List<MemoDTO> memoList = memoMapper.selectMemo();

        assertFalse(memoList.isEmpty());
        boolean isSuccess = memoMapper.deleteMemo(memoList.get(0).getId());

        log.info("delete success ? => " + isSuccess);
    }

}