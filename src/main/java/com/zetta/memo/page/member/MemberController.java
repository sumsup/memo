package com.zetta.memo.page.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/writer")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public boolean register(@RequestBody MemberDTO memberDTO) {
        return memberService.joinMember(memberDTO);
    }

}
