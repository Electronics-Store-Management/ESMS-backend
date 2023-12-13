package com.penguin.esms.components.category;

import com.penguin.esms.components.category.response.FoundCategoryItem;
import com.penguin.esms.components.permission.PermissionEntity;
import com.penguin.esms.components.staff.StaffEntity;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final DTOtoEntityMapper mapper;

    public CategoryEntity postCategory(CategoryEntity categoryEntity) {
        if (categoryRepo.findByName(categoryEntity.getName()).isPresent())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Category existed");
        categoryEntity.setIsStopped(false);
        return categoryRepo.save(categoryEntity);
    }

    public CategoryEntity editCategory(CategoryDTO categoryDTO, String id) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepo.findById(id);
        if (categoryEntityOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Category not existed");
        if (categoryEntityOptional.get().getIsStopped()==true)
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Category has been discontinued ");
        CategoryEntity category = categoryEntityOptional.get();
        mapper.updateCategoryFromDto(categoryDTO, category);
        return categoryRepo.save(category);

    }
    public void deleteCategory(String id) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepo.findById(id);
        if (categoryEntityOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Category not existed");
        categoryEntityOptional.get().setIsStopped(true);
        categoryRepo.save(categoryEntityOptional.get());
    }
    public List<CategoryEntity> getCategory(String name) {
        return categoryRepo.findByNameContainingIgnoreCaseAndIsStopped(name, false);
    }
    public List<CategoryEntity> getDiscontinuedCategory(String name) {
        return categoryRepo.findByNameContainingIgnoreCaseAndIsStopped(name, true);
    }
    public CategoryEntity getCategoryById(String id) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepo.findById(id);
        if (categoryEntityOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Category not existed");
        else {
            return categoryEntityOptional.get();
        }
    }
}
