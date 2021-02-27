package com.zetta.memo.page.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemoService {
    @Autowired
    private MemoMapper memoMapper;

    public List<MemoDTO> getMemos() {
        return memoMapper.selectMemo();
    }

    public boolean insertMemo(MemoDTO memoDTO) {
        memoDTO.setUpdateAt(LocalDateTime.now());
        return memoMapper.insertMemo(memoDTO);
    }
}
