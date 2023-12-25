package com.penguin.esms.components.customer.dto;

import com.penguin.esms.components.saleBill.SaleBillEntity;
import com.penguin.esms.components.saleBill.dto.SaleBillDTO;
import com.penguin.esms.components.staff.validators.PhoneNumberFormat;
import com.penguin.esms.components.warrantyBill.WarrantyBillEntity;
import com.penguin.esms.components.warrantyBill.dto.WarrantyBillDTO;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private String name;
    @PhoneNumberFormat(message = "Invalid phone number")
    private String phone;
    private String address;
    private List<WarrantyBillDTO> warrantyBills;
    private List<SaleBillDTO> saleBills;


    public CustomerDTO(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
