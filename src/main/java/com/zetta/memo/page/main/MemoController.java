package com.zetta.memo.page.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemoController {
    @Autowired
    private MemoService memoService;

    @GetMapping("/memo")
    public List<MemoDTO> getHello(String name) {
        return memoService.getMemos();
    }
}
