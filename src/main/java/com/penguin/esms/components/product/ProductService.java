package com.penguin.esms.components.product;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.category.CategoryRepo;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final DTOtoEntityMapper mapper;

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
                    HttpStatus.BAD_REQUEST, "Product existed");
        ProductEntity product = new ProductEntity();
        mapper.updateProductFromDto(productDTO, product);
        if (productDTO.getCategoryId() != null) {
            Optional<CategoryEntity> category = categoryRepo.findById(productDTO.getCategoryId());
            if (category.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found");
            }
            product.setCategory(category.get());
        }
        return productRepo.save(product);
    }

    public ProductEntity update(ProductDTO productDTO, String id) {
        if (productRepo.findById(id).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not existed");
        ProductEntity product = productRepo.findById(id).get();
        mapper.updateProductFromDto(productDTO, product);
        if (productDTO.getCategoryId() != null) {
            Optional<CategoryEntity> category = categoryRepo.findById(productDTO.getCategoryId());
            if (category.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category not found");
            }
            product.setCategory(category.get());
        }
        return productRepo.save(product);
    }

    public void remove(String id) {
        if (productRepo.findById(id).isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Product not existed");
        productRepo.deleteById(id);
    }
}
