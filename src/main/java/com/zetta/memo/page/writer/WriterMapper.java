package com.zetta.memo.page.writer;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WriterMapper {
    List<WriterDTO> getWriters();
    boolean registerWriter(WriterDTO writerDTO);
    boolean update(WriterDTO writerDTO);
    boolean delete(int id);
}
