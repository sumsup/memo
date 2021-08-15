package com.zetta.memo.page.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MemberDTO {
    private int id;
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String status;
    private LocalDateTime joinAt;
    private LocalDateTime updateAt;
    private LocalDateTime exitAt;
}
