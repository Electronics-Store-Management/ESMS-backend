package com.penguin.esms.mapper;

import com.penguin.esms.components.category.CategoryDTO;
import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.customer.CustomerEntity;
import com.penguin.esms.components.customer.dto.CustomerDTO;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.staff.StaffDTO;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.components.supplier.dto.SupplierDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DTOtoEntityMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCategoryFromDto(CategoryDTO dto, @MappingTarget CategoryEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductDTO dto, @MappingTarget ProductEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateStaffFromDto(StaffDTO dto, @MappingTarget StaffEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSupplierFromDto(SupplierDTO dto, @MappingTarget SupplierEntity entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateCustomerFromDto(CustomerDTO dto, @MappingTarget CustomerEntity entity);
    default List<SupplierEntity> map(List<String> value) {
        return value.stream().map(v -> new SupplierEntity()).toList();
    }
    default List<CustomerEntity> mapp(List<String> value) {
        return value.stream().map(v -> new CustomerEntity()).toList();
    }
}
