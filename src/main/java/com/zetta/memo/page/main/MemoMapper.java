package com.zetta.memo.page.main;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemoMapper {
    List<MemoDTO> selectMemo();
}
