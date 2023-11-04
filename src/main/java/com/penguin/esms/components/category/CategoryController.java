package com.penguin.esms.components.category;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "category")
@Getter
@Setter
public class CategoryController {
    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
//    @ResponseBody
    public CategoryEntity get(@RequestBody ObjectNode categoryEntity) {
        return categoryService.getCategory(categoryEntity.get("name").asText());
    }

    @PostMapping
    public List<CategoryEntity> post(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.postCategory(categoryEntity);
    }
    @PutMapping
    public List<CategoryEntity> edit(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.editCategory(categoryEntity);
    }

    @DeleteMapping
    public CategoryEntity delete(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.deleteCategory(categoryEntity);
    }
}
