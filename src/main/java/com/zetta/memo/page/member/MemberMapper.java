package com.zetta.memo.page.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    boolean joinMember(MemberDTO memberDTO);
    MemberDTO getMember(MemberDTO memberDTO);
}
