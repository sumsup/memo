package com.zetta.memo.page.memo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO : 리얼 환경에서 테스트 스키마 h2-console 띄우기.

@SpringBootTest
public class MemoServiceTest {
    @Autowired
    private MemoMapper memoMapper;
    @Autowired
    private MemoService memoService;

    private MemoDTO.Search search;


    private static final Logger log = LoggerFactory.getLogger(MemoServiceTest.class);

    @BeforeEach
    void setUp() {
        search = new MemoDTO.Search();
        search.setMemo("테스트");
    }

    @Test
    public void searchMemoTest() {
        MemoDTO.Search search = new MemoDTO.Search();
        List<MemoDTO> memoList = memoMapper.selectMemo(search);

        assertNotNull(memoList);

        log.info("memoList : " + memoList);
    }

    @Test
    @Transactional
    public void deleteMemoTest() {
        MemoDTO.Search search = new MemoDTO.Search();
        List<MemoDTO> memoList = memoMapper.selectMemo(search);

        assertFalse(memoList.isEmpty());
        boolean isSuccess = memoMapper.deleteMemo(memoList.get(0).getId());

        assertTrue(isSuccess);
    }

    @Test
    @DisplayName("search memo with contain 'memo' condition")
    void searchMemoContainMemoConditions() {
        List<MemoDTO> memoList = memoService.getMemos(search);

        memoList.stream().forEach(memo -> {
            assertThat(memo.getMemo()).contains("테스트");
        });

    }

}