package com.penguin.esms.components.product;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.category.CategoryRepo;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.components.supplier.SupplierRepo;
import com.penguin.esms.entity.Error;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import com.penguin.esms.services.AmazonS3Service;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
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

    public List<ProductEntity> findByName(String name) {
        return productRepo.findByNameContainingIgnoreCase(name);
    }

    public ProductEntity getProduct(String productId) {
        Optional<ProductEntity> product = productRepo.findById(productId);
        if (product.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return product.get();
    }

    public ProductEntity add(ProductDTO productDTO) {
        if (productRepo.findByName(productDTO.getName()).isPresent())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, new Error("Product existed").toString());
        ProductEntity product = updateFromDTO(productDTO, new ProductEntity());
        return productRepo.save(product);
    }

    public ProductEntity update(ProductDTO productDTO, String id, MultipartFile photo) throws IOException {
        if (productRepo.findById(id).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not existed");
        ProductEntity product = updateFromDTO(productDTO, productRepo.findById(id).get());
        if (photo != null) {
            List<String> parsedURL = Arrays.stream(product.getPhotoURL().split("/")).toList();
            amazonS3Service.deleteFile(parsedURL.get(parsedURL.size() - 1));
            String objectURL = amazonS3Service.updateFile(photo, product.getName() + "_" + photo.getOriginalFilename());
            product.setPhotoURL(objectURL);
        }
        return productRepo.save(product);
    }

    public ProductEntity remove(String id) {
        Optional<ProductEntity> productEntityOptional = productRepo.findById(id);
        if (productEntityOptional.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not existed");
        productRepo.deleteById(id);
        return productEntityOptional.get();
    }

    private ProductEntity updateFromDTO(ProductDTO productDTO, ProductEntity product) {
        mapper.updateProductFromDto(productDTO, product);
        if (productDTO.getCategoryId() != null) {
            Optional<CategoryEntity> category = categoryRepo.findById(productDTO.getCategoryId());
            if (category.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found");
            }
            product.setCategory(category.get());
        }
        List<SupplierEntity> supplierEntities = new ArrayList<>();
        productDTO.getSuppliers().forEach(s -> {
            Optional<SupplierEntity> supplier = supplierRepo.findById(s);
            if (supplier.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, new Error("Supplier with ID: " + s + " not found.").toString());
            }
            supplierEntities.add(supplier.get());
        });
        product.setSuppliers(supplierEntities);
        return product;
    }
}
