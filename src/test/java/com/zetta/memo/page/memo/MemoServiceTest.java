package com.zetta.memo.page.memo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MemoServiceTest {
    @Autowired
    private MemoMapper memoMapper;

    @Test
    public void searchMemoTest() {
        List<MemoDTO> memoList = memoMapper.selectMemo();

        log.info("memoList : " + memoList.toString());
    }

    @Test
    @Transactional
    public void deleteMemoTest() {
        boolean isSuccess = memoMapper.deleteMemo(51);

        log.info("delete success ? => " + isSuccess);
    }

}