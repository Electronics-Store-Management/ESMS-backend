package com.penguin.esms.components.category;

import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "category")
@Getter
@Setter
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryEntity> get(@RequestParam(defaultValue = "") String name) {
        return categoryService.getCategory(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryEntity post(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.postCategory(categoryEntity);
    }
    @PutMapping(path = "{id}")
    public CategoryEntity edit(@RequestBody CategoryDTO categoryDTO, @PathVariable String id) {
        return categoryService.editCategory(categoryDTO, id);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable String id) {
        categoryService.deleteCategory(id);
    }
}