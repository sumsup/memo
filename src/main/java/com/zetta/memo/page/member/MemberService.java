package com.zetta.memo.page.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    public boolean joinMember(MemberDTO memberDTO) {
        return memberMapper.joinMember(memberDTO);
    }

}
