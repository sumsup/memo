package com.zetta.memo.page.writer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WriterService {
    @Autowired
    private WriterMapper writerMapper;

    public List<WriterDTO> getWriters() {
        return writerMapper.getWriters();
    }

    public boolean registerWriter(WriterDTO writerDTO) {
        writerDTO.setJoinAt(LocalDateTime.now());
        return writerMapper.registerWriter(writerDTO);
    }

    public boolean update(WriterDTO writerDTO) {
        return writerMapper.update(writerDTO);
    }

    public boolean delete(int id) {
        return writerMapper.delete(id);
    }
}
