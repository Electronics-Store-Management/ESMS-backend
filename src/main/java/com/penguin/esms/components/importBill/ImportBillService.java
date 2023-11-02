package com.penguin.esms.components.importBill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportBillService {
    private final ImportBillRepo importBillRepo;
    @Autowired
    public ImportBillService(ImportBillRepo importBillRepo) {
        this.importBillRepo = importBillRepo;
    }
}
