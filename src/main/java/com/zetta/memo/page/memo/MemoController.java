package com.zetta.memo.page.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memo")
public class MemoController {
    @Autowired
    private MemoService memoService;

    @PostMapping("/search")
    @ResponseBody
    public List<MemoDTO> searchMemos(@RequestBody MemoDTO.Search search) {
        return memoService.getMemos(search);
    }

    @PostMapping("/register")
    public long insertMemo(@RequestBody MemoDTO memoDTO) {
        memoService.insertMemo(memoDTO);
        return memoDTO.getId();
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