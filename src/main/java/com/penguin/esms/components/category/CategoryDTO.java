package com.penguin.esms.components.category;

import com.penguin.esms.dto.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO extends BaseDTO {
    private String name;
    private Integer productNum;
}
