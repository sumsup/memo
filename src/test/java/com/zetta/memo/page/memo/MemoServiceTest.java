package com.zetta.memo.page.memo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemoServiceTest {
    @Autowired
    private MemoMapper memoMapper;

    @Test
    public void searchMemoTest() {
        List<MemoDTO> memoList = memoMapper.selectMemo();

        log.info("memoList : " + memoList.toString());
    }

    @Test
    public void deleteMemoTest() {
        boolean isSuccess = memoMapper.deleteMemo(51);

        log.info("delete success ? => " + isSuccess);
    }

}