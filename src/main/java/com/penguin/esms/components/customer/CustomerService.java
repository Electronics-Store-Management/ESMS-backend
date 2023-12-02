package com.penguin.esms.components.customer;

import com.penguin.esms.components.category.CategoryEntity;
import com.penguin.esms.components.customer.dto.CustomerDTO;
import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.product.ProductEntity;
import com.penguin.esms.components.product.dto.ProductDTO;
import com.penguin.esms.components.supplier.SupplierEntity;
import com.penguin.esms.entity.Error;
import com.penguin.esms.mapper.DTOtoEntityMapper;
import com.penguin.esms.mapper.DTOtoEntityMapperImpl;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final DTOtoEntityMapper mapper;

    public CustomerEntity getCustomer(String customerId) {
        Optional<CustomerEntity> customer = customerRepo.findById(customerId);
        if (customer.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        return customer.get();
    }
    public CustomerEntity postCustomer(CustomerEntity customerEntity) {
        return customerRepo.save(customerEntity);
    }
    public CustomerEntity removeCustomer(String id) {
        Optional<CustomerEntity> customer = customerRepo.findById(id);
        if (customer.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Customer not existed").toString());
        customerRepo.deleteById(id);
        return customer.get();
    }
    private CustomerEntity updateFromDTO(CustomerDTO customerDTO, CustomerEntity customer) {
        mapper.updateCustomerFromDto(customerDTO, customer);
        return customer;
    }
    public CustomerEntity update(CustomerDTO customerDTO, String id) throws IOException {
        Optional<CustomerEntity> customer = customerRepo.findById(id);
        if (customer.isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, new Error("Customer not existed").toString());
        CustomerEntity customerEntity = updateFromDTO(customerDTO, customerRepo.findById(id).get());
               return customerRepo.save(customerEntity);
    }

}
