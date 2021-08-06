package com.zetta.memo.page.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemoService {
    @Autowired
    private MemoMapper memoMapper;

    public List<MemoDTO> getMemos(MemoDTO.Search search) {
        return memoMapper.selectMemo(search);
    }

    public void insertMemo(MemoDTO memoDTO) {
        memoMapper.insertMemo(memoDTO);
    }

    public boolean updateMemo(MemoDTO memoDTO) {
        memoDTO.setUpdatedAt(LocalDateTime.now());
        return memoMapper.updateMemo(memoDTO);
    }

    public boolean deleteMemo(long id) {
        return memoMapper.deleteMemo(id);
    }
}
