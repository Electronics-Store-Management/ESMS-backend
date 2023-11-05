package com.penguin.esms.mapper;

import com.penguin.esms.components.category.CategoryDTO;
import com.penguin.esms.components.category.CategoryEntity;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface DTOtoEntityMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromDto(CategoryDTO dto, @MappingTarget CategoryEntity entity);
}
