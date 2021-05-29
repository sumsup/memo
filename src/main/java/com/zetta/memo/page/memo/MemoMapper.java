package com.zetta.memo.page.memo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoMapper {
    List<MemoDTO> selectMemo(MemoDTO.Search search);
    boolean insertMemo(MemoDTO memoDTO);
    boolean updateMemo(MemoDTO memoDTO);
    boolean deleteMemo(long id);
}
