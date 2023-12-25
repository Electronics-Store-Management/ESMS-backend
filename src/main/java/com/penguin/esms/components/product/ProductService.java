package com.penguin.esms.components.product;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.category.CategoryRepo;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.components.supplier.SupplierRepo;
import com.penguin.esms.entity.Error;
import com.penguin.esms.envers.AuditEnversInfo;
import com.penguin.esms.envers.AuditEnversInfoRepo;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import com.penguin.esms.services.AmazonS3Service;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final SupplierRepo supplierRepo;
    private final DTOtoEntityMapper mapper;
    private final AmazonS3Service amazonS3Service;
    @PersistenceContext
    private final EntityManager entityManager;
    private final AuditEnversInfoRepo auditEnversInfoRepo;

    public List<ProductEntity> findRelatedCategory(String name, String categoryName) {
        if (categoryName != null && !categoryName.isEmpty()) {
            Optional<CategoryEntity> optionalCategory = categoryRepo.findByName(categoryName);
            if (optionalCategory.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("category not found").toString());
            }
            if (optionalCategory.get().getIsStopped() == true)
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Category has been discontinued ");
            return productRepo.findByNameContainingIgnoreCaseAndCategoryAndIsStopped(name, optionalCategory.get(), false);
        }
        Optional<ProductEntity> productEntityOptional = productRepo.findByName(name);
        if (productEntityOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("product not found").toString());
        }
        return productRepo.findByNameContainingIgnoreCaseAndIsStopped(name, false);

    }
    public List<ProductEntity> findDiscontinuedRelatedCategory(String name, String categoryName) {
        if (categoryName != null && !categoryName.isEmpty()) {
            Optional<CategoryEntity> optionalCategory = categoryRepo.findByName(categoryName);
            if (optionalCategory.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, new Error("category not found").toString());
            }
            if (optionalCategory.get().getIsStopped() == true)
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Category has been discontinued ");
            return productRepo.findByNameContainingIgnoreCaseAndCategoryAndIsStopped(name, optionalCategory.get(), true);
        }
        return productRepo.findByNameContainingIgnoreCaseAndIsStopped(name, true);
    }

    public ProductEntity getProductById(String productId) {
        Optional<ProductEntity> product = productRepo.findById(productId);
        if (product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Product not found").toString());
        }
        if (product.get().getIsStopped() == true)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Product has been discontinued ");
        return product.get();
    }

    public ProductEntity add(ProductDTO productDTO) {
        Optional<ProductEntity> productOpt = productRepo.findByName(productDTO.getName());
        if (productOpt.isPresent()) {
            if (productOpt.get().getIsStopped() == true)
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, new Error("Product has been discontinued ").toString());
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, new Error("Product existed").toString());
        }
        ProductEntity product = updateFromDTO(productDTO, new ProductEntity());
        product.setIsStopped(false);
        return productRepo.save(product);
    }

    public ProductEntity update(ProductDTO productDTO, String id, MultipartFile photo) throws IOException {
        Optional<ProductEntity> productEntityOptional = productRepo.findById(id);
        if (productEntityOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not existed");
        if (productEntityOptional.get().getIsStopped() == true)
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Product has been discontinued ");
        ProductEntity product = updateFromDTO(productDTO, productEntityOptional.get());
        if (photo != null) {
            List<String> parsedURL = Arrays.stream(product.getPhotoURL().split("/")).toList();
            amazonS3Service.deleteFile(parsedURL.get(parsedURL.size() - 1));
            String objectURL = amazonS3Service.updateFile(photo, product.getName() + "_" + photo.getOriginalFilename());
            product.setPhotoURL(objectURL);
        }
        return productRepo.save(product);
    }

    public void remove(String id) {
        Optional<ProductEntity> productEntityOptional = productRepo.findById(id);
        if (productEntityOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Product not existed").toString());
        if (productEntityOptional.get().getIsStopped().equals(true))
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, new Error("Product has been discontinued").toString());
        productEntityOptional.get().setIsStopped(true);
        productRepo.save(productEntityOptional.get());
    }

    private ProductEntity updateFromDTO(ProductDTO productDTO, ProductEntity product) {
        mapper.updateProductFromDto(productDTO, product);
        if (productDTO.getCategoryId() != null) {
            if (productDTO.getCategoryId().isEmpty()) {
                product.setCategory(null);
            } else {
                Optional<CategoryEntity> category = categoryRepo.findById(productDTO.getCategoryId());
                if (category.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, new Error("Category not found").toString());
                }
                product.setCategory(category.get());
                if (category.get().getIsStopped() == true)
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Category has been discontinued ");
                product.setCategory(category.get());
            }
        }
        List<SupplierEntity> supplierEntities = new ArrayList<>();
        productDTO.getSuppliers().forEach(s -> {
            Optional<SupplierEntity> supplier = supplierRepo.findById(s);
            if (supplier.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Supplier with ID: " + s + " not found.").toString());
            }
            if (supplier.get().getIsStopped())
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Supplier has been discontinued ");
            supplierEntities.add(supplier.get());
        });
        product.setSuppliers(supplierEntities);
        return product;
    }
    @Transactional
    public List<?> getRevisionsForProduct(String id) {
        Optional<ProductEntity> productEntityOptional = productRepo.findById(id);
        if (productEntityOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not existed");
        AuditReader auditReader = AuditReaderFactory.get(entityManager);

        AuditQuery query = auditReader.createQuery()
                .forRevisionsOfEntity(ProductEntity.class, true, true)
                .add(AuditEntity.id().eq(id))
                .addProjection(AuditEntity.property("id"))
                .addProjection(AuditEntity.property("name"))
                .addProjection(AuditEntity.property("category_id"))
                .addProjection(AuditEntity.property("unit"))
                .addProjection(AuditEntity.property("price"))
                .addProjection(AuditEntity.property("quantity"))
                .addProjection(AuditEntity.property("warrantyPeriod"))
                .addProjection(AuditEntity.property("isAvailable"))
                .addProjection(AuditEntity.property("photoURL"))
                .addProjection(AuditEntity.revisionNumber())
                .addProjection(AuditEntity.revisionType())
                .addOrder(AuditEntity.revisionNumber().desc());

        List<AuditEnversInfo> productAudit = new ArrayList<AuditEnversInfo>();
        List<Object[]> objects = query.getResultList();
        for(int i=0; i< objects.size();i++){
            Object[] objArray = objects.get(i);
            Optional<AuditEnversInfo> auditEnversInfoOptional = auditEnversInfoRepo.findById((int) objArray[9]);
            if (auditEnversInfoOptional.isPresent()) {
                AuditEnversInfo auditEnversInfo = auditEnversInfoOptional.get();
                ProductDTO product = new ProductDTO(id, (String) objArray[1],  (String) objArray[2], (String) objArray[3], (Long) objArray[4], (Integer) objArray[5], (Integer) objArray[6] , (Boolean) objArray[7], (String) objArray[8]);
                auditEnversInfo.setRevision(product);
                productAudit.add(auditEnversInfo);
            }
        }
        entityManager.close();
        return productAudit;
    }
}
