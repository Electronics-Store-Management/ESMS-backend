package com.penguin.esms.components.product;

import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.services.AmazonS3Service;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path ="product")
public class ProductController {
    private final ProductService service;
    private final AmazonS3Service amazonS3Service;

    @GetMapping
    public List<ProductEntity> getAl(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String category) {
        return service.findByName(name, category);
    }

    @GetMapping("{id}")
    public ProductEntity getProduct(@PathVariable String id) {
        return service.getProduct(id);
    }

    @PostMapping
    public ResponseEntity<?> postProduct(@RequestParam @Nullable MultipartFile photo, @Valid ProductDTO productDTO) throws IOException {
        if (photo != null) {
            String objectURL = amazonS3Service.addFile(photo, productDTO.getName() + "_" + photo.getOriginalFilename());
            productDTO.setPhotoURL(objectURL);
        }
        return ResponseEntity.ok(service.add(productDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putProduct(@RequestParam @Nullable MultipartFile photo, @Valid ProductDTO productDTO, @PathVariable String id) throws IOException {
        return ResponseEntity.ok(service.update(productDTO, id, photo));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        ProductEntity product = service.remove(id);
//        if (product.getPhotoURL() != null)
//        List<String> parsedURL = Arrays.stream(product.getPhotoURL().split("/")).toList();
//        amazonS3Service.deleteFile(parsedURL.get(parsedURL.size() - 1));
        return ResponseEntity.ok().build();
    }
}
