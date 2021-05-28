package com.zetta.memo.page.memo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    MemoMapper memoMapper;

    @Test
    @Transactional
    void insertTest() {
        MemoDTO memo = new MemoDTO();
        memo.setWriterId(1);
        memo.setMemo("insert 테스트 입니다.");

        boolean insertResult = memoMapper.insertMemo(memo);

        assertTrue(insertResult);

        List<MemoDTO> memoList = memoMapper.selectMemo();

        assertEquals(memo.getMemo(), memoList.get(1).getMemo());
    }
}
