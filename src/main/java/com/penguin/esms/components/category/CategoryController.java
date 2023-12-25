package com.penguin.esms.components.category;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.penguin.esms.components.category.response.FoundCategoryItem;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "category")
@Getter
@Setter
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryEntity> getALl(@RequestParam(defaultValue = "") String name) {
        return categoryService.getCategory(name);
    }
    @GetMapping("discontinued")
    public List<CategoryEntity> getALlDiscontinued(@RequestParam(defaultValue = "") String name) {
        return categoryService.getDiscontinuedCategory(name);
    }

    @GetMapping("history/{id}")
    public List<?> getHistory(@PathVariable String id) {
        return categoryService.getRevisionsForCategory(id);
    }

    @GetMapping("{id}")
    public CategoryEntity getItem(@PathVariable String id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> post(CategoryEntity categoryEntity) {
        return ResponseEntity.ok(categoryService.postCategory(categoryEntity));
    }
    @PutMapping(path = "{id}")
    public CategoryEntity edit(CategoryDTO categoryDTO, @PathVariable String id) {
        return categoryService.editCategory(categoryDTO, id);
    }

    @DeleteMapping(path = "{id}")
    public void delete(@PathVariable String id) {
        categoryService.deleteCategory(id);
    }

    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<?> handleException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ResponseEntity<?> handleNotReadableException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body not found");
    }
}
