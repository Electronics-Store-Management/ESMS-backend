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
    public List<CustomerEntity> getAll(@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String phone) {
        return customerService.getCustomer(name, phone);
    }
    @GetMapping("banned")
    public List<CustomerEntity> getAllBanned(@RequestParam(defaultValue = "") String name) {
        return customerService.getBannedCustomer(name);
    }
    @GetMapping("{id}")
    public CustomerEntity getById(@PathVariable String id) {
        return customerService.getById(id);
    }
    @GetMapping("phone/{phone}")
    public List<CustomerEntity> getByPhone(@PathVariable String phone) {
        return customerService.getByPhone(phone);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> post(@RequestBody CustomerDTO dto) {
        return ResponseEntity.ok(customerService.postCustomer(dto));
    }
    @PutMapping("{id}")
    public ResponseEntity<?> put(@RequestBody MultipartFile photo, @Valid CustomerDTO customerDTO, @PathVariable String id) throws IOException {
        return ResponseEntity.ok(customerService.update(customerDTO, id));
    }
    @DeleteMapping("{id}")
    public void remove(@PathVariable String id) {
        customerService.removeCustomer(id);
    }

    @GetMapping("history/{id}")
    public List<?> getALlHistory(@PathVariable String id) {
        return customerService.getRevisions(id);
    }
}
