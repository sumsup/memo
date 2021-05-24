package com.zetta.memo.page.memo;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MemoDTO {
    private long id;
    private int writerId;
    private String memo;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

