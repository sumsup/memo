package com.zetta.memo.page.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memo")
public class MemoController {
    @Autowired
    private MemoService memoService;

    @GetMapping("/search")
    @ResponseBody
    public List<MemoDTO> searchMemos() {
        return memoService.getMemos();
    }

    @PostMapping("/register")
    public boolean insertMemo(@RequestBody MemoDTO memoDTO) {
        return memoService.insertMemo(memoDTO);
    }

    @PutMapping("/update/{id}")
    public boolean putUpdateMemo(@RequestBody MemoDTO memoDTO, @PathVariable long id) {
        memoDTO.setId(id);
        return memoService.updateMemo(memoDTO);
    }

    @PatchMapping("/update/{id}")
    public boolean patchUpdateMemo(@RequestBody MemoDTO memoDTO, @PathVariable long id) {
        memoDTO.setId(id);
        return memoService.updateMemo(memoDTO);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteMemo(@PathVariable long id) {
        return memoService.deleteMemo(id);
    }

}