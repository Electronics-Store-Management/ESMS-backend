package com.penguin.esms.components.importBill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "import bill")
public class ImportBillController {
    private final ImportBillService importBillService;
    @Autowired
    public ImportBillController(ImportBillService importBillService) {
        this.importBillService = importBillService;
    }
}
