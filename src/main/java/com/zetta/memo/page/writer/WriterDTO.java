package com.zetta.memo.page.writer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class WriterDTO {
    private int id;
    private String password;
    private String email;
    private String nickname;
    private String sex;
    private LocalDateTime joinAt;
    private String birthday;
}
