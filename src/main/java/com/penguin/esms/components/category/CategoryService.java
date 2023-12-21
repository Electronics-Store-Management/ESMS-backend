package com.penguin.esms.components.category;

import com.penguin.esms.entity.Error;
import com.penguin.esms.envers.AuditEnversInfo;
import com.penguin.esms.envers.AuditEnversInfoRepo;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class CategoryService {
    @PersistenceContext
    private final EntityManager entityManager;
    private final AuditEnversInfoRepo auditEnversInfoRepo;
    private final CategoryRepo categoryRepo;
    private final DTOtoEntityMapper mapper;
    public CategoryEntity postCategory(CategoryEntity categoryEntity) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepo.findByName(categoryEntity.getName());
        if (categoryEntityOptional.isPresent()) {
            if (categoryEntityOptional.get().getIsStopped() == true)
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, new Error("Category has been discontinued ").toString());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, new Error("Category existed").toString());
        }
        categoryEntity.setIsStopped(false);
        return categoryRepo.save(categoryEntity);
    }

    public CategoryEntity editCategory(CategoryDTO categoryDTO, String id) {
        Optional<CategoryEntity> categoryEntityOptional = categoryRepo.findById(id);
        if (categoryEntityOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Category not existed");
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
    @Transactional
    public List<?> getRevisionsForCategory(String id) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(CategoryEntity.class, true, true)
                .add(AuditEntity.id().eq(id))
                .addProjection(AuditEntity.property("id"))
                .addProjection(AuditEntity.property("name"))
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> categoryAudit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for(int i=0; i< objects.size();i++){
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[2]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                CategoryEntity category = new CategoryEntity(id, (String) objArray[1]);
                auditEnversInfo.setRevision(category);
                categoryAudit.add(auditEnversInfo);
            }
        }
        entityManager.close();
        return categoryAudit;
    }
}
