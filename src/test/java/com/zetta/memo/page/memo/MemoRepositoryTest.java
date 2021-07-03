package com.zetta.memo.page.memo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    MemoMapper memoMapper;

    @Test
    @Transactional
    @DisplayName("insert memo test")
    void insertTest() {
        MemoDTO memo = new MemoDTO();
        memo.setWriterId(1);
        memo.setMemo("insert 테스트 입니다.");

        boolean insertResult = memoMapper.insertMemo(memo);

        assertTrue(insertResult);

        MemoDTO.Search search = new MemoDTO.Search();
        List<MemoDTO> memoList = memoMapper.selectMemo(search);
        assertEquals(memo.getMemo(), memoList.get(1).getMemo());
    }

    @Test
    @DisplayName("search condition is activate")
    @Transactional
    void searchConditionTest() {
        MemoDTO memo = new MemoDTO();
        memo.setWriterId(1);
        memo.setMemo("insert 테스트 입니다.");

        boolean insertResult = memoMapper.insertMemo(memo);

        assertTrue(insertResult);

        MemoDTO.Search search = new MemoDTO.Search();
        search.setMemo("테스트");

        List<MemoDTO> list = memoMapper.selectMemo(search);

        list.stream().forEach(dto -> {
            assertThat(dto.getMemo()).contains("테스트");
        });

    }


}
