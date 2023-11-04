package com.penguin.esms.components.category.request;

import com.penguin.esms.components.permission.EntityType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class CategoryRequest {
    private String name;
}
