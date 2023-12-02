package com.penguin.esms.components.importBill.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImportBillDTO {
    private String staffId;
    private String supplierId;
    private String paymentMethod;
    private Date importDate;
    private List<String> importProducts = new ArrayList<>();

}
