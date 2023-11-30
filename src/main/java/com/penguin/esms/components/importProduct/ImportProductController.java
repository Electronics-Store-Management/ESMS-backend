package com.penguin.esms.components.importProduct;

import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.ProductService;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.services.AmazonS3Service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping(path ="import_product")
public class ImportProductController {
    private final ImportProductService importProductService;
//    @GetMapping
//    public List<ImportProductEntity> getAll(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String product) {
//        return importProductService.findByName(name, product);
//    }

    @GetMapping("{id}")
    public ImportProductEntity getImportProduct(@PathVariable String id) {
        return importProductService.getImportProduct(id);
    }

}
