package com.penguin.esms.components.saleBill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleBillService {
    private final SaleBillRepo saleBillRepo;
    @Autowired
    public SaleBillService(SaleBillRepo saleBillRepo) {
        this.saleBillRepo = saleBillRepo;
    }
}
