package com.penguin.esms.components.warrantyBill;

import com.penguin.esms.components.importBill.ImportBillEntity;
import com.penguin.esms.components.importBill.ImportBillService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "warranty")
@RequiredArgsConstructor
public class WarrantyBillController {
    private final WarrantyBillService warrantyBillService;

    @GetMapping("{id}")
    public WarrantyBillEntity get(@PathVariable String id) {
        return warrantyBillService.getWarrantyBill(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> post(@RequestBody WarrantyBillEntity warrantyBillEntity) {
        return ResponseEntity.ok(warrantyBillService.postWarrantyBill(warrantyBillEntity));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        WarrantyBillEntity warrantyBill = warrantyBillService.removeWarrantyBill(id);
        return ResponseEntity.ok().build();
    }
}
