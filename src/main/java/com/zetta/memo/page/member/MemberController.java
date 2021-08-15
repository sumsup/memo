package com.zetta.memo.page.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/writer")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/search")
    public List<MemberDTO> getWriters() {
        return memberService.getWriters();
    }

    @PostMapping("/register")
    public boolean register(@RequestBody MemberDTO memberDTO) {
        return memberService.registerWriter(memberDTO);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody MemberDTO memberDTO) {
        return memberService.update(memberDTO);
    }

    @DeleteMapping("/delete")
    public boolean delete(int id) {
        return memberService.delete(id);
    }

}
