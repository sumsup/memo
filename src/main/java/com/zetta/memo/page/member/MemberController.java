package com.zetta.memo.page.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/join")
    public boolean join(@RequestBody MemberDTO memberDTO) {
        return memberService.joinMember(memberDTO);
    }

}
