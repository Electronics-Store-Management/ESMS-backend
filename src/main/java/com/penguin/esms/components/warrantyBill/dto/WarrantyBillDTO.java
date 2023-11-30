package com.penguin.esms.components.warrantyBill.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WarrantyBillDTO {
    private String staffId;
    private String customerId;
    private Date warrantyDate;
    private List<String> warrantyProducts = new ArrayList<>();

}
