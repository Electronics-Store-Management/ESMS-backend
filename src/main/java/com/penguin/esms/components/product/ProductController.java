package com.penguin.esms.components.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path ="product")
public class ProductController {
    private final ProductService service;

    @GetMapping
    public List<ProductEntity> getAl(@RequestParam(defaultValue = "") String name) {
        return service.findByName(name);
    }

    @GetMapping("{id}")
    public ProductEntity getProduct(@PathVariable String id) {
        return service.getProduct(id);
    }

    @PostMapping
    public ResponseEntity<?> postProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(service.add(productDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> putProduct(@RequestBody ProductDTO productDTO, @PathVariable String id) {
        return ResponseEntity.ok(service.update(productDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        service.remove(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({ ResponseStatusException.class })
    public ResponseEntity<?> handleException(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ResponseEntity<?> handleNotReadableException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request body not found");
    }
}
