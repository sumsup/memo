package com.zetta.memo.page.memo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoMapper {
    List<MemoDTO> selectMemo(MemoDTO.Search search);
    void insertMemo(MemoDTO memoDTO);
    void insertMemoBatch(List<MemoDTO> memberList);
    boolean updateMemo(MemoDTO memoDTO);
    boolean deleteMemo(long id);
}
