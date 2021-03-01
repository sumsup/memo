package com.zetta.memo.page.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/search")
    public List<CategoryDTO> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping("/register")
    public boolean register(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.register(categoryDTO);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(categoryDTO);
    }

    @DeleteMapping("/delete")
    public boolean delete(int id) {
        return categoryService.delete(id);
    }
}
