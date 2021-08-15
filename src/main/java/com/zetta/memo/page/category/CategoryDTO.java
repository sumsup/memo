package com.zetta.memo.page.category;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CategoryDTO {
    private int id;
    private int memberId;
    private String category;
    private LocalDateTime createdAt;
}
