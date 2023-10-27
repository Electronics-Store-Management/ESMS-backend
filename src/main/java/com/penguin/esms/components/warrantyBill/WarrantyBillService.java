package com.penguin.esms.components.warrantyBill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarrantyBillService {
    private final WarrantyBillRepo warrantyBillRepo;
    @Autowired
    public WarrantyBillService(WarrantyBillRepo warrantyBillRepo) {
        this.warrantyBillRepo = warrantyBillRepo;
    }
}
