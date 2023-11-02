package com.penguin.esms.components.saleBill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "saleBill")
public class SaleBillController {
    private final SaleBillService saleBillService;
    @Autowired
    public SaleBillController(SaleBillService saleBillService) {
        this.saleBillService = saleBillService;
    }
}
