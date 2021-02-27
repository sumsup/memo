package com.zetta.memo.page.main;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class MemoDTO {
    private int writerId;
    private String memo;
    private String category;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}

