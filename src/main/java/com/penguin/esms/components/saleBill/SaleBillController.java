package com.penguin.esms.components.saleBill;

import com.penguin.esms.components.importBill.ImportBillEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "sale")
@RequiredArgsConstructor
public class SaleBillController {
    private final SaleBillService saleBillService;
    @GetMapping("{id}")
    public SaleBillEntity get(@PathVariable String id) {
        return saleBillService.getSaleBill(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> post(@RequestBody SaleBillEntity saleBillEntity) {
        return ResponseEntity.ok(saleBillService.postSaleBill(saleBillEntity));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        SaleBillEntity saleBill = saleBillService.removeSaleBill(id);
        return ResponseEntity.ok().build();
    }
}
