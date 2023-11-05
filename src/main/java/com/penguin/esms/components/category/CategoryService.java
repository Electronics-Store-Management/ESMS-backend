package com.penguin.esms.components.category;

import com.penguin.esms.components.permission.PermissionEntity;
import com.penguin.esms.components.staff.StaffEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CategoryService {
    private final CategoryRepo categoryRepo;
    @Autowired
    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<CategoryEntity> postCategory(CategoryEntity categoryEntity) {
        if (categoryRepo.findByName(categoryEntity.getName()).isPresent())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Category existed");
        List<CategoryEntity> categoryEntities = new ArrayList<CategoryEntity>();
        categoryEntities.add(categoryEntity);
        return categoryRepo.saveAll(categoryEntities);
    }

    public CategoryEntity editCategory(CategoryEntity categoryEntity, String id) {
        if (categoryRepo.findById(id).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Category not existed");
        categoryRepo.deleteById(categoryEntity.getId());
        return categoryRepo.save(categoryEntity);
    }
    public void deleteCategory(String id) {
        if (categoryRepo.findById(id).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Category not existed");
        categoryRepo.deleteById(id);
    }
    public CategoryEntity getCategory(String name) {
        if (categoryRepo.findByName(name).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Category not found");

        return categoryRepo.findByName(name).get();
    }
}
