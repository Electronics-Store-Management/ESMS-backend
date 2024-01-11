package com.penguin.esms.components.product;

import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.services.AmazonS3Service;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "product")
public class ProductController {
    private final ProductService service;
    private final AmazonS3Service amazonS3Service;

    @GetMapping
    public List<ProductEntity> getAll(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String category) {
        return service.findRelatedCategory(name, category);
    }

    @GetMapping("discountinued")
    public List<ProductEntity> getAllDiscountinued(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String category) {
        return service.findDiscontinuedRelatedCategory(name, category);
    }

    @GetMapping("{id}")
    public ProductEntity getProduct(@PathVariable String id) {
        return service.getProductById(id);
    }

    @GetMapping("history/{id}")
    public List<?> getALlHistory(@PathVariable String id) {
        return service.getRevisionsForProduct(id);
    }

    @PostMapping
    public ResponseEntity<?> postProduct(@RequestParam @Nullable List<MultipartFile> photo, @Valid ProductDTO productDTO) throws IOException {
        if (photo != null) {
            List<String> photoURLs = new ArrayList<>();
            photo.forEach(multipartFile -> {
                try {
                    String objectURL = amazonS3Service.addFile(multipartFile, productDTO.getName() + "_" + multipartFile.getOriginalFilename());
                    photoURLs.add(objectURL);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            productDTO.setPhotoURL(String.join(";", photoURLs));
        }
        return ResponseEntity.ok(service.add(productDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putProduct(@RequestParam @Nullable MultipartFile photo, @Valid ProductDTO productDTO, @PathVariable String id) throws IOException {
        return ResponseEntity.ok(service.update(productDTO, id, photo));
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable String id) {
        service.remove(id);
    }
}
