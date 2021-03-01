package com.zetta.memo.page.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;


    public List<CategoryDTO> getCategories() {
        return categoryMapper.getCategories();
    }

    public boolean register(CategoryDTO categoryDTO) {
        categoryDTO.setCreatedAt(LocalDateTime.now());
        return categoryMapper.register(categoryDTO);
    }

    public boolean update(CategoryDTO categoryDTO) {
        return categoryMapper.update(categoryDTO);
    }

    public boolean delete(int id) {
        return categoryMapper.delete(id);
    }
}
