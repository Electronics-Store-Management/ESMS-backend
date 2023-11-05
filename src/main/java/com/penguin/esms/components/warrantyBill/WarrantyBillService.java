package com.penguin.esms.components.warrantyBill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Service
public class WarrantyBillService {
    private final WarrantyBillRepo warrantyBillRepo;
    @Autowired
    public WarrantyBillService(WarrantyBillRepo warrantyBillRepo) {
        this.warrantyBillRepo = warrantyBillRepo;
    }
    public Boolean checkWarranty(Date buyDate, Period periodTime){
        if (Period.between(LocalDate.now(), buyDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getMonths() > periodTime.getMonths())
            return false;
        return true;
    }
}
