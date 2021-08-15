package com.zetta.memo.page.member;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class MemberServiceTest {
    @Autowired
    private MemberMapper memberMapper;

    @Test
    @Transactional
    @DisplayName("join member test")
    public void joinMemberTest() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setEmail("tester_join@naver.com");
        memberDTO.setPassword("1234");
        memberDTO.setNickname("하루살이");
        memberDTO.setPhoneNumber("01012341233");

        memberMapper.joinMember(memberDTO);

        MemberDTO searchMember = memberMapper.getMember(memberDTO);
        log.info("**** join member test result : {}", searchMember.toString());

        assertNotNull(searchMember);

    }

}
