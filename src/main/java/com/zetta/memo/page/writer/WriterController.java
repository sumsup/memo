package com.zetta.memo.page.writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/writer")
public class WriterController {
    @Autowired
    private WriterService writerService;

    @GetMapping("/search")
    public List<WriterDTO> getWriters() {
        return writerService.getWriters();
    }

    @PostMapping("/register")
    public boolean register(@RequestBody WriterDTO writerDTO) {
        return writerService.registerWriter(writerDTO);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody WriterDTO writerDTO) {
        return writerService.update(writerDTO);
    }

    @DeleteMapping("/delete")
    public boolean delete(int id) {
        return writerService.delete(id);
    }

}
