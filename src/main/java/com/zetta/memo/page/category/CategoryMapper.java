package com.zetta.memo.page.category;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<CategoryDTO> getCategories();
    boolean register(CategoryDTO categoryDTO);
    boolean update(CategoryDTO categoryDTO);
    boolean delete(int id);
}
