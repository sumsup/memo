package com.zetta.memo.page.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memo")
public class MemoController {
    @Autowired
    private MemoService memoService;

    @GetMapping("/search")
    public List<MemoDTO> searchMemos() {
        return memoService.getMemos();
    }

    @PostMapping("/register")
    public boolean insertMemo(@RequestBody MemoDTO memoDTO) {
        return memoService.insertMemo(memoDTO);
    }

    @PutMapping("/update")
    public boolean updateMemo() {
        return true;
    }

    @DeleteMapping("/delete")
    public boolean deleteMemo() {
        return true;
    }

}