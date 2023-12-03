package com.penguin.esms.components.customer;

import com.penguin.esms.components.customer.dto.CustomerDTO;
import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.supplier.SupplierEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping
    public List<CustomerEntity> getAll(@RequestParam(defaultValue = "") String name) {
        return customerService.findByName(name);
    }
    @GetMapping("{id}")
    public CustomerEntity getById(@PathVariable String id) {
        return customerService.getById(id);
    }
    @GetMapping("phone/{phone}")
    public CustomerEntity getByPhone(@PathVariable String phone) {
        return customerService.getByPhone(phone);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> post(@RequestBody CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.postCustomer(customerDTO));
    }
    @PutMapping("{id}")
    public ResponseEntity<?> put(@RequestParam MultipartFile photo, @Valid CustomerDTO customerDTO, @PathVariable String id) throws IOException {
        return ResponseEntity.ok(customerService.update(customerDTO, id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        CustomerEntity customer = customerService.removeCustomer(id);
        return ResponseEntity.ok().build();
    }
}
