package com.penguin.esms.components.category;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @PutMapping(path = "{id}")
    public CategoryEntity edit(@RequestBody CategoryEntity categoryEntity, @PathVariable String id) {
        return categoryService.editCategory(categoryEntity, id);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable String id) {
        categoryService.deleteCategory(id);
    }
}
