package com.penguin.esms.components.category;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "category")
@Getter
@Setter
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
}
