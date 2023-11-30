package com.penguin.esms.components.importBill.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class importBillDTO {
    private String staffId;
    private String supplierId;
    private String paymentMethod;
    private Date importDate;
    private List<String> importProducts = new ArrayList<>();

}
