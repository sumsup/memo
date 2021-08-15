package com.zetta.memo.page.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    List<MemberDTO> getWriters();
    boolean registerWriter(MemberDTO memberDTO);
    boolean update(MemberDTO memberDTO);
    boolean delete(int id);
}
