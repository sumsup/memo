package com.zetta.memo.page.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {
    @Autowired
    private MemoMapper memoMapper;

    public List<MemoDTO> getMemos() {
        return memoMapper.selectMemo();
    }
}
