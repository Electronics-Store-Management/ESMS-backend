package com.penguin.esms.mapper;

import com.penguin.esms.components.category.CategoryDTO;
import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.product.ProductDTO;
import com.penguin.esms.components.product.ProductEntity;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface DTOtoEntityMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromDto(CategoryDTO dto, @MappingTarget CategoryEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductDTO dto, @MappingTarget ProductEntity entity);
}
