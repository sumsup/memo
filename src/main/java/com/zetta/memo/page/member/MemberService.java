package com.zetta.memo.page.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    public List<MemberDTO> getWriters() {
        return memberMapper.getWriters();
    }

    public boolean registerWriter(MemberDTO memberDTO) {
        memberDTO.setJoinAt(LocalDateTime.now());
        return memberMapper.registerWriter(memberDTO);
    }

    public boolean update(MemberDTO memberDTO) {
        return memberMapper.update(memberDTO);
    }

    public boolean delete(int id) {
        return memberMapper.delete(id);
    }
}
