package com.penguin.esms.components.saleBill.dto;

import com.penguin.esms.components.saleProduct.SaleProductEntity;

import java.util.Date;
import java.util.List;

public class SaleBillDTO {
    private String staffId;
    private String customerId;
    private String paymentMethod;
    private Date saleDate;
    private Float discount;
    private List<String> saleProducts;
}
